package com.saladukha.telegram.message;

import com.saladukha.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: Yauhen Saladukha
 * email: yauhensaladukha@gmail.com
 * Date: 10.06.2024
 */

@Component
public class MessageBuilder {

    @Value("${app.telegram.username}")
    private String username;

    public SendMessage buildWelcomeMsg(Long chatId, User user) {
        SendMessage message = new SendMessage();
        String txt = """
        Welcome %s! \uD83D\uDC4B
        This bot lets you play 🎮 the Taboo game with your friends.
        Here's how:
        1. <a href="https://t.me/%s?startgroup">Add this bot</a> to a chat with other players.
        2. In that chat, send the /play command to start a new game.
        
        Use the /rules command to view the game rules
        """.formatted(user.getFirstName(), username);
        message.setText(txt);
        message.setChatId(chatId);
        message.setParseMode(ParseMode.HTML);
        return message;
    }

    public SendMessage buildCommandNotFoundMsg(Message message){
        var sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("You tried to perform an unknown command!");
        return sendMessage;
    }

    public SendMessage buildRulesMsg(Long chatId) {
        var message = new SendMessage();
        String txt = """
                There are two roles: \uD83D\uDC42Guesser and \uD83D\uDDE3 Explainer.
                
                The Explainer receives a card in a private chat that includes a word for others to guess and a list of taboo words \uD83D\uDEAB
                ✅ The objective is to assist other players in guessing the word while avoiding the use of any of the taboo words
                
                If any of the Guessers correctly guess a word, both the Explainer and Guesser earn a score\uD83C\uDFAF
                
                The Explainer role changes every five minutes ⌛, and the game continues until everyone has taken a turn explaining.     
                """;
        message.setText(txt);
        message.setChatId(chatId);
        return message;
    }

    public SendMessage buildTextMsg(Long chatId, String text) {
        var message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        message.setParseMode(ParseMode.HTML);
        return message;
    }

    public SendMessage buildAwaitingMsg(Long chatId, String hash) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("To join the game press the \"start\" command\nwithin a private chat with the bot.\n\nThe game starts in two minutes");

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Join the game");
        button.setUrl(buildJoinToGameLink(hash));
        row1.add(button);
        inlineKeyboardMarkup.setKeyboard(List.of(row1));
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }

    private String buildJoinToGameLink(String hash) {
        return "https://t.me/%s?start=%s".formatted(username, hash);
    }
}
