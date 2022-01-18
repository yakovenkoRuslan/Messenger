package com.chess.service.interfaces;

import com.chess.dao.entity.messanger.UserEntity;
import com.chess.dto.UserDto;
import com.chess.service.exceptions.ValidationException;

import java.util.List;

public interface UserService {

    UserEntity findUserById(Long id);

    UserEntity findUserByUsername(String username);

    List<UserEntity> findAllUsers();

    void saveUser(UserEntity userEntity);

    void editUser(UserDto userDto) throws ValidationException;

    void deleteUser(UserEntity userEntity);

}
