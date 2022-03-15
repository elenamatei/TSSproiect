package com.example.demo.appuser.repository;

import com.example.demo.appuser.model.Class_;
import com.example.demo.appuser.model.Teacher;
import com.example.demo.quiz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface ClassRepository extends JpaRepository<Class_, Long> {
    Class_ getById(Long id);

    @Query("SELECT c FROM classes c")
    Collection<Class_> getClassesByTeacher(Teacher teacher);
}
