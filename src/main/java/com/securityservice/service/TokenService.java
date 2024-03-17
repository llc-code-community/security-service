package com.securityservice.service;

import com.securityservice.entity.Token;
import com.securityservice.entity.User;
import com.securityservice.model.exception.token.InvalidTokenException;
import com.securityservice.model.exception.token.TokenAlreadyExpiredOrRevoked;
import com.securityservice.model.exception.user.UserJwtNotFoundException;
import com.securityservice.model.exception.user.UserNotFoundException;
import com.securityservice.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtService jwtService;
    private final UserService userService;
    private final TokenRepository tokenRepository;

    public void createToken(User user, String jwt) {
        tokenRepository.save(Token.builder().user(user).token(jwt).expired(false).revoked(false).build());
    }

    public void deletePreviousUserToken(User user) {
        tokenRepository.findByUserId(user.getUserId()).ifPresent(tokenRepository::delete);
    }

    public boolean isTokenValid(String token) {
        User user = loadUserDetailsFromJwt(token);

        boolean isTokenValid = tokenRepository.findByToken(token)
                .map(jwt -> !jwt.isExpired() && !jwt.isRevoked())
                .orElseThrow(() -> new TokenAlreadyExpiredOrRevoked("Your current token already expired or revoked: " + token));

        if (isTokenValid && jwtService.isTokenValid(token, user)) {
            return true;
        } else {
            throw new InvalidTokenException("Invalid token.. Oooppss");
        }
    }

    public User loadUserDetailsFromJwt(String jwt) {
        String email = jwtService.extractEmail(jwt);
        try {
            return userService.loadUserByUsername(email);
        } catch (UserNotFoundException e) {
            throw new UserJwtNotFoundException(e.getMessage());
        }
    }
}
