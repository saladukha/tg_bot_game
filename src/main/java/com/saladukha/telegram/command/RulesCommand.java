package com.saladukha.telegram.command;

import com.saladukha.telegram.message.MessageBuilder;
import com.saladukha.telegram.message.MessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Created by: Yauhen Saladukha
 * email: yauhensaladukha@gmail.com
 * Date: 11.06.2024
 */

@Component
@RequiredArgsConstructor
public class RulesCommand implements BotCommand {

    private static final String RULES_COMMAND = "rules";

    private final MessageSender messageSender;

    private final MessageBuilder messageBuilder;

    @Override
    public void handle(Message message) {
        SendMessage response = messageBuilder.buildRulesMsg(message.getChatId());
        messageSender.sendMessage(response);
    }

    @Override
    public boolean isMatch(String command) {
        return RULES_COMMAND.equals(command);
    }
}
