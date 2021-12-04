package com.chess.dao.repository;

import com.chess.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findUserEntityById(Long id);

    UserEntity findUserEntityByUsername(String username);

    List<UserEntity> findAllByIdNotNull();
}
