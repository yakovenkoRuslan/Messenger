package com.chess.service.implementations;

import com.chess.dao.entity.messanger.StatusEntity;
import com.chess.dao.entity.messanger.UserEntity;
import com.chess.dto.UserDto;
import com.chess.mapper.UserMapper;
import com.chess.service.exceptions.ServiceException;
import com.chess.service.exceptions.ValidationException;
import com.chess.service.interfaces.RegisterService;
import com.chess.service.interfaces.UserService;
import com.chess.service.validator.UserDtoValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
public class RegisterServiceImpl implements RegisterService {

    final UserService userService;

    final PasswordEncoder passwordEncoder;

    final UserDtoValidator userDtoValidator;

    final UserMapper userMapper;

    public RegisterServiceImpl(UserService userService,
            PasswordEncoder passwordEncoder, UserDtoValidator userDtoValidator,
            UserMapper userMapper) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userDtoValidator = userDtoValidator;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public UserDto registerUser(UserDto userDto) throws Exception {
        UserEntity userEntity;
        try {
            userEntity = userMapper.convertUserDtoToUserEntity(userDto);
            userDtoValidator.isValidFields(userDto);
            userDto.setPassword(null);
            userService.saveUser(userEntity);
        } catch (ValidationException e) {
            throw new ServiceException("Error when validate fields. " + e);
        } catch (Exception e) {
            throw new ServiceException("Error when saving user into database");
        }
        return userDto;
    }
}
