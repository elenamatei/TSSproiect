package com.example.demo.appuser.repository;

import com.example.demo.appuser.model.Student;
import com.example.demo.appuser.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByEmail(String email);
    Teacher getById(Long id);
}
