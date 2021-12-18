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

    @GetMapping
    public ResponseEntity<UserDto> userInfo(@RequestBody UserDto userDto) {
        try {
            userService.editUser(userDto);
            userService.findUserByUsername(userDto.getUsername());
            return ResponseEntity.ok(userDto);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<UserDto> edit(@RequestBody UserDto userDto) {
        UserEntity user;
        try {
            userService.editUser(userDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Нужно допилить  удаление  (redirect:/logout)
    @DeleteMapping
    public ResponseEntity<UserDto> delete(@RequestBody UserDto userDto) {
        userService.deleteUser(
                userService.findUserByUsername(userDto.getUsername()));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
