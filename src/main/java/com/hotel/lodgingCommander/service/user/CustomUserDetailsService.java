package com.hotel.lodgingCommander.service.user;

import com.hotel.lodgingCommander.model.entity.User;
import com.hotel.lodgingCommander.model.repository.UserRepository;
import com.hotel.lodgingCommander.model.user.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("loadUserByUsername 호출됨: {}", username);  // 메서드 호출 시 로그
        User userData = userRepository.findByUsername(username);

        if (userData != null) {
            logger.info("사용자 정보 발견: {}", username);  // 사용자가 존재할 때 로그
            return new CustomUserDetails(userData);
        } else {
            logger.warn("사용자 정보를 찾을 수 없음: {}", username);  // 사용자가 없을 때 경고 로그
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username);
        }
    }
}