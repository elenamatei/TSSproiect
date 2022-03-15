package com.example.demo.quiz.repository;

import com.example.demo.appuser.model.Student;
import com.example.demo.appuser.model.Teacher;
import com.example.demo.quiz.model.Question;
import com.example.demo.quiz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Quiz getById(Long id);

    @Query("SELECT q FROM quizzes q WHERE q.author=?1")
    Collection<Quiz> getQuizzesByAuthor(Teacher author);

    @Query("SELECT q FROM quizzes q JOIN q.studentsEnrolled s WHERE s=?1")
    Collection<Quiz> getQuizzesByStudent(Student student);
}
