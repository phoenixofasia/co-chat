package com.cochat.sso.security.userdetail;

import com.cochat.sso.security.entity.ChatOAuth2User;
import com.cochat.sso.security.repository.OAuth2UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final OAuth2UserRepository repository;
    private final RedirectStrategy redirectStrategy;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        CustomAuthenticatedPrincipal principal = (CustomAuthenticatedPrincipal) authentication.getPrincipal();
        if (repository.findByEmail(principal.getEmail()).isEmpty()) {
            repository.save(new ChatOAuth2User(null, authentication.getName(), principal.getEmail(), principal.getFirstName(), principal.getLastName()));
        }
        redirectStrategy.sendRedirect(request, response, "/");
    }
}
