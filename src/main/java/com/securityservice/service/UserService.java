package com.securityservice.service;

import com.securityservice.model.dto.UserDTO;
import com.securityservice.entity.User;
import com.securityservice.mapper.UserDTOMapper;
import com.securityservice.model.exception.user.UserEmailAlreadyExistsException;
import com.securityservice.model.exception.user.UserNotFoundException;
import com.securityservice.model.request.AuthRequest;
import com.securityservice.model.request.RegisterRequest;
import com.securityservice.model.response.TokenResponse;
import com.securityservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final UserDTOMapper userDTOMapper;
  private final JwtService jwtService;
  private final TokenService tokenService;
  private final PasswordEncoder passwordEncoder;

  public void register(RegisterRequest registerRequest) {
    String email = registerRequest.email();
    if (userRepository.existsUserWithEmail(email)) {
      throw new UserEmailAlreadyExistsException("Email already exists");
    }

    User user = new User(
            registerRequest.firstname(),
            registerRequest.lastname(),
            registerRequest.email(),
            passwordEncoder.encode(registerRequest.password())
    );
    userRepository.save(user);
  }

  public TokenResponse authenticate(AuthRequest authRequest) {
    User user = userRepository.findByEmail(authRequest.email())
            .orElseThrow(() -> new UserNotFoundException("User not found"));

    if (!passwordEncoder.matches(authRequest.password(), user.getPassword())) {
      throw new IllegalArgumentException("Invalid password");
    }

    String token = jwtService.generateToken(user);
    tokenService.createToken(user, token);

    return new TokenResponse(token);
  }

  public UserDTO getUserById(Integer userId) {
    return userRepository.selectUserById(userId)
            .map(userDTOMapper)
            .orElseThrow(() -> new UserNotFoundException(
                    "user with id [%s] not found".formatted(userId)
            ));
  }

  public void deleteUserById(Integer userId) {
    if (!userRepository.existsUserById(userId)) {
      throw new UserNotFoundException("user with id [%s] not found".formatted(userId));
    }

    userRepository.deleteUserById(userId);
  }

  public User loadUserByUsername(String email) {
    return userRepository
            .findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("User Not Found with current email: " + email));
  }
}
