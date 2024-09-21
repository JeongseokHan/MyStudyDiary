package com.example.mystudydiary.quiz;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class QuizDTO {
    @AllArgsConstructor
    @Data
    public static class Body {
        private String model;
        private List<Message> messages;
    }

    @AllArgsConstructor
    @Data
    public static class Message {
        private String role;
        private String content;
    }
}
