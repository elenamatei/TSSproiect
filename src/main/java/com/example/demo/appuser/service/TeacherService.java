package com.example.demo.appuser.service;

//import com.example.demo.appuser.AppUserRepository;
import com.example.demo.appuser.model.Class_;
import com.example.demo.appuser.model.Student;
import com.example.demo.appuser.model.Teacher;
import com.example.demo.appuser.repository.ClassRepository;
import com.example.demo.appuser.repository.StudentRepository;
import com.example.demo.appuser.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeacherService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return teacherRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(Teacher teacher) {
        boolean userExists = teacherRepository
                .findByEmail(teacher.getEmail())
                .isPresent();

        if (userExists) {
            throw new IllegalStateException("email already taken by another teacher account");
        }

        userExists = studentRepository
                .findByEmail(teacher.getEmail())
                .isPresent();

        if (userExists) {
            throw new IllegalStateException("email already taken by a student account");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(teacher.getPassword());

        teacher.setPassword(encodedPassword);

        teacherRepository.save(teacher);

        //return "Teacher Added:\n" + teacher.toString();
        return "login";

    }

    public Teacher getTeacherById(Long id) {
        return teacherRepository.getById(id);
    }

    public Teacher enrollTeacherToClass(Long teacher_id, Long class_id) throws Exception{
        Teacher teacher = teacherRepository.getById(teacher_id);
        Class_ class_ = classRepository.getById(class_id);
        if (!teacher.getClasses().contains(class_)) {
            teacher.getClasses().add(class_);
            teacherRepository.save(teacher);
            return teacher;
        } else {
            throw new Exception("Teacher is already enrolled to this class.");
        }

    }
}
