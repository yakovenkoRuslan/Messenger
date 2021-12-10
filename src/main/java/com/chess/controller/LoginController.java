package com.chess.controller;

import com.chess.dao.entity.messanger.UserEntity;
import com.chess.dto.AuthenticationRequestDto;
import com.chess.security.jwt.JwtTokenProvider;
import com.chess.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    final JwtTokenProvider jwtTokenProvider;

    final AuthenticationManager authenticationManager;

    final UserService userService;

    public LoginController(JwtTokenProvider jwtTokenProvider,
            AuthenticationManager authenticationManager,
            UserService userService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Map<Object, Object>> login(
            @RequestBody AuthenticationRequestDto authenticationRequestDto) {
        try {
            String username = authenticationRequestDto.getUsername();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username,
                            authenticationRequestDto.getPassword()));
            UserEntity user = userService.findUserByUsername(username);
            log.info("user: {} find", user);

            if (user == null) {
                throw new UsernameNotFoundException(
                        "User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(user);

            log.info("token {} successfully created", token);
            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
