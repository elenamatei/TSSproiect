package com.example.demo.quiz.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name="students_answers")
@ToString
public class StudentAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private QuestionAnswer answer;
    @ManyToOne
    private QuizSession quizSession;
    private boolean isCorrect;

    public StudentAnswer(QuestionAnswer answer, QuizSession quizSession, boolean isCorrect) {
        this.answer = answer;
        this.quizSession = quizSession;
        this.isCorrect = isCorrect;
    }
}
