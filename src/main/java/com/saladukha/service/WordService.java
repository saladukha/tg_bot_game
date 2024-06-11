package com.saladukha.service;

import com.saladukha.entity.Word;
import com.saladukha.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by: Yauhen Saladukha
 * email: yauhensaladukha@gmail.com
 * Date: 12.06.2024
 */

@Service
@RequiredArgsConstructor
public class WordService {

    private final WordRepository wordRepository;

    public boolean isTableEmpty() {
        return wordRepository.count() == 0;
    }

    public Word save(String word) {
        return wordRepository.save(build(word));
    }

    public Word save (String word, Word initWord) {
        Word entity = build(word);
        entity.setInitWord(initWord);
        return wordRepository.save(entity);
    }

    private Word build(String word) {
        var entity = new Word();
        entity.setWord(word);
        return entity;
    }
}
