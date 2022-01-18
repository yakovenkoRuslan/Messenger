package com.chess.service.interfaces;

import com.chess.dao.entity.messanger.FriendEntity;
import com.chess.dao.entity.messanger.UserEntity;
import com.chess.service.exceptions.ServiceException;

import java.util.List;

public interface FriendService {

    List<FriendEntity> findAllFriends(UserEntity user);

    void addNewFriend(UserEntity firstUser, UserEntity secondUser);

    void deleteFriend(UserEntity firstUser, UserEntity secondUser)
            throws ServiceException;

    FriendEntity findFriendEntityByFirstUserAndSecondUser(UserEntity firstUser,
            UserEntity secondUser);
}
