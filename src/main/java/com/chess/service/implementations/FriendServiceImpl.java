package com.chess.service.implementations;

import com.chess.dao.entity.messanger.FriendEntity;
import com.chess.dao.entity.messanger.UserEntity;
import com.chess.dao.repository.FriendRepository;
import com.chess.service.interfaces.FriendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FriendServiceImpl implements FriendService {

    final FriendRepository friendRepository;

    public FriendServiceImpl(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    @Override
    public List<FriendEntity> findAllFriends(UserEntity user) {
        return friendRepository.findAllByFirstUser(user);
    }

    @Override
    public void addNewFriend(UserEntity firstUser, UserEntity secondUser) {
        if (friendRepository.findFriendEntityByFirstUserAndSecondUser(firstUser,
                secondUser) != null) {
            log.info("friend {} already exists!", secondUser.getUsername());
            return;
        }
        FriendEntity friendEntity = new FriendEntity();
        friendEntity.setFirstUser(firstUser);
        friendEntity.setSecondUser(secondUser);
        log.info(
                "successfully created friendEntity with first user id: {} and second user id: {}",
                firstUser.getId(), secondUser.getId());
        friendRepository.save(friendEntity);
        log.info("friendEntity {} successfully added", friendEntity);

    }

    @Override
    public void deleteFriend(UserEntity firstUser, UserEntity secondUser) {
        FriendEntity friendEntity = findFriendEntityByFirstUserAndSecondUser(
                firstUser, secondUser);
        log.info(
                "successfully created friendEntity with first user id: {} and second user id: {}",
                firstUser.getId(), secondUser.getId());
        if (friendEntity != null) {
            friendRepository.delete(friendEntity);
        }
        log.info("friendEntity {} successfully deleted", friendEntity);
    }

    @Override
    public FriendEntity findFriendEntityByFirstUserAndSecondUser(
            UserEntity firstUser, UserEntity secondUser) {
        return friendRepository.findFriendEntityByFirstUserAndSecondUser(
                firstUser, secondUser);
    }
}
