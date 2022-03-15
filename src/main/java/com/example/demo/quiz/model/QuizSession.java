package com.example.demo.quiz.model;

import com.example.demo.appuser.model.Student;
import com.example.demo.quiz.service.QuizService;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "quiz_sessions")
@ToString
public class QuizSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private Quiz quiz;
    @ManyToOne
    private Student student;
    @Transient
    private int score;

    public QuizSession(Quiz quiz, Student student) {
        this.quiz = quiz;
        this.student = student;
        this.score = 0;
    }
}

