package com.chess.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class FriendEntity implements Serializable {

    @Id
    @ManyToOne
    UserEntity firstUser;

    @Id
    @ManyToOne()
    UserEntity secondUser;

}
