package com.saladukha.repository;

import com.saladukha.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by: Yauhen Saladukha
 * email: yauhensaladukha@gmail.com
 * Date: 16.06.2024
 */

public interface CardRepository extends JpaRepository<Card, Long> {
}
