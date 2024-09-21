package com.example.mystudydiary.quiz;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuizService {

    @Autowired
    private RestTemplate restTemplate;

    private final String apiKey = ""; // API 키는 환경 변수나 설정 파일에서 관리하세요.

    public Map<String, Object> createQuiz(String message) {
        URI uri = UriComponentsBuilder
                .fromUriString("https://api.openai.com/v1/chat/completions")
                .build()
                .encode()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 요청 메시지 생성
        String prompt = String.format("%s\n다음 내용을 바탕으로 객관식으로 문제 10개를 4지선다로 정답과 함께 만들어줘 이 때 정답은 정답 : ~ 이런 형식으로 해주고, 문제는 ?로 끝나게 해줘", message);
        QuizDTO.Body body = new QuizDTO.Body("gpt-3.5-turbo", List.of(new QuizDTO.Message("user", prompt)));

        // API 요청 보내기
        HttpEntity<QuizDTO.Body> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, String.class);

        // 응답 본문 가져오기
        String responseBody = response.getBody();

        // 응답을 확인
        System.out.println("API 전체 응답: " + responseBody);

        // 문제 및 보기와 정답을 저장할 변수
        List<Map<String, Object>> questionsWithOptions = new ArrayList<>();
        List<String> options = new ArrayList<>();
        List<String> questions = new ArrayList<>();
        List<String> answers = new ArrayList<>();

        // JSON 응답 파싱
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(responseBody);

            // GPT 응답에서 텍스트 추출 -> choices의 message의 content부분을 뽑아서 String 형으로 저장
            String gptResponse = rootNode.path("choices").get(0).path("message").path("content").asText();

            // content 부분을 출력
            System.out.println("GPT 응답 내용: " + gptResponse);

            // 응답을 줄 단위로 나누기
            String[] lines = gptResponse.split("\n");

            // 응답 텍스트를 순회하며 문제, 보기, 정답을 추출
            for (String line : lines) {
                //line = line.trim(); // 공백 제거

                if (!line.isEmpty() && line.charAt(line.length() - 1) == '?') {
                    questions.add(line);
                }
                else if (line.contains("정답")) {
                    answers.add(line); // 정답을 추가
                }
                else if(!line.isEmpty()) {
                    options.add(line);
                }
                System.out.println("line : " + line);
            }

            // 디버깅용 출력
            System.out.println("문제 : " + questions);
            System.out.println("보기 : " + options);
            System.out.println("정답들: " + answers);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 결과를 맵에 담아 반환
        Map<String, Object> result = new HashMap<>();
        result.put("문제", questions);
        result.put("보기", options);
        result.put("정답", answers);

        return result;
    }
}
