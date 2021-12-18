package com.chess.service.implementations;

import com.chess.dao.entity.messanger.UserEntity;
import com.chess.dao.repository.UserRepository;
import com.chess.dto.UserDto;
import com.chess.service.exceptions.ValidationException;
import com.chess.service.interfaces.UserService;
import com.chess.service.validator.UserDtoValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    final PasswordEncoder passwordEncoder;

    final UserDtoValidator userDtoValidator;

    final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository,
            UserDtoValidator userDtoValidator, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userDtoValidator = userDtoValidator;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity findUserById(Long id) {
        return userRepository.findUserEntityById(id);
    }

    @Override
    public UserEntity findUserByUsername(String username) {
        return userRepository.findUserEntityByUsername(username);
    }

    @Override
    public List<UserEntity> findAllUsers() {
        return userRepository.findAllByIdNotNull();
    }

    @Override
    public void saveUser(UserEntity userEntity) {
        userRepository.save(userEntity);
        log.info("User with id: {} successfully find", userEntity.getId());
    }

    @Override
    public void editUser(UserDto userDto) throws ValidationException {
        UserEntity user = findUserByUsername(userDto.getUsername());
        if (!Objects.equals(userDto.getPassword(), null)
                && userDtoValidator.isValidPassword(userDto.getPassword())) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        if (!Objects.equals(userDto.getEmail(), null)
                && userDtoValidator.isValidEmail(userDto.getEmail())) {
            user.setEmail(userDto.getEmail());
        }
        log.info("Prepared user for edit : id {} username {} email {}",
                user.getId(), user.getUsername(), user.getEmail());
        userRepository.save(user);
        log.info("User with username: {} successfully updated",
                userDto.getId());
    }

    @Override
    public void deleteUser(UserEntity userEntity) {
        userRepository.delete(userEntity);
        log.info("User with id: {} successfully deleted", userEntity.getId());
    }
}
