package com.example.demo.appuser.model;

import com.example.demo.appuser.model.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@NoArgsConstructor
@Getter
@Setter
@Entity(name="teachers")
public class Teacher extends AppUser {
    @ManyToMany
    private Collection<Class_> classes;

    public Teacher(String firstName,
                   String lastName,
                   String email,
                   String password) {
        super(firstName, lastName, email, password);
        this.classes = null;
    }

    public Teacher(String email, String password) {
    }

    @Override
    public String toString() {
        if (this.classes == null) {
            return "Teacher{" +
                    super.toString() +
                    '}';
        } else {
            return "Teacher{" +
                    super.toString() + ',' +
                    //"classes=" + classes.toString() +
                    '}';
        }

    }
}
