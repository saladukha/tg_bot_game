package com.saladukha.telegram.command;

import com.saladukha.entity.User;
import com.saladukha.service.UserService;
import com.saladukha.telegram.message.MessageBuilder;
import com.saladukha.telegram.message.MessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Created by: Yauhen Saladukha
 * email: yauhensaladukha@gmail.com
 * Date: 10.06.2024
 */

@Component
@RequiredArgsConstructor
public class StartCommand implements BotCommand {

    private static final String START_COMMAND = "start";

    private final MessageSender messageSender;

    private final MessageBuilder messageBuilder;

    private final UserService userService;

    @Override
    public void handle(Message message) {
        User user = convert(message.getFrom());
        user = userService.save(user);
        SendMessage response = messageBuilder.buildWelcomeMsg(message.getChatId(), user);
        messageSender.sendMessage(response);
    }

    @Override
    public boolean isMatch(String command) {
        return START_COMMAND.equals(command);
    }

    private User convert(org.telegram.telegrambots.meta.api.objects.User tgUser) {
        var user = new User();
        user.setFirstName(tgUser.getFirstName());
        user.setLastName(tgUser.getLastName());
        user.setUsername(tgUser.getUserName());
        user.setTelegramId(tgUser.getId());
        return user;
    }
}
