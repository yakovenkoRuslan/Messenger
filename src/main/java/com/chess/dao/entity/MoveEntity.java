package com.chess.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Move")
public class MoveEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "game_id")
    @ManyToOne
    GameEntity gameEntity;

    @Column(name = "color")
    String color;

    @Column(name = "figure")
    String figure;

    @Column(name = "new_x_value")
    int coordinateX;

    @Column(name = "new_y_value")
    int coordinateY;

}
