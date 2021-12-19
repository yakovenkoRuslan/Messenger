package com.chess.dao.repository;

import com.chess.dao.entity.messanger.MessageEntity;
import com.chess.dao.entity.messanger.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findAllBySenderAndRecipient(UserEntity sender,
            UserEntity recipient);

    MessageEntity findMessageEntityById(Long id);
}
