package com.example.demo.quiz.repository;

import com.example.demo.quiz.model.Quiz;
import com.example.demo.quiz.model.QuizSession;
import com.example.demo.quiz.model.StudentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface QuizSessionRepository extends JpaRepository<QuizSession, Long> {
    QuizSession getById(Long id);

    @Query("SELECT q FROM quiz_sessions q WHERE q.quiz=?1")
    Collection<QuizSession> getSessionsFromQuiz(Quiz quiz);
}
