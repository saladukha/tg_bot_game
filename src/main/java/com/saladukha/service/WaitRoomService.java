package com.saladukha.service;

import com.saladukha.entity.Chat;
import com.saladukha.entity.User;
import com.saladukha.entity.WaitRoom;
import com.saladukha.repository.ChatRepository;
import com.saladukha.repository.UserRepository;
import com.saladukha.repository.WaitRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by: Yauhen Saladukha
 * email: yauhensaladukha@gmail.com
 * Date: 30.06.2024
 */

@Service
@Transactional
@RequiredArgsConstructor
public class WaitRoomService {

    private final WaitRoomRepository waitRoomRepository;

    private final ChatRepository chatRepository;

    private final UserRepository userRepository;

    public WaitRoom save(Long telegramChatId, String hash) {
        var waitRoom = new WaitRoom();
        Chat chat = chatRepository.findByTelegramChatId(telegramChatId);
        waitRoom.setChat(chat);
        waitRoom.setHash(hash);
        return waitRoomRepository.save(waitRoom);
    }

    public WaitRoom join(String hash, Long telegramId) {
        WaitRoom waitRoom = waitRoomRepository.findByHash(hash);
        User user = userRepository.findByTelegramId(telegramId);
        waitRoom.getUsers().add(user);
        return waitRoom;
    }
}
