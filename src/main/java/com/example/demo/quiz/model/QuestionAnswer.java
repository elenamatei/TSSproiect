package com.example.demo.quiz.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity(name="answers")
@ToString
public class QuestionAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Question question;
    private String text;
    private boolean isCorrect;


    public QuestionAnswer(Question question,String answerText, boolean isCorrect){
        this.question = question;
        this.text = answerText;
        this.isCorrect = isCorrect;
    }
}
