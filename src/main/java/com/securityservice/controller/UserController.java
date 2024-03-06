package com.securityservice.controller;

import static org.springframework.http.HttpStatus.OK;

import com.securityservice.model.request.UserRequest;
import com.securityservice.model.response.UserResponse;
import com.securityservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserService userService;

  @GetMapping("/")
  public ResponseEntity<UserResponse> getUserBpId(@RequestParam Long userId) {
    return new ResponseEntity<>(userService.getUserBpId(userId), OK);
  }

  @PostMapping("/")
  public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest request) {
    return new ResponseEntity<>(userService.createUser(request), OK);
  }
}
