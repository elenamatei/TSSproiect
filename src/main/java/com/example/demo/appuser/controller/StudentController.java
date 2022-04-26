package com.example.demo.appuser.controller;

import com.example.demo.appuser.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(path="students")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping(value="{id}")
    public String getStudentById(@PathVariable long id) {
        try {
            return studentService.getStudentById(id).toString();
        } catch (EntityNotFoundException exception) {
            return "Student not found.";
        } catch (Exception exception) {
            return exception.toString();
        }
    }

    @PostMapping(value="{student_id}/enroll_to_class/{class_id}")
    public String enrollStudentToClass(@PathVariable Long student_id, @PathVariable Long class_id) {
        try {
            return "Student enrolled:\n" + studentService.enrollStudentToClass(student_id, class_id).toString();
        } catch (EntityNotFoundException exception) {
            return "Student not found:" + "\n" + exception.toString();
        } catch (DataIntegrityViolationException exception) {
            return "Class not found:" + "\n" + exception.toString();
        }
    }
}
