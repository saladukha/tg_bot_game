package com.saladukha.telegram.command;

import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Created by: Yauhen Saladukha
 * email: yauhensaladukha@gmail.com
 * Date: 10.06.2024
 */

public interface BotCommand {

    void handle(Message message);

    boolean isMatch(String command);
}
