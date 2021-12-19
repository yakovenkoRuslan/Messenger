package com.chess.util;

import com.chess.dao.entity.messanger.MessageEntity;

import java.util.Comparator;

public class MessageEntityComparator implements Comparator<MessageEntity> {

    @Override
    public int compare(MessageEntity o1, MessageEntity o2) {
        if (o1.getId() > o2.getId())
            return 1;
        else
            return -1;
    }
}
