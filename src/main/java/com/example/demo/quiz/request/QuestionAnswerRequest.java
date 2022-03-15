package com.example.demo.quiz.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Collection;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class QuestionAnswerRequest {
    private final String text;
    private final boolean isTrue;
}
