package com.chess.dao.repository;

import com.chess.dao.entity.messanger.FriendEntity;
import com.chess.dao.entity.messanger.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<FriendEntity, Long> {

    List<FriendEntity> findAllByFirstUser(FriendEntity friendEntity);

    FriendEntity findFriendEntityByFirstUserAndSecondUser(
            UserEntity firstUser, UserEntity secondUser);
}
