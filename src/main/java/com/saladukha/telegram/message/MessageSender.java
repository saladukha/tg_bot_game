package com.saladukha.telegram.message;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * Created by: Yauhen Saladukha
 * email: yauhensaladukha@gmail.com
 * Date: 10.06.2024
 */

@Component
public class MessageSender extends DefaultAbsSender {

    @SneakyThrows
    public Object sendMessage(SendMessage message) {
        return execute(message);
    }

    public MessageSender(@Value("${app.telegram.token}") String botToken) {
        super(new DefaultBotOptions(), botToken);
    }
}
