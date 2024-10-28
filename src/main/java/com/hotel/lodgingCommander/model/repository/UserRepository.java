package com.hotel.lodgingCommander.model.repository;

import com.hotel.lodgingCommander.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    boolean existsByUsername(String username);

    boolean deleteByUsername(String username);

    void deleteById(Long id);

    Optional<User> findById(Long id);

    User findByNickname(String nickname);

    boolean existsById(Long id);

    Boolean existsByNickname(String nickname);


}