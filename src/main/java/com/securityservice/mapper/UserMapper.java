package com.securityservice.mapper;

import com.securityservice.entity.User;
import com.securityservice.model.request.UserRequest;
import com.securityservice.model.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public UserResponse mapToResponse(User user) {
    return new UserResponse(user.getUserId(), user.getFirstName(), user.getLastName());
  }

  public User mapToEntity(UserRequest request) {
    return User.builder().firstName(request.firstName()).lastName(request.lastName()).build();
  }
}
