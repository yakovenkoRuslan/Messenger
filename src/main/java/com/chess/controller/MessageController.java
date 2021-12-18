package com.chess.controller;

import com.chess.service.interfaces.MessageService;
import com.chess.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MessageController {


    final UserService userService;

    final MessageService messageService;

    public MessageController(MessageService messageService,
            UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }


}
