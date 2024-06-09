package com.saladukha.telegram;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Created by: Yauhen Saladukha
 * email: yauhensaladukha@gmail.com
 * Date: 01.06.2024
 */

@Slf4j
@Component
public class Bot extends TelegramLongPollingBot {

    private final String username;

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public String getBotUsername() {
        return username;
    }

    public Bot(@Value("${app.telegram.username}") String username,
               @Value("${app.telegram.token}") String botToken) {
        super(botToken);
        this.username = username;
    }
}
