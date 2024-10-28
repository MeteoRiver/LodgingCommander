package com.hotel.lodgingCommander.model.user;

import com.hotel.lodgingCommander.model.entity.enums.UserGrade;
import com.hotel.lodgingCommander.model.entity.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserModel {

    private Long id;
    @NotBlank
    @Pattern(regexp = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$",
            message = "이메일 주소의 형식은 ~~@~~.~~입니다.")
    private String username;
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-Z])((?=.*\\d)|(?=.*\\W)).{8,128}$",
            message = "비밀번호는 8자리 이상 대소문자+숫자+특수문자 조합으로 구성되어야합니다.")
    private String password;
    @NotBlank
    @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
            message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다.")
    private String tel;

    @NotBlank
    private String nickname;

    @NotBlank
    private String name;

    @NotBlank
    private UserGrade grade;

    @NotBlank
    private UserRole role;
}
