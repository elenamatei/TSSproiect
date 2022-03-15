package com.example.demo.registration;

import com.example.demo.appuser.model.AppUserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "register")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @GetMapping
    public ModelAndView registerPage(Model model) {
        model.addAttribute("user", new RegistrationRequest("", "", "", "", AppUserRole.STUDENT));
        return new ModelAndView("register");
    }

    @PostMapping
    public String register(@ModelAttribute RegistrationRequest request) {
        return registrationService.register(request);
    }

}
