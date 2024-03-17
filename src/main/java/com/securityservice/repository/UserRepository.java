package com.securityservice.repository;

import com.securityservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsUserWithEmail(String email);
    boolean existsUserById(Integer userId);
    Optional<User> selectUserById(Integer userId);

    void deleteUserById(Integer userId);
}
