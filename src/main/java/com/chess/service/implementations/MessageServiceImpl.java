package com.chess.service.implementations;

import com.chess.dao.entity.messanger.MessageEntity;
import com.chess.dao.entity.messanger.UserEntity;
import com.chess.dao.repository.MessageRepository;
import com.chess.dto.MessageDto;
import com.chess.service.exceptions.ServiceException;
import com.chess.service.interfaces.MessageService;
import com.chess.util.MessageEntityComparator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    @Transactional
    public List<MessageEntity> findAllMessagesWithCurrentUsers(
            UserEntity sender, UserEntity recipient) throws ServiceException {

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

        if (recipient == null || sender == null) {
            throw new ServiceException("Recipient or sender not exists!");
        }

        log.info("Count of all messages between {} and {} is {}",
                recipient.getUsername(), sender.getUsername(),
                messagesFromFirstUser.size());
        return messagesFromFirstUser;
    }

    @Override
    @Transactional
    public MessageEntity findMessageEntityById(Long id) {
        return messageRepository.findMessageEntityById(id);
    }

    @Override
    @Transactional
    public void addNewMessage(MessageEntity messageEntity) {
        messageRepository.save(messageEntity);
        log.info("message successfully saved");
    }

    @Override
    @Transactional
    public void editMessage(MessageDto messageDto) {
        MessageEntity messageEntity = findMessageEntityById(
                messageDto.getId());
        messageEntity.setMsg(messageDto.getMsg());
        messageRepository.save(messageEntity);
        log.info("message with id {} successfully edited", messageDto.getId());
    }

    @Override
    @Transactional
    public void deleteMessage(Long id) {
        messageRepository.delete(findMessageEntityById(id));
        log.info("message with id {} successfully deleted", id);
    }


}
