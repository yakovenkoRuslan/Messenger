package com.chess.dao.entity.messanger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(unique = true, name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private StatusEntity status;
}
