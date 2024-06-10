package com.saladukha.repository;

import com.saladukha.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by: Yauhen Saladukha
 * email: yauhensaladukha@gmail.com
 * Date: 10.06.2024
 */

public interface UserRepository extends JpaRepository<User, Long> {

    User findByTelegramId(Long telegramId);
}
