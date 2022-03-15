package com.example.demo.login;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@AllArgsConstructor
@RequestMapping(path = "login")
public class LoginController {

    @GetMapping
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }
}
