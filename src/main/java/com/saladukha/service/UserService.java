package com.saladukha.service;

import com.saladukha.entity.User;
import com.saladukha.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by: Yauhen Saladukha
 * email: yauhensaladukha@gmail.com
 * Date: 10.06.2024
 */

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(User user) {
        User existedUser = userRepository.findByTelegramId(user.getTelegramId());
        if (existedUser == null) {
            existedUser = userRepository.save(user);
        }
        return existedUser;
    }
}
