package com.hotel.lodgingCommander.config;

import com.hotel.lodgingCommander.Filter.JWTUtil;
import com.hotel.lodgingCommander.Filter.LoginFilter;
import com.hotel.lodgingCommander.Filter.LogoutFilter;
import com.hotel.lodgingCommander.service.user.CustomOAuth2UserService;
import com.hotel.lodgingCommander.service.user.CustomSuccessHandler;
import com.hotel.lodgingCommander.service.user.CustomUserDetailsService;
import com.hotel.lodgingCommander.service.user.JwtTokenServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomSuccessHandler customSuccessHandler;
    private final JWTUtil jwtUtil;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtTokenServiceImpl jwtTokenService;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService, AuthenticationConfiguration authenticationConfiguration, CustomSuccessHandler customSuccessHandler, JWTUtil jwtUtil, JwtTokenServiceImpl jwtTokenService) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.customSuccessHandler = customSuccessHandler;
        this.jwtUtil = jwtUtil;
        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtTokenService = jwtTokenService;
    }

    @Bean
    public BCryptPasswordEncoder encoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, CustomUserDetailsService userDetailsService) throws Exception {
        httpSecurity
                .cors(corsCustomizer -> corsCustomizer
                        .configurationSource(request -> {
                            CorsConfiguration configuration = new CorsConfiguration();
                            configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                            configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                            configuration.setAllowCredentials(true);
                            configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
                            configuration.setMaxAge(3600L); // 1 hour
                            configuration.setExposedHeaders(Arrays.asList("Set-Cookie", "Authorization"));
                            return configuration;
                        }))
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .addFilterBefore(new LogoutFilter(jwtUtil, jwtTokenService), UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(new LoginFilter(authenticationManager(), jwtUtil, jwtTokenService), UsernamePasswordAuthenticationFilter.class)

                .authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers("/**").permitAll()
/*                                .requestMatchers("/user/**").permitAll()
                                .requestMatchers("/hotel/**").permitAll()
                                .requestMatchers("/hotel/search").permitAll()
                                .requestMatchers("/rooms").permitAll()
                                .requestMatchers("/static/*").permitAll()
                                .requestMatchers("/log.png").permitAll()
                                .requestMatchers("/uploads/**").permitAll()
                                .requestMatchers("/hotel/details/*").permitAll()
                                .requestMatchers("/sample.jpg").permitAll()
                                .requestMatchers("/booking/cancel/**").permitAll()
                                .requestMatchers("/hotel/details/**").permitAll()
                                .requestMatchers("/likelist/**").permitAll()
                                .requestMatchers("/review/**").permitAll()*/
                                .anyRequest().authenticated())
/*                .formLogin((form) ->
                        form
                                .usernameParameter("email")
                                .passwordParameter("password")
                                .loginProcessingUrl("/user/auth")
                                .successForwardUrl("/user/authSuccess")
                                .failureForwardUrl("/user/authFail")
                )*/
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig.userService(customOAuth2UserService))
                        .successHandler(customSuccessHandler)
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
/*                .logout((logout) ->
                        logout
                                .logoutUrl("/user/logOut")
                                .logoutSuccessUrl("/user/logOutSuccess")
                                .clearAuthentication(true)
                                .deleteCookies("JSESSIONID"))*/
        //        .userDetailsService(userDetailsService)
        ;

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
