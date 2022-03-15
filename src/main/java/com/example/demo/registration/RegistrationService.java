package com.example.demo.registration;

import com.example.demo.appuser.model.AppUserRole;
import com.example.demo.appuser.model.Student;
import com.example.demo.appuser.model.Teacher;
import com.example.demo.appuser.service.StudentService;
import com.example.demo.appuser.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.
                test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }

        if (request.getAppUserRole() == AppUserRole.STUDENT) {
            return studentService.signUpUser(
                new Student(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword()
                )
            );
        } else {
            return teacherService.signUpUser(
                new Teacher(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword()
                )
            );
        }
    }
}
