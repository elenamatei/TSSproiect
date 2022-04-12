package com.example.demo.appuser.controller;

import com.example.demo.appuser.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(path="teachers")
@AllArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping(value="{id}")
    public String getTeacherById(@PathVariable long id) {
        try {
            return teacherService.getTeacherById(id).toString();
        } catch (EntityNotFoundException exception) {
            return "Teacher not found.";
        } catch (Exception exception) {
            return exception.toString();
        }
    }

    @PostMapping(value="{teacher_id}/enroll_to_class/{class_id}")
    public String enrollTeacherToClass(@PathVariable Long teacher_id, @PathVariable Long class_id) {
        try {
            return "Teacher enrolled:\n" + teacherService.enrollTeacherToClass(teacher_id, class_id).toString();
        } catch (EntityNotFoundException exception) {
            return "Teacher not found:" + "\n" + exception.toString();
        } catch (JpaObjectRetrievalFailureException exception) {
            return "Class not found:" + "\n" + exception.toString();
        } catch (Exception exception) {
            return exception.toString();
        }
    }
}
