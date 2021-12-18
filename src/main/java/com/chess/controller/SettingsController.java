package com.chess.controller;

import com.chess.dao.entity.messanger.UserEntity;
import com.chess.dto.UserDto;
import com.chess.mapper.UserMapper;
import com.chess.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/settings")
public class SettingsController {

    final UserService userService;

    final UserMapper userMapper;

    public SettingsController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> userInfo(@PathVariable Long id) {
        UserEntity user;
        try {
            user = userService.findUserById(id);
            UserDto responseUser = userMapper.convertUserEntityToUserDto(user);
            userService.editUser(user);
            return ResponseEntity.ok(responseUser);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<UserDto> edit(@RequestBody UserDto userDto) {
        UserEntity user;
        try {
            user = userMapper.convertUserDtoToUserEntity(userDto);
            user.setId(userDto.getId());
            userService.saveUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
