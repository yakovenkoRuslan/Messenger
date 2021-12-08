package com.chess.dao.repository;

import com.chess.dao.entity.messanger.FriendEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<FriendEntity, Long> {

    List<FriendEntity> findAllByFirstUser(FriendEntity friendEntity);
}
