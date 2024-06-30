package com.saladukha.repository;

import com.saladukha.entity.WaitRoom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by: Yauhen Saladukha
 * email: yauhensaladukha@gmail.com
 * Date: 30.06.2024
 */

public interface WaitRoomRepository extends JpaRepository<WaitRoom, Long> {
    WaitRoom findByHash(String hash);
}
