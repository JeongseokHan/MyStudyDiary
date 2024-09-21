package com.example.mystudydiary.quiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @PostMapping("/send")
    public ResponseEntity<Map<String, Object>> sendQuiz(@RequestBody QuizRequestDTO request) {
        Map<String, Object> quiz = quizService.createQuiz(request.getMessage());
        return ResponseEntity.ok().body(quiz);
    }
}
