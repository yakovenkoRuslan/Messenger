package com.chess.service.implementations;

import com.chess.dao.entity.messanger.UserEntity;
import com.chess.dto.UserDto;
import com.chess.service.exceptions.ServiceException;
import com.chess.service.exceptions.ValidationException;
import com.chess.service.interfaces.RegisterService;
import com.chess.service.interfaces.UserService;
import com.chess.service.validator.UserDtoValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RegisterServiceImpl implements RegisterService {

    final UserService userService;

    final PasswordEncoder passwordEncoder;

    final UserDtoValidator userDtoValidator;

    public RegisterServiceImpl(UserService userService,
            PasswordEncoder passwordEncoder,
            UserDtoValidator userDtoValidator) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userDtoValidator = userDtoValidator;
    }

    @Override
    public UserDto registerUser(UserDto userDto) throws Exception {
        UserEntity userEntity = new UserEntity();
        try {
            userEntity.setUsername(userDto.getUsername());
            userEntity.setEmail(userDto.getEmail());
            userDtoValidator.isValidFields(userDto);
            String password = passwordEncoder.encode(userDto.getPassword());
            userDto.setPassword(password);
            userEntity.setPassword(password);
            userEntity.setStatus(null);
            userService.saveUser(userEntity);
        } catch (ValidationException e) {
            throw new ServiceException("Error when validate fields. ", e);
        } catch (Exception e) {
            throw new ServiceException("Error when saving user into database");
        }
        return userDto;
    }
}
