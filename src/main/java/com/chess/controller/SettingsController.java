package com.chess.controller;

import com.chess.dto.UserDto;
import com.chess.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/settings")
public class SettingsController {

    final UserService userService;

    public SettingsController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/{id}")
    ResponseEntity<UserDto> edit(@RequestBody UserDto userDto) {

        return null;
    }
}
