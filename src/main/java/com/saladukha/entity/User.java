package com.saladukha.entity;

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
@Table(name = "user_entity")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private Long telegramId;
}
