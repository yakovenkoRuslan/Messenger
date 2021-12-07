package com.chess.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "game")
public class GameEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "first_player")
    Long firstPlayerId;

    @Column(name = "second_player")
    Long secondPlayerId;

    @Column(name = "first_player_color")
    String firstPlayerColor;

    @Column(name = "second_player_color")
    String secondPlayerColor;

    @Column(name = "winner")
    Long userWinnerId;

    @Column(name = "type_of_game")
    Long typeOfGame;

    @Column(name = "received_rating")
    int receivedRating;

    @Column(name = "datetime")
    Date date;

}
