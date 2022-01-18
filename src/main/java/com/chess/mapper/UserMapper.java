package com.chess.mapper;

import com.chess.dao.entity.messanger.UserEntity;
import com.chess.dto.UserDto;
import com.chess.service.interfaces.StatusService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    final StatusService statusService;

    final PasswordEncoder passwordEncoder;

    public UserMapper(StatusService statusService,
            PasswordEncoder passwordEncoder) {
        this.statusService = statusService;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity convertUserDtoToUserEntity(UserDto userDto) {
        UserEntity user = new UserEntity();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return user;
    }

    public UserDto convertUserEntityToUserDto(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setUsername(userEntity.getUsername());
        userDto.setEmail(userEntity.getEmail());
        userDto.setPassword(null);
        userDto.setOnline(userEntity.isOnline());
        return userDto;
    }
}
