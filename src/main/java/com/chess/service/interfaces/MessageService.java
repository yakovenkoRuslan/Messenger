package com.chess.service.interfaces;

import com.chess.dao.entity.messanger.MessageEntity;
import com.chess.dao.entity.messanger.UserEntity;
import com.chess.dto.MessageDto;
import com.chess.service.exceptions.ServiceException;

import java.util.List;

public interface MessageService {
    List<MessageEntity> findAllMessagesWithCurrentUsers(UserEntity sender,
            UserEntity recipient) throws ServiceException;

    void addNewMessage(MessageEntity messageEntity);

    void editMessage(MessageDto messageDto);

    void deleteMessage(Long id);
}
