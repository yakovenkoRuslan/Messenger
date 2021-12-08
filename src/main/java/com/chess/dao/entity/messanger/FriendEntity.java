package com.chess.dao.entity.messanger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class FriendEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "first_user", referencedColumnName = "id", nullable = false)
    UserEntity firstUser;

    @ManyToOne
    @JoinColumn(name = "second_user", referencedColumnName = "id", nullable = false)
    UserEntity secondUser;

}
