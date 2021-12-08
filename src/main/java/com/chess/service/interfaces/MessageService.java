package com.chess.service.interfaces;

import com.chess.dao.entity.messanger.MessageEntity;
import com.chess.dao.entity.messanger.UserEntity;

import java.util.List;

public interface MessageService {
    List<MessageEntity> findAllMessagesWithCurrentUsers(UserEntity sender,
            UserEntity recipient);

    void addNewMessage(MessageEntity messageEntity);
}
