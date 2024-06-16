package com.saladukha.service;

import com.saladukha.entity.Card;
import com.saladukha.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by: Yauhen Saladukha
 * email: yauhensaladukha@gmail.com
 * Date: 16.06.2024
 */

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    private final WordService wordService;

    public Card save(List<String> answerAndTaboos) {
        var card = new Card();
        card.setAnswer(answerAndTaboos.get(0));
        card.setTaboos(answerAndTaboos.subList(1, answerAndTaboos.size()));
        card.setAllTaboos(
                answerAndTaboos.stream()
                        .flatMap(w -> wordService.getAllWordForm(w).stream())
                        .toList()
        );
        return cardRepository.save(card);
    }

    public boolean isTableEmpty() {
        return cardRepository.count() == 0;
    }
}
