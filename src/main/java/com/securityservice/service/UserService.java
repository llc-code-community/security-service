package com.securityservice.service;

import com.securityservice.entity.User;
import com.securityservice.mapper.UserMapper;
import com.securityservice.model.exception.user.UserNotFoundException;
import com.securityservice.model.request.UserRequest;
import com.securityservice.model.response.UserResponse;
import com.securityservice.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public User loadUserByUsername(String email) {
    return userRepository
            .findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("User Not Found with current email: " + email));
  }
}
