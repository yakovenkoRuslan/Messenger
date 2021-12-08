package com.chess.service.implementations;

import com.chess.dao.entity.messanger.MessageEntity;
import com.chess.dao.entity.messanger.UserEntity;
import com.chess.dao.repository.MessageRepository;
import com.chess.service.interfaces.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<MessageEntity> findAllMessagesWithCurrentUsers(
            UserEntity sender, UserEntity recipient) {
        return messageRepository.findAllBySenderAndRecipient(sender, recipient);
    }

    @Override
    public void addNewMessage(MessageEntity messageEntity) {
        messageRepository.save(messageEntity);
        log.info("message with id: {} successfully saved", messageEntity.getId());
    }
}
