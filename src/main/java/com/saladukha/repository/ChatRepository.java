package com.saladukha.repository;

import com.saladukha.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by: Yauhen Saladukha
 * email: yauhensaladukha@gmail.com
 * Date: 30.06.2024
 */

public interface ChatRepository extends JpaRepository<Chat, Long> {

    Chat findByTelegramChatId(Long telegramChatId);
}
