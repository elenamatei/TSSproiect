package com.example.demo.quiz.model;


import com.example.demo.appuser.model.Student;
import com.example.demo.appuser.model.Teacher;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Collection;


import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "quizzes")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private Teacher author;
    private String name;
    @ManyToMany
    private Collection<Student> studentsEnrolled;
    private boolean published;
    @Transient
    private Collection<Question> questions;


    public Quiz(Teacher author, String name) {
        this.author = author;
        this.name = name;
        this.studentsEnrolled = null;
        this.published = false;
        this.questions = null;
    }

    @Override
    public String toString() {
        if (this.studentsEnrolled != null) {
            return "Quiz{" +
                    "id=" + id +
                    ", author=" + author +
                    ", name='" + name + '\'' +
                    ", published='" + published + '\'' +
                    ", studentsEnrolled=" + studentsEnrolled +
                    '}';
        } else {
            return "Quiz{" +
                    "id=" + id +
                    ", author=" + author +
                    ", name='" + name + '\'' +
                    ", published='" + published + '\'' +
                    '}';
        }
    }
}
