package com.example.demo.quiz.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@NoArgsConstructor
@Setter
@Getter
@Entity(name="questions")
@ToString
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Quiz quiz;
    private String questionText;
    private int score;
    @Transient
    private Collection<QuestionAnswer> answers;
    @Transient
    private Collection<StudentAnswer> studentAnswers;

    public Question(Quiz quiz,String questionText,int score){
        this.quiz = quiz;
        this.questionText = questionText;
        this.score = score;
        this.answers = null;
        studentAnswers = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id.equals(question.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getScoreToString() {
        return " (" + this.score + " p)";
    }
}
