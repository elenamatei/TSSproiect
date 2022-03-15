package com.example.demo.appuser.model;

import com.example.demo.appuser.model.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity(name="students")
public class Student extends AppUser {
    @ManyToOne
    private Class_ class_;

    public Student(String firstName,
                   String lastName,
                   String email,
                   String password) {
        super(firstName, lastName, email, password);
        this.class_ = null;
    }

    public Student(String email, String password) {
    }

    @Override
    public String toString() {
        return "Student{" +
                super.toString() + ',' +
                "class_=" + class_ +
                '}';
    }
}
