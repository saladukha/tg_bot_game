package com.saladukha.telegram.message;

import com.saladukha.entity.User;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Created by: Yauhen Saladukha
 * email: yauhensaladukha@gmail.com
 * Date: 10.06.2024
 */

@Component
public class MessageBuilder {

    public SendMessage buildWelcomeMsg(Long chatId, User user) {
        SendMessage message = new SendMessage();
        String txt = """
        Welcome %s! \uD83D\uDC4B
        This bot lets you play 🎮 the Taboo game with your friends.
        Here's how:
        1. Add this bot to a chat with other players.
        2. In that chat, send the /play command to start a new game.
        
        Use the /rules command to view the game rules
        """.formatted(user.getFirstName());
        message.setText(txt);
        message.setChatId(chatId);
        return message;
    }

    public SendMessage buildCommandNotFoundMsg(Message message){
        var sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("You tried to perform an unknown command!");
        return sendMessage;
    }
}
