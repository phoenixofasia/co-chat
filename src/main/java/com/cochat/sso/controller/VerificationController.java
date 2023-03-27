package com.cochat.sso.controller;

import com.cochat.sso.security.entity.ChatUser;
import com.cochat.sso.security.repository.UserRepository;
import com.cochat.sso.security.service.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class VerificationController {

    private final VerificationService verificationService;
    private final UserRepository userRepository;

    @GetMapping("/verify/email")
    public String verifyEmail(@RequestParam String id) {
        String username = verificationService.getUsernameById(id)
                .orElseThrow(() ->
                        new RuntimeException("Verification id: " + id + " not found")
                );
        ChatUser user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with username: " + username + " not found !")
                );
        user.setVerified(true);
        userRepository.save(user);
        return "redirect:/login";
    }
}
