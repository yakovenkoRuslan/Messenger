package com.chess.service.implementations;

import com.chess.dao.entity.messanger.UserEntity;
import com.chess.dto.UserDto;
import com.chess.service.interfaces.RegisterService;
import com.chess.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RegisterServiceImpl implements RegisterService {

    final UserService userService;

    final PasswordEncoder passwordEncoder;

    public RegisterServiceImpl(UserService userService,
            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDto.getUsername());
        userEntity.setEmail(userDto.getEmail());
        String password = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(password);
        userEntity.setPassword(password);
        userEntity.setStatus(null);

        try {
            userService.saveUser(userEntity);
        } catch (Exception e) {
            log.error("error when saving user into register service" + e);
        }
        return userDto;
    }
}
