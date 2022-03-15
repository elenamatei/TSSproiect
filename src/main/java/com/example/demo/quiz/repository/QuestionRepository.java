package com.example.demo.quiz.repository;

import com.example.demo.appuser.model.Class_;
import com.example.demo.appuser.model.Student;
import com.example.demo.quiz.model.Question;
import com.example.demo.quiz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question getById(Long id);

    @Query("SELECT q FROM questions q WHERE q.quiz=?1")
    Collection<Question> getQuestionFromQuiz(Quiz quiz);
}