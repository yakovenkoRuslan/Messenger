package com.chess.controller;

import com.chess.dao.entity.messanger.MessageEntity;
import com.chess.dto.MessageDto;
import com.chess.mapper.MessageMapper;
import com.chess.service.exceptions.ServiceException;
import com.chess.service.interfaces.MessageService;
import com.chess.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/message")
public class MessageController {

    final UserService userService;

    final MessageService messageService;

    final MessageMapper messageMapper;

    public MessageController(MessageService messageService,
            UserService userService, MessageMapper messageMapper) {
        this.messageService = messageService;
        this.userService = userService;
        this.messageMapper = messageMapper;
    }

    @GetMapping
    public ResponseEntity<List<MessageDto>> getAllMessagesBetweenTwoUsers(
            @RequestParam(name = "firstUser", defaultValue = "Noname") String firstUser,
            @RequestParam(name = "secondUser", defaultValue = "Noname") String secondUser) {
        if (firstUser.equals("Noname") || secondUser.equals("Noname")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            List<MessageEntity> messages = messageService.findAllMessagesWithCurrentUsers(
                    userService.findUserByUsername(firstUser),
                    userService.findUserByUsername(secondUser));

            if (messages == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return ResponseEntity.ok(messages.stream()
                    .map(messageMapper::convertMessageEntityToMessageDto)
                    .collect(Collectors.toCollection(ArrayList::new)));
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping
    public ResponseEntity<MessageDto> send(@RequestBody MessageDto messageDto) {

        log.info("Message recipient {} , sender {}", messageDto.getRecipient(),
                messageDto.getSender());
        MessageEntity messageEntity = messageMapper.convertMessageDtoToMessageEntity(
                messageDto);
        messageService.addNewMessage(messageEntity);
        return ResponseEntity.ok(messageDto);
    }

    @PutMapping
    public ResponseEntity<MessageDto> editMessage(
            @RequestBody MessageDto messageDto) {
        messageService.editMessage(messageDto);
        return ResponseEntity.ok(messageDto);
    }

    @DeleteMapping
    public ResponseEntity<MessageDto> deleteMessage(
            @RequestBody MessageDto messageDto) {
        messageService.deleteMessage(messageDto.getId());
        return ResponseEntity.ok(messageDto);
    }
}
