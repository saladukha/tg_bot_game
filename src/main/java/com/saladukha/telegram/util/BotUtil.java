package com.saladukha.telegram.util;

import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Objects;

/**
 * Created by: Yauhen Saladukha
 * email: yauhensaladukha@gmail.com
 * Date: 11.06.2024
 */

public class BotUtil {

    public static boolean isPrivateMsg(Message message) {
        Long chatId = message.getChatId();
        Long telegramId = message.getFrom().getId();
        return Objects.equals(chatId, telegramId);
    }
}
