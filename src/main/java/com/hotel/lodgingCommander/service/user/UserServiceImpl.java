package com.hotel.lodgingCommander.service.user;

import com.hotel.lodgingCommander.model.entity.User;
import com.hotel.lodgingCommander.model.entity.enums.UserRole;
import com.hotel.lodgingCommander.model.repository.UserRepository;
import com.hotel.lodgingCommander.model.user.RegisterModel;
import com.hotel.lodgingCommander.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

import static com.hotel.lodgingCommander.model.entity.enums.UserRole.ROLE_ADMIN;
import static com.hotel.lodgingCommander.model.entity.enums.UserRole.ROLE_USER;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final int SILVER_THRESHOLD = 5;
    private final int GOLD_THRESHOLD = 15;
    private final int VIP_THRESHOLD = 30;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void create(RegisterModel registerModel) {
        try {
            // 사용자 이름 중복 확인
            if (userRepository.existsByUsername(registerModel.getUsername())) {
                throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
            }

            // 닉네임 중복 확인
            if (userRepository.existsByNickname(registerModel.getNickname())) {
                throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
            }

            // 비밀번호와 비밀번호 확인 일치 여부 확인
            if (!registerModel.getPassword().equals(registerModel.getPasswordcheck())) {
                throw new IllegalArgumentException("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            }

            // 이메일 검증
            if(!Pattern.matches("^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$", registerModel.getUsername())){
                throw new IllegalArgumentException("이메일 형식이 올바르지 않습니다.");
            }

            // 비밀번호 검증
            if(!Pattern.matches("^(?=.*[a-zA-Z])((?=.*\\d)|(?=.*\\W)).{8,128}$", registerModel.getPassword())){
                throw new IllegalArgumentException("비밀번호가 올바르지 않습니다. 최소 8자 이상이며, 알파벳 대소문자, 숫자 또는 특수 문자가 포함시켜주세요.");
            }

            // 휴대폰 번호 검증
            if(!Pattern.matches("^010-\\d{3,4}-\\d{4}$", registerModel.getTel())){
                throw new IllegalArgumentException("휴대폰 번호가 올바르지 않습니다.");
            }

            // 비밀번호 암호화
            String encodedPassword = passwordEncoder.encode(registerModel.getPassword());

            // 새로운 UserModel 생성
            User user = new User();
            user.setUsername(registerModel.getUsername());
            user.setPassword(encodedPassword);
            user.setName(registerModel.getName());
            user.setTel(registerModel.getTel());
            user.setNickname(registerModel.getNickname());
            user.setGrade(registerModel.getGrade());
            user.setRole(ROLE_USER); // 기본 역할 설정

            // 사용자 정보 저장
            userRepository.save(user);
        } catch (DataAccessException e) {
            // 데이터베이스 접근 예외 처리
            throw new RuntimeException("회원가입 중 데이터베이스 오류 발생: " + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            // 비즈니스 로직 예외 처리
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    @Override
    public boolean checkNickname(RegisterModel registerModel) {
        try {
            return !userRepository.existsByNickname(registerModel.getNickname());
        } catch (DataAccessException e) {
            // 데이터베이스 접근 예외 처리
            throw new RuntimeException("닉네임 중복 확인 중 데이터베이스 오류 발생: " + e.getMessage(), e);
        }
    }
    @Override
    public boolean updatePassword(Long id, String newPassword) {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        } catch (DataAccessException e) {
            // 데이터베이스 접근 예외 처리
            throw new RuntimeException("비밀번호 변경 중 데이터베이스 오류 발생: " + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            // 비즈니스 로직 예외 처리
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    @Override
    public boolean updateNickname(Long id, String newNickname) {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
            user.setNickname(newNickname);
            userRepository.save(user);
            return true;
        } catch (DataAccessException e) {
            // 데이터베이스 접근 예외 처리
            throw new RuntimeException("비밀번호 변경 중 데이터베이스 오류 발생: " + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            // 비즈니스 로직 예외 처리
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    @Override
    public boolean checkPassword(String rawPassword, String encodedPassword) {
        try {
            return passwordEncoder.matches(rawPassword, encodedPassword);
        } catch (Exception e) {
            // 비밀번호 확인 중 발생할 수 있는 예외 처리
            throw new RuntimeException("비밀번호 확인 중 오류 발생: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean updateRole(Long id, UserRole role) {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
            user.setRole(role);
            userRepository.save(user);
        } catch (DataAccessException e) {
            // 데이터베이스 접근 예외 처리
            throw new RuntimeException("비밀번호 변경 중 데이터베이스 오류 발생: " + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            // 비즈니스 로직 예외 처리
            throw new IllegalArgumentException(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean checkUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserRole checkRole(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("사용자가 존재하지 않습니다."));
        return user.getRole();
    }

    @Override
    public List<User> getAllUsers(Long userId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

            if (user.getRole().equals(ROLE_ADMIN)) {
                return userRepository.findAll();
            } else {
                throw new IllegalArgumentException("관리자만 접근 가능합니다.");
            }
        } catch (DataAccessException e) {
            // 데이터베이스 접근 예외 처리
            throw new RuntimeException("모든 사용자 조회 중 데이터베이스 오류 발생: " + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            // 비즈니스 로직 예외 처리
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    @Override
    public User getUserDetail(Long userId) {
        try {
            return userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
        } catch (DataAccessException e) {
            // 데이터베이스 접근 예외 처리
            throw new RuntimeException("사용자 조회 중 데이터베이스 오류 발생: " + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            // 비즈니스 로직 예외 처리
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(Long id) {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }


}