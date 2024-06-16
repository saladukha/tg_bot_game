package com.saladukha.runner;

import com.saladukha.entity.Word;
import com.saladukha.service.WordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Order;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by: Yauhen Saladukha
 * email: yauhensaladukha@gmail.com
 * Date: 12.06.2024
 */

@Slf4j
@Order(1)
@Component
@RequiredArgsConstructor
public class DictionaryRunner implements ApplicationRunner {

    private final WordService wordService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (wordService.isTableEmpty()) {
            Resource resource = new ClassPathResource("/files/dictionary.txt");
            try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    insertWord(line);
                }
            } catch (IOException e) {
                log.error("Error reading file: " + e.getMessage());
            }
        }
    }

    private void insertWord(String line) {
        String[] words = line.split(",");
        Word initWord = wordService.save(words[0]);
        for (int i = 1; i < words.length; i++) {
            String word = words[i];
            wordService.save(word, initWord);
        }
    }
}
