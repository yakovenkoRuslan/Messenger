package com.chess.service.implementations;

import com.chess.dao.entity.UserEntity;
import com.chess.dao.repository.MessageRepository;
import com.chess.dao.repository.UserRepository;
import com.chess.service.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    final MessageRepository messageRepository;

    public UserServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public UserEntity findUserById() {
        return null;
    }

    @Override
    public UserEntity findUserByUsername() {
        return null;
    }

    @Override
    public List<UserEntity> findAllUsers() {
        return null;
    }

    @Override
    public void saveUser(UserEntity userEntity) {

    }

    @Override
    public void editUser(UserEntity userEntity) {

    }

    @Override
    public void deleteUser(UserEntity userEntity) {

    }
}
