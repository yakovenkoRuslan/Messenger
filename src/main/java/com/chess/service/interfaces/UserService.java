package com.chess.service.interfaces;

import com.chess.dao.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity findUserById();

    UserEntity findUserByUsername();

    List<UserEntity> findAllUsers();

    void saveUser(UserEntity userEntity);

    void editUser(UserEntity userEntity);

    void deleteUser(UserEntity userEntity);

}
