package com.example.demo.appuser.repository;

import com.example.demo.appuser.model.Class_;
import com.example.demo.appuser.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
    Student getById(Long id);

    @Query("SELECT s FROM students s WHERE s.class_=?1")
    Collection<Student> getAllStudentsByClassId(Class_ class_);
}
