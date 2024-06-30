package com.saladukha.telegram;

import com.saladukha.entity.Chat;
import com.saladukha.entity.enums.ChatBotStatus;
import com.saladukha.service.ChatService;
import com.saladukha.telegram.command.BotCommandHandler;
import com.saladukha.telegram.message.MessageBuilder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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
    private final MessageBuilder messageBuilder;
    private final ChatService chatService;

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            String text = message.getText();
            if (text == null) return;
            if (text.startsWith("/")) {
                commandHandler.handleCommand(message);
            }
        } else if (update.hasMyChatMember()) {
            Chat chat = chatService.convert(update.getMyChatMember());
            chat = chatService.save(chat);
            if (ChatBotStatus.ADMIN == chat.getChatBotStatus()){
                SendMessage message = messageBuilder.buildTextMsg(chat.getTelegramChatId(), "Hello there!\nLet's play the taboo game");
                execute(message);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    public Bot(@Value("${app.telegram.username}") String username,
               @Value("${app.telegram.token}") String botToken,
               BotCommandHandler commandHandler,
               MessageBuilder messageBuilder,
               ChatService chatService) {
        super(botToken);
        this.username = username;
        this.commandHandler = commandHandler;
        this.messageBuilder = messageBuilder;
        this.chatService = chatService;
    }
}
