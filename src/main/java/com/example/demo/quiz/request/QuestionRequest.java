package com.example.demo.quiz.request;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class QuestionRequest {
    private final String questionText;
    private final String answer1;
    private final boolean isAnswer1Correct;
    private final String answer2;
    private final boolean isAnswer2Correct;
    private final String answer3;
    private final boolean isAnswer3Correct;
    private final String answer4;
    private final boolean isAnswer4Correct;
    private final int score;
}
