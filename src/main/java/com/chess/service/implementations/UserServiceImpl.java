package com.chess.service.implementations;

import com.chess.dao.entity.messanger.UserEntity;
import com.chess.dao.repository.UserRepository;
import com.chess.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    public void editUser(UserEntity userEntity) {
        UserEntity user = findUserById(userEntity.getId());
        userEntity.setId(user.getId());
        userRepository.save(user);
        log.info("User with id: {} successfully updated", userEntity.getId());
    }

    @Override
    public void deleteUser(UserEntity userEntity) {
        userRepository.delete(userEntity);
        log.info("User with id: {} successfully deleted", userEntity.getId());
    }
}
