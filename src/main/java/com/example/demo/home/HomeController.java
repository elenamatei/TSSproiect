package com.example.demo.home;

import com.example.demo.appuser.model.AppUser;
import com.example.demo.appuser.model.Class_;
import com.example.demo.appuser.model.Student;
import com.example.demo.appuser.model.Teacher;
import com.example.demo.appuser.service.ClassService;
import com.example.demo.quiz.model.Quiz;
import com.example.demo.quiz.service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;


@Controller
@RequestMapping(path = "")
@AllArgsConstructor
public class HomeController {
    private final QuizService quizService;
    private final ClassService classService;
    @GetMapping("")
    public String viewHomePage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof Teacher) {
            Collection<Quiz> quizzes = quizService.getQuizzesByAuthorId(((Teacher) auth.getPrincipal()).getId());
            Collection<Class_> classes = classService.getClassesByTeacherId(((Teacher) auth.getPrincipal()).getId());
            model.addAttribute("classes", classes);
            model.addAttribute("role", "teacher");
            model.addAttribute("quizzes", quizzes);
        } else {
            Collection<Quiz> quizzes = quizService.getQuizzesByStudentId(((Student) auth.getPrincipal()).getId());
            model.addAttribute("role", "student");
            model.addAttribute("quizzes", quizzes);
        }
        model.addAttribute("user", auth.getPrincipal());
        return "home";
    }
}
