package com.chess.mapper;

import com.chess.dao.entity.messanger.MessageEntity;
import com.chess.dto.MessageDto;
import com.chess.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class MessageMapper {

    final UserService userService;

    public MessageMapper(UserService userService) {
        this.userService = userService;
    }

    public MessageEntity convertMessageDtoToMessageEntity(
            MessageDto messageDto) {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setSender(
                userService.findUserByUsername(messageDto.getSender()));
        messageEntity.setRecipient(
                userService.findUserByUsername(messageDto.getRecipient()));

        messageEntity.setMsg(messageDto.getMsg());
        messageEntity.setDate(new Date());
        log.info(
                "messageEntity successfully created with: recipient {} , sender {}, msg: {}",
                messageDto.getRecipient(), messageDto.getSender(),
                messageDto.getMsg());
        return messageEntity;
    }

    public MessageDto convertMessageEntityToMessageDto(
            MessageEntity messageEntity) {

        MessageDto messageDto = new MessageDto();
        messageDto.setId(messageEntity.getId());
        messageDto.setRecipient(messageEntity.getRecipient().getUsername());
        messageDto.setSender(messageEntity.getSender().getUsername());
        messageDto.setMsg(messageEntity.getMsg());
        return messageDto;
    }
}
