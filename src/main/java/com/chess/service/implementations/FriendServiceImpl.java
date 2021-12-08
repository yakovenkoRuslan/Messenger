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
    public List<FriendEntity> findAllFriends(FriendEntity user) {
        return friendRepository.findAllByFirstUser(user);
    }

    @Override
    public void addNewFriend(UserEntity firstUser, UserEntity secondUser) {
        FriendEntity friendEntity = new FriendEntity(firstUser, secondUser);
        log.info(
                "successfully created friendEntity with first user id: {} and second user id: {}",
                firstUser.getId(), secondUser.getId());
        friendRepository.save(friendEntity);
        log.info("friendEntity {} successfully added", friendEntity);

    }

    @Override
    public void deleteFriend(UserEntity firstUser, UserEntity secondUser) {
        FriendEntity friendEntity = new FriendEntity(firstUser, secondUser);
        log.info(
                "successfully created friendEntity with first user id: {} and second user id: {}",
                firstUser.getId(), secondUser.getId());
        friendRepository.delete(friendEntity);
        log.info("friendEntity {} successfully deleted", friendEntity);
    }
}
