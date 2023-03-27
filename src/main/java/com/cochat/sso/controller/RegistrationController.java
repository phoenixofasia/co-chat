package com.cochat.sso.controller;

import com.cochat.sso.security.event.UserRegistrationEvent;
import com.cochat.sso.security.model.UserDto;
import com.cochat.sso.security.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid UserDto user, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        userService.create(user);
        eventPublisher.publishEvent(new UserRegistrationEvent(user));
        return "redirect:register?success";
    }
}
