package com.chess.controller;

import com.chess.dto.UserDto;
import com.chess.service.interfaces.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/registration")
public class RegisterController {

    final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping
    public ResponseEntity<UserDto> registration(@RequestBody UserDto userDto) {
        log.info("Registering user: {} with username: {} and email: {}", userDto, userDto.getUsername(), userDto.getEmail());
        try {
            userDto = registerService.registerUser(userDto);
            return ResponseEntity.ok(userDto);
        } catch (Exception e) {
            log.error("Exceptions in register controller: " + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userDto);
        }
    }
}
