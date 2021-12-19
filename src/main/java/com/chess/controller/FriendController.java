package com.chess.controller;

import com.chess.dao.entity.messanger.FriendEntity;
import com.chess.dao.entity.messanger.UserEntity;
import com.chess.dto.FriendDto;
import com.chess.dto.UserDto;
import com.chess.mapper.FriendMapper;
import com.chess.mapper.UserMapper;
import com.chess.service.interfaces.FriendService;
import com.chess.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/friends")
public class FriendController {

    final FriendService friendService;

    final UserService userService;

    final UserMapper userMapper;

    final FriendMapper friendMapper;

    public FriendController(FriendService friendService, UserMapper userMapper,
            UserService userService, FriendMapper friendMapper) {
        this.friendService = friendService;
        this.userMapper = userMapper;
        this.userService = userService;
        this.friendMapper = friendMapper;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> friend(
            @RequestParam(name = "user", defaultValue = "Noname") String username) {
        try {
            if ("Noname".equals(username)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            UserEntity user = userService.findUserByUsername(username);
            List<FriendEntity> friends = friendService.findAllFriends(user);
            if (friends == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(friends.stream()
                    .map(friendMapper::convertFriendEntityToUserDto)
                    .collect(Collectors.toCollection(ArrayList::new)));
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/add-friend")
    public ResponseEntity<UserDto> addFriend(@RequestBody FriendDto friendDto) {
        UserEntity user = userService.findUserByUsername(
                friendDto.getSecondUsername());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        friendService.addNewFriend(
                userService.findUserByUsername(friendDto.getFirstUsername()),
                user);

        return ResponseEntity.ok(userMapper.convertUserEntityToUserDto(user));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<FriendDto> deleteFriend(
            @RequestBody FriendDto friendDto) {
        friendService.deleteFriend(
                userService.findUserByUsername(friendDto.getFirstUsername()),
                userService.findUserByUsername(friendDto.getSecondUsername()));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
