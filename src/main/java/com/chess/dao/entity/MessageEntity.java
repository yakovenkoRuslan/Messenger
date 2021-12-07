package com.chess.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "message")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sender")
    @ManyToOne
    private UserEntity userEntity;

    @Column(name = "recipient")
    @ManyToOne
    private UserEntity recipient;

    @Column(name = "text")
    private String msg;
}
