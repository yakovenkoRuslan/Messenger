package com.chess.controller;

import com.chess.dao.entity.messanger.UserEntity;
import com.chess.dto.AuthenticationRequestDto;
import com.chess.dto.UserDto;
import com.chess.mapper.UserMapper;
import com.chess.security.jwt.JwtTokenProvider;
import com.chess.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    final JwtTokenProvider jwtTokenProvider;

    final AuthenticationManager authenticationManager;

    final UserService userService;

    final UserMapper userMapper;

    public LoginController(JwtTokenProvider jwtTokenProvider,
            AuthenticationManager authenticationManager,
            UserService userService, UserMapper userMapper) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public ResponseEntity<UserDto> login(
            @RequestBody AuthenticationRequestDto authenticationRequestDto) {
        try {
            String username = authenticationRequestDto.getUsername();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username,
                            authenticationRequestDto.getPassword()));
            UserEntity user = userService.findUserByUsername(username);
            log.info("user: {} find", user);

            UserDto responseUser = userMapper.convertUserEntityToUserDto(user);
            String token = jwtTokenProvider.createToken(user);
            log.info("token {} successfully created", token);

            responseUser.setAuthenticationToken(token);
            responseUser.setOnline(true);
            user.setOnline(true);
            userService.saveUser(user);

            return ResponseEntity.ok(responseUser);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
