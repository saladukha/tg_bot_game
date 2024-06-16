package com.saladukha.repository;

import com.saladukha.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by: Yauhen Saladukha
 * email: yauhensaladukha@gmail.com
 * Date: 12.06.2024
 */

public interface WordRepository extends JpaRepository<Word, Long> {
    List<Word> findByWord(String word);
}
