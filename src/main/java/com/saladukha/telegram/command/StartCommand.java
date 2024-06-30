package com.saladukha.telegram.command;

import com.saladukha.entity.User;
import com.saladukha.entity.WaitRoom;
import com.saladukha.service.UserService;
import com.saladukha.service.WaitRoomService;
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

    private final WaitRoomService waitRoomService;

    @Override
    public void handle(Message message) {
        Long chatId = message.getChatId();
        User user = convert(message.getFrom());
        user = userService.save(user);
        String hash = extractHash(message.getText());
        if (hash != null) {
            WaitRoom waitRoom = waitRoomService.join(hash, message.getFrom().getId());
            String txt = "You've joined the game in the <b>%s</b> group".formatted(waitRoom.getChat().getTitle());
            SendMessage joinMessage = messageBuilder.buildTextMsg(chatId, txt);
            messageSender.sendMessage(joinMessage);
        } else {
            SendMessage response = messageBuilder.buildWelcomeMsg(message.getChatId(), user);
            messageSender.sendMessage(response);
        }
    }

    @Override
    public boolean isMatch(String command) {
        return command.startsWith(START_COMMAND);
    }

    private User convert(org.telegram.telegrambots.meta.api.objects.User tgUser) {
        var user = new User();
        user.setFirstName(tgUser.getFirstName());
        user.setLastName(tgUser.getLastName());
        user.setUsername(tgUser.getUserName());
        user.setTelegramId(tgUser.getId());
        return user;
    }

    private String extractHash(String text) {
        String[] split = text.split(" ");
        if (split.length > 1) {
            return split[1];
        }
        return null;
    }
}
