package com.chess.dao.entity.messanger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class FriendEntity implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "first_user", referencedColumnName = "id", nullable = false)
    UserEntity firstUser;

    @Id
    @ManyToOne
    @JoinColumn(name = "second_user", referencedColumnName = "id", nullable = false)
    UserEntity secondUser;

}
