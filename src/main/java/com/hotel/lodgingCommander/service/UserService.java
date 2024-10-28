package com.hotel.lodgingCommander.service;

import com.hotel.lodgingCommander.model.entity.User;
import com.hotel.lodgingCommander.model.entity.enums.UserRole;
import com.hotel.lodgingCommander.model.user.RegisterModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    void create(RegisterModel registerModel) ;

    boolean checkNickname(RegisterModel registerModel);

    boolean updateNickname(Long id, String newNickname);

    User getUserDetail(Long id);

    boolean delete(Long userId);

    List<User> getAllUsers(Long id);

    boolean checkPassword(String rawPassword, String encodedPassword);

    boolean updateRole(Long id, UserRole role);

    boolean checkUsername(String username);

    UserRole checkRole(Long id);

    boolean updatePassword(Long id, String newPassword);


}
