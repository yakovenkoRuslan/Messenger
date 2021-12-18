package com.chess.controller;

import com.chess.service.interfaces.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MessageController {


    final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }


}
