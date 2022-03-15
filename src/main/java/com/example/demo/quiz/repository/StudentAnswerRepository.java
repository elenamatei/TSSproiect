package com.example.demo.quiz.repository;

import com.example.demo.quiz.model.Question;
import com.example.demo.quiz.model.QuestionAnswer;
import com.example.demo.quiz.model.QuizSession;
import com.example.demo.quiz.model.StudentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Collection;

public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Long> {
    StudentAnswer getById(Long id);

    @Query("SELECT a FROM students_answers a WHERE a.quizSession=?1 ")
    Collection<StudentAnswer> getAnswersFromQuizSession(QuizSession quizSession);

    @Transactional
    @Modifying
    @Query("UPDATE students_answers a SET a.isCorrect=?1 where a.id=?2")
    void updateStudentAnswer(boolean isCorrect, long id);
}
