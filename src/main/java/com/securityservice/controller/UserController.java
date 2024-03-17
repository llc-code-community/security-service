package com.securityservice.controller;

import com.securityservice.model.dto.UserDTO;
import com.securityservice.model.request.AuthRequest;
import com.securityservice.model.request.RegisterRequest;
import com.securityservice.model.response.TokenResponse;
import com.securityservice.service.TokenService;
import com.securityservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;
    private final TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<Boolean> registerUser(@RequestBody @Valid RegisterRequest registerRequest) {
        userService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/auth")
    public ResponseEntity<TokenResponse> authenticateUser(@RequestBody @Valid AuthRequest authRequest) {
        TokenResponse tokenResponse = userService.authenticate(authRequest);
        return ResponseEntity.ok(tokenResponse);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userId) {
        UserDTO userDTO = userService.getUserById(userId);
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }
}
