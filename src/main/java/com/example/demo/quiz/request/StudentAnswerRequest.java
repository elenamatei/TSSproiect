package com.example.demo.quiz.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class StudentAnswerRequest {
    private final boolean isAnswerCorrect0;
    private final boolean isAnswerCorrect1;
    private final boolean isAnswerCorrect2;
    private final boolean isAnswerCorrect3;
}
