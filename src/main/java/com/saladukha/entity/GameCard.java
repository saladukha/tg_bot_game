package com.saladukha.entity;

import com.saladukha.entity.enums.CardStatus;
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
public class GameCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Card card;

    @ManyToOne
    private Game game;

    @Enumerated(EnumType.STRING)
    private CardStatus status = CardStatus.IN_PROGRESS;
}
