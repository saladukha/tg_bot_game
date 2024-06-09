package com.saladukha.entity;

import com.saladukha.entity.enums.GameRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by: Yauhen Saladukha
 * email: yauhensaladukha@gmail.com
 * Date: 09.06.2024
 */

@Getter
@Setter
@Entity
public class UserGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Game game;

    @Enumerated(EnumType.STRING)
    private GameRole gameRole;

    private int score;
    private boolean explained;
}
