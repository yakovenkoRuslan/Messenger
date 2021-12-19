package com.chess.service.implementations;

import com.chess.dao.entity.messanger.UserEntity;
import com.chess.dao.repository.UserRepository;
import com.chess.dto.UserDto;
import com.chess.service.exceptions.ValidationException;
import com.chess.service.interfaces.StatusService;
import com.chess.service.interfaces.UserService;
import com.chess.service.validator.UserDtoValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    final StatusService statusService;

    final UserDtoValidator userDtoValidator;

    final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository,
            UserDtoValidator userDtoValidator, StatusService statusService) {
        this.userRepository = userRepository;
        this.userDtoValidator = userDtoValidator;
        this.statusService = statusService;
    }

    @Override
    @Transactional
    public UserEntity findUserById(Long id) {
        return userRepository.findUserEntityById(id);
    }

    @Override
    @Transactional
    public UserEntity findUserByUsername(String username) {
        return userRepository.findUserEntityByUsername(username);
    }

    @Override
    @Transactional
    public List<UserEntity> findAllUsers() {
        return userRepository.findAllByIdNotNull();
    }

    @Override
    @Transactional
    public void saveUser(UserEntity userEntity) {
        userRepository.save(userEntity);
        log.info("User with id: {} successfully find", userEntity.getId());
    }

    @Override
    @Transactional
    public void editUser(UserDto userDto) throws ValidationException {
        UserEntity user = findUserByUsername(userDto.getUsername());
        if (!Objects.equals(userDto.getPassword(), null)
                && userDtoValidator.isValidPassword(userDto.getPassword())) {
            user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        }
        if (!Objects.equals(userDto.getEmail(), null)
                && userDtoValidator.isValidEmail(userDto.getEmail())) {
            user.setEmail(userDto.getEmail());
        }
        log.info("Prepared user for edit : id {} username {} email {}",
                user.getId(), user.getUsername(), user.getEmail());
        saveUser(user);
        log.info("User with id: {} successfully updated",
                userDto.getId());
    }

    @Override
    @Transactional
    public void deleteUser(UserEntity userEntity) {
        userEntity.setStatus(statusService.findStatusById(2L));
        log.info("User with id: {} successfully deleted", userEntity.getId());
    }
}
