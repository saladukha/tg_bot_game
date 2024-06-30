package com.saladukha.service;

import com.saladukha.entity.Chat;
import com.saladukha.entity.enums.ChatBotStatus;
import com.saladukha.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.ChatMemberUpdated;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;

/**
 * Created by: Yauhen Saladukha
 * email: yauhensaladukha@gmail.com
 * Date: 30.06.2024
 */

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    public Chat save(Chat chat) {
        Chat existedChat = chatRepository.findByTelegramChatId(chat.getTelegramChatId());
        if (existedChat == null) {
            return chatRepository.save(chat);
        } else {
            existedChat.setChatBotStatus(chat.getChatBotStatus());
            return chatRepository.save(existedChat);
        }
    }

    public Chat convert(ChatMemberUpdated chatMemberUpdated) {
        var chat = new Chat();
        chat.setTelegramChatId(chatMemberUpdated.getChat().getId());
        chat.setTitle(chatMemberUpdated.getChat().getTitle());
        ChatMember newChatMember = chatMemberUpdated.getNewChatMember();
        if (newChatMember != null) {
            chat.setChatBotStatus(convertStatus(newChatMember.getStatus()));
        }
        return chat;
    }

    private ChatBotStatus convertStatus(String status) {
        return switch (status) {
            case "administrator" -> ChatBotStatus.ADMIN;
            case "kicked" -> ChatBotStatus.KICKED;
            default -> ChatBotStatus.MEMBER;
        };
    }
}
