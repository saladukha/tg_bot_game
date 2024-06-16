package com.saladukha.runner;

import com.saladukha.service.CardService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Order;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * Created by: Yauhen Saladukha
 * email: yauhensaladukha@gmail.com
 * Date: 16.06.2024
 */

@Order(2)
@Component
@RequiredArgsConstructor
public class TabooCardRunner implements ApplicationRunner {

    private final CardService cardService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (cardService.isTableEmpty()) {
            ClassPathResource resource = new ClassPathResource("/files/taboo-cards.txt");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    List<String> answerAndTaboos = Arrays.stream(line.split(",")).toList();
                    cardService.save(answerAndTaboos);
                }
            }
        }
    }
}
