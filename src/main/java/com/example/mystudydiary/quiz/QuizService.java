package com.example.mystudydiary.quiz;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    private final RestTemplate restTemplate;
    private final String apiKey = "YOUR_API_KEY";

    public QuizService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> sendMessageToOpenAi(String message) {

        URI uri = UriComponentsBuilder
                .fromUriString("https://api.openai.com/v1/chat/completions")
                .build()
                .encode()
                .toUri();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + apiKey);
        httpHeaders.add("Content-Type", "application/json");

        List<QuizDTO.Message> messages = new ArrayList<>();
        messages.add(new QuizDTO("user", message));

        QuizDTO body = new QuizDTO("gpt-3.5-turbo", messages);

        RequestEntity<QuizDTO> request = new RequestEntity<>(body, httpHeaders, HttpMethod.POST, uri);

        return restTemplate.exchange(request, String.class);
    }
}
