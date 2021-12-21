package com.chess.mapper;

import com.chess.dao.entity.messanger.FriendEntity;
import com.chess.dao.entity.messanger.UserEntity;
import com.chess.dto.UserDto;
import com.chess.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FriendMapper {

    final UserService userService;

    public FriendMapper(UserService userService) {
        this.userService = userService;
    }

    public UserDto convertFriendEntityToUserDto(FriendEntity friendEntity) {
        UserEntity userEntity = userService.findUserByUsername(
                friendEntity.getSecondUser().getUsername());
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setUsername(userEntity.getUsername());

        userDto.setEmail(userEntity.getEmail());
        userDto.setOnline(userEntity.isOnline());
        return userDto;
    }
}
