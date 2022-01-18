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
@Table(name = "status")
public class StatusEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "statusGenerator")
    @SequenceGenerator(name = "statusGenerator", initialValue = 1, allocationSize = 1)
    Long id;

    String name;
}
