package com.chess.mapper;

import com.chess.dao.entity.messanger.UserEntity;
import com.chess.dto.UserDto;
import com.chess.service.interfaces.StatusService;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    final StatusService statusService;

    public UserMapper(StatusService statusService) {
        this.statusService = statusService;
    }

    public UserEntity convertUserDtoToUserEntity(UserDto userDto) {
        UserEntity user = new UserEntity();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(user.getPassword());
        user.setStatus(null);
        return user;
    }

    public UserDto convertUserEntityToUserDtp(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setUsername(userEntity.getUsername());
        userDto.setEmail(userEntity.getEmail());
        userDto.setPassword(userEntity.getPassword());
        return userDto;
    }
}
