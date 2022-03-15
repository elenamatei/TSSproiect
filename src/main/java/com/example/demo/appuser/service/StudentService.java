package com.example.demo.appuser.service;

//import com.example.demo.appuser.AppUserRepository;
import com.example.demo.appuser.model.Class_;
import com.example.demo.appuser.model.Student;
import com.example.demo.appuser.repository.ClassRepository;
import com.example.demo.appuser.repository.StudentRepository;
import com.example.demo.appuser.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class StudentService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final ClassRepository classRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return studentRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(Student student) {
        boolean userExists = studentRepository
                .findByEmail(student.getEmail())
                .isPresent();

        if (userExists) {
            throw new IllegalStateException("email already taken by another student account");
        }

        userExists = teacherRepository
                .findByEmail(student.getEmail())
                .isPresent();

        if (userExists) {
            throw new IllegalStateException("email already taken by a teacher account");
        }


        String encodedPassword = bCryptPasswordEncoder
                .encode(student.getPassword());

        student.setPassword(encodedPassword);

        studentRepository.save(student);

        //return "Student Added:\n" + student.toString();
        return "login";
    }

    public Student getStudentById(Long id) {
        return studentRepository.getById(id);
    }

    public Student enrollStudentToClass(Long student_id, Long class_id) {
        Student student = studentRepository.getById(student_id);
        Class_ class_ = classRepository.getById(class_id);
        student.setClass_(class_);
        studentRepository.save(student);
        return student;
    }
}
