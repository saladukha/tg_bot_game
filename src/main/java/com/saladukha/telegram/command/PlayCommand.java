package com.saladukha.telegram.command;

import com.saladukha.telegram.message.MessageBuilder;
import com.saladukha.telegram.message.MessageSender;
import com.saladukha.telegram.util.BotUtil;
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
public class PlayCommand implements BotCommand {

    private static final String PLAY_COMMAND = "play";

    private static final String NOT_A_GROUP_CHAT = "The game should take place in a group chat rather then in a private chat!";

    private final MessageSender messageSender;

    private final MessageBuilder messageBuilder;

    @Override
    public void handle(Message message) {
        if (BotUtil.isPrivateMsg(message)) {
            SendMessage response = messageBuilder.buildTextMsg(message.getChatId(), NOT_A_GROUP_CHAT);
            messageSender.sendMessage(response);
        }
    }

    @Override
    public boolean isMatch(String command) {
        return PLAY_COMMAND.equals(command);
    }
}
