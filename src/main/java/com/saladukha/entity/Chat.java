package com.saladukha.entity;

import com.saladukha.entity.enums.ChatBotStatus;
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
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Long telegramChatId;

    @Enumerated(EnumType.STRING)
    private ChatBotStatus chatBotStatus;
}
