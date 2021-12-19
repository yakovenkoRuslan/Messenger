package com.chess.controller;

import com.chess.dao.entity.messanger.UserEntity;
import com.chess.dto.UserDto;
import com.chess.mapper.UserMapper;
import com.chess.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/profile")
public class ProfileController {

    final UserService userService;

    final UserMapper userMapper;

    public ProfileController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public ResponseEntity<UserDto> getUserInfo(
            @RequestParam(value = "username", defaultValue = "Noname") String username) {

        UserEntity userEntity = userService.findUserByUsername(username);

        if (userEntity == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        return ResponseEntity.ok(
                userMapper.convertUserEntityToUserDto(userEntity));
    }

}