package com.chess.service.implementations;

import com.chess.dao.entity.messanger.FriendEntity;
import com.chess.dao.entity.messanger.UserEntity;
import com.chess.dao.repository.FriendRepository;
import com.chess.service.exceptions.ServiceException;
import com.chess.service.interfaces.FriendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class FriendServiceImpl implements FriendService {

    final FriendRepository friendRepository;

    public FriendServiceImpl(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    @Override
    @Transactional
    public List<FriendEntity> findAllFriends(UserEntity user) {
        return friendRepository.findAllByFirstUser(user);
    }

    @Override
    @Transactional
    public void addNewFriend(UserEntity firstUser, UserEntity secondUser) {
        if (findFriendEntityByFirstUserAndSecondUser(firstUser,
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
    @Transactional
    public void deleteFriend(UserEntity firstUser, UserEntity secondUser)
            throws ServiceException {

        if (firstUser == null || secondUser == null) {
            throw new ServiceException("FirstUser or secondUser is null!");
        }

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
    @Transactional
    public FriendEntity findFriendEntityByFirstUserAndSecondUser(
            UserEntity firstUser, UserEntity secondUser) {
        return friendRepository.findFriendEntityByFirstUserAndSecondUser(
                firstUser, secondUser);
    }
}
