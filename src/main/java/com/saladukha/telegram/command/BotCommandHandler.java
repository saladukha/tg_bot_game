package com.saladukha.telegram.command;

import com.saladukha.telegram.message.MessageBuilder;
import com.saladukha.telegram.message.MessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

/**
 * Created by: Yauhen Saladukha
 * email: yauhensaladukha@gmail.com
 * Date: 10.06.2024
 */

@Component
@RequiredArgsConstructor
public class BotCommandHandler {
    private final List<BotCommand> commands;

    private final MessageSender messageSender;

    private final MessageBuilder messageBuilder;

    public void handleCommand(Message message) {
        BotCommand botCommand = commands.stream()
                .filter(c -> c.isMatch(extractCommand(message)))
                .findFirst()
                .orElse(null);
        if (botCommand == null) {
            SendMessage response = messageBuilder.buildCommandNotFoundMsg(message);
            messageSender.sendMessage(response);
        } else {
            botCommand.handle(message);
        }
    }

    private String extractCommand(Message message) {
        String text = message.getText();
        if (text.startsWith("/")) {
            return text.substring(1);
        }
        throw new IllegalArgumentException("Could not extract a command from text:" + text);
    }
}
