package com.saladukha.entity;

import com.saladukha.entity.enums.GameStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by: Yauhen Saladukha
 * email: yauhensaladukha@gmail.com
 * Date: 09.06.2024
 */

@Getter
@Setter
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private GameStatus status = GameStatus.IN_PROGRESS;

    @ManyToOne
    private Chat chat;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<UserGame> users;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<GameCard> cards;

    public void addUserGame(UserGame userGame) {
        userGame.setGame(this);
        users.add(userGame);
    }

    public void addGameCard(GameCard gameCard) {
        gameCard.setGame(this);
        cards.add(gameCard);
    }
}
