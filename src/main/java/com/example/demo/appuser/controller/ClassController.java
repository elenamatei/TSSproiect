package com.example.demo.appuser.controller;

import com.example.demo.appuser.model.Class_;
import com.example.demo.appuser.request.ClassRequest;
import com.example.demo.appuser.service.ClassService;
import com.example.demo.registration.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(path="classes")
@AllArgsConstructor
public class ClassController {
    private final ClassService classService;

    @PostMapping(value="/add")
    public String addClass(@RequestBody ClassRequest request) {// @ModelAttribute
        return classService.addClass(request);
    }

    @GetMapping(value="{id}")
    public String getClassById(@PathVariable long id) {
        try {
            return classService.getClassById(id).toString();
        } catch (EntityNotFoundException exception) {
            return "Class not found.";
        } catch (Exception exception) {
            return "Something wrong happened" + exception.toString();
        }
    }

}
