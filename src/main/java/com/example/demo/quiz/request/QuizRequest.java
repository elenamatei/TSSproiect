package com.example.demo.quiz.request;

import com.example.demo.appuser.model.Teacher;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Collection;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class QuizRequest {
    private final Long authorId;
    private final String name;
    private final Long classId;
}