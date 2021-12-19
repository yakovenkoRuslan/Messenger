package com.chess.service.implementations;

import com.chess.dao.entity.messanger.MessageEntity;
import com.chess.dao.entity.messanger.UserEntity;
import com.chess.dao.repository.MessageRepository;
import com.chess.service.interfaces.MessageService;
import com.chess.util.MessageEntityComparator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<MessageEntity> messagesFromFirstUser = messageRepository.findAllBySenderAndRecipient(
                sender, recipient);
        List<MessageEntity> messagesFromSecondUser = messageRepository.findAllBySenderAndRecipient(
                recipient, sender);

        messagesFromFirstUser = messagesFromFirstUser != null ?
                messagesFromFirstUser :
                new ArrayList<>();

        messagesFromSecondUser = messagesFromSecondUser != null ?
                messagesFromSecondUser :
                new ArrayList<>();

        messagesFromFirstUser.addAll(messagesFromSecondUser);
        messagesFromFirstUser.sort(new MessageEntityComparator());

        log.info("Count of all messages between {} and {} is {}",
                recipient.getUsername(), sender.getUsername(),
                messagesFromFirstUser.size());
        return messagesFromFirstUser;
    }

    @Override
    public void addNewMessage(MessageEntity messageEntity) {
        messageRepository.save(messageEntity);
        log.info("message successfully saved");
    }
}
