/*
package com.hotel.lodgingCommander.controller;

import com.hotel.lodgingCommander.model.entity.enums.UserRole;
import com.hotel.lodgingCommander.model.repository.UserRepository;
import com.hotel.lodgingCommander.model.user.RegisterModel;
import com.hotel.lodgingCommander.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final UserRepository userRepository;
    //회원가입
    @PostMapping
    public ResponseEntity<?> register(@RequestBody RegisterModel registerModel) {
        try {
            userService.create(registerModel);
            return ResponseEntity.ok("회원가입 성공");
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    //비밀번호 변경
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePassword(@PathVariable Long id, @RequestParam String newPassword) {
        if(userRepository.findById(id).get().getUsername().startsWith("naver")){
            throw new IllegalArgumentException("올바르지 않은 접근입니다.");
        }
        return ResponseEntity.ok(userService.updatePassword(id, newPassword));
    }
    //닉네임 변경
    @PutMapping("/updateNickname/{id}")
    public ResponseEntity<?> updateNickname(@PathVariable Long id, @RequestParam String newNicknmae) {
        if(userRepository.findById(id).get().getUsername().startsWith("naver")){
            throw new IllegalArgumentException("올바르지 않은 접근입니다.");
        }
        return ResponseEntity.ok(userService.updateNickname(id, newNicknmae));
    }
    //권한 변경
    @PutMapping("/updateRole/{id}")
    public ResponseEntity<?> updateRole(@PathVariable Long id, @RequestParam UserRole newRole) {
        return ResponseEntity.ok(userService.updateRole(id, newRole));
    }
    //한명 정보 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> getInfo(@PathVariable Long id) {
        if(!userRepository.existsById(id)){
            throw new IllegalArgumentException("올바르지 않은 접근입니다.");
        }
        return ResponseEntity.ok( userService.getUserDetail(id));
    }

    //회원 정보 조회(관리자용)
    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    //회원 탈퇴
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if(!userRepository.existsById(id)){
            throw new IllegalArgumentException("올바르지 않은 접근입니다.");
        }
        return ResponseEntity.ok( userService.delete(id));
    }

    //닉네임 검증
    @GetMapping("/checkNickname")
    public ResponseEntity<?> checkNickname(@RequestBody RegisterModel registerModel) {
        return ResponseEntity.ok(userService.checkNickname(registerModel));
    }

    //비밀번호 확인
    @GetMapping("/checkPassword")
    public ResponseEntity<?> checkPassword(@RequestBody RegisterModel registerModel) {
        return ResponseEntity.ok(userService.checkPassword(registerModel.getPassword(), registerModel.getPasswordcheck()));
    }

    //권한 확인
    @GetMapping("/checkRole/{id}")
    public ResponseEntity<?> checkRole(@RequestBody RegisterModel registerModel) {
        return ResponseEntity.ok(userService.checkRole(registerModel.getId()));
    }
    //이메일 중복확인
    @GetMapping("/checkEmail")
    public ResponseEntity<?> checkEmail(@RequestBody RegisterModel registerModel) {
        return ResponseEntity.ok(userService.checkRole(registerModel.getId()));
    }


}*/
