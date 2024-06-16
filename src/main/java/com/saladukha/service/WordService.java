package com.saladukha.service;

import com.saladukha.entity.Word;
import com.saladukha.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by: Yauhen Saladukha
 * email: yauhensaladukha@gmail.com
 * Date: 12.06.2024
 */

@Service
@Transactional
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

    public List<String> getAllWordForm(String word) {
        return getOrCreate(word).stream()
                .map(w -> w.getInitWord() == null ? w : w.getInitWord())
                .flatMap(w -> Stream.concat(Stream.of(w), w.getForms().stream()))
                .map(Word::getWord)
                .toList();
    }

    public List<Word> getOrCreate(String word) {
        List<Word> wordsList = wordRepository.findByWord(word);
        if (wordsList.isEmpty()) {
            return List.of(save(word));
        }
        return wordsList;
    }
}
