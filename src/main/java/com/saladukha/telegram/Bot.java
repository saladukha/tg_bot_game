package com.saladukha.telegram;

import com.saladukha.telegram.command.BotCommandHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
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
    private final BotCommandHandler commandHandler;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            String text = message.getText();
            if (text == null) return;
            if (text.startsWith("/")) {
                commandHandler.handleCommand(message);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    public Bot(@Value("${app.telegram.username}") String username,
               @Value("${app.telegram.token}") String botToken,
               BotCommandHandler commandHandler) {
        super(botToken);
        this.username = username;
        this.commandHandler = commandHandler;
    }
}
