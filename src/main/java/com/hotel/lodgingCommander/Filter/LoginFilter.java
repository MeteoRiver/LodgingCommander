package com.hotel.lodgingCommander.Filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotel.lodgingCommander.model.user.CustomUserDetails;
import com.hotel.lodgingCommander.model.user.LoginModel;
import com.hotel.lodgingCommander.service.user.JwtTokenServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenServiceImpl jwtTokenService;
    private final JWTUtil jwtUtil;

    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, JwtTokenServiceImpl jwtTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/api/users/login/general");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("attemptAuthentication 메서드 호출됨");  // 메서드 호출 시 로그
        LoginModel loginModel = new LoginModel();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ServletInputStream inputStream = request.getInputStream();
            String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            loginModel = objectMapper.readValue(messageBody, LoginModel.class);
            log.info("로그인 요청 데이터: username={}, password={}", loginModel.getUsername(), loginModel.getPassword());  // 요청 데이터 로그
        } catch (Exception e) {
            log.error("로그인 요청 파싱 중 오류 발생", e);
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                loginModel.getUsername(), loginModel.getPassword());

        log.info("인증 토큰 생성: {}", authToken);
        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication)
            throws IOException, ServletException {
        log.info("successfulAuthentication 메서드 호출됨 - 로그인 성공");

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String username = userDetails.getUsername();
        String nickname = "";
        Long id = 0L;

        if (userDetails instanceof CustomUserDetails) {
            nickname = ((CustomUserDetails) userDetails).getNickname();
            id = ((CustomUserDetails) userDetails).getUserId();
            log.info("사용자 인증 성공: username={}, nickname={}, id={}", username, nickname, id);  // 사용자 정보 로그
        }

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.iterator().next().getAuthority();
        log.info("사용자 역할: {}", role);  // 권한 로그

        String access = jwtUtil.createJwt("access", id, role, nickname, 600000L);
        String refresh = jwtUtil.createJwt("refresh", id, role, nickname, 86400000L);
        log.info("JWT 생성 완료 - Access Token={}, Refresh Token={}", access, refresh);  // JWT 생성 로그

        jwtTokenService.storeToken(refresh, id.toString(), 86400000L);

        response.setHeader("access", access);
        response.addCookie(createCookie("access", access));
        response.addCookie(createCookie("refresh", refresh));
        response.setStatus(HttpStatus.OK.value());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
        log.info("unsuccessfulAuthentication 메서드 호출됨 - 로그인 실패");
        log.warn("로그인 실패 원인: {}", failed.getMessage());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        log.info("쿠키 생성 - Key={}, Value={}", key, value);  // 쿠키 생성 로그
        return cookie;
    }
}