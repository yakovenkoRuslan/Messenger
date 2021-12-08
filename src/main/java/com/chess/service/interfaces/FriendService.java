package com.chess.service.interfaces;

import com.chess.dao.entity.messanger.FriendEntity;
import com.chess.dao.entity.messanger.UserEntity;

import java.util.List;

public interface FriendService {

    List<FriendEntity> findAllFriends(FriendEntity user);

    void addNewFriend(UserEntity firstUser, UserEntity secondUser);

    void deleteFriend(UserEntity firstUser, UserEntity secondUser);

    FriendEntity findFriendEntityByFirstUserAndSecondUser(UserEntity firstUser,
            UserEntity secondUser);
}
