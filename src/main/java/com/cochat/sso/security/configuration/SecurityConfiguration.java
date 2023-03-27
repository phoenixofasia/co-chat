package com.cochat.sso.security.configuration;

import com.cochat.sso.security.service.CustomOidcUserService;
import com.cochat.sso.security.userdetail.OAuth2AuthenticationSuccessHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@AllArgsConstructor
public class SecurityConfiguration {

    private final UserDetailsService usersService;
    private final BCryptPasswordEncoder encoder;
    private final PersistentTokenRepository persistentTokenRepository;
    private final OAuth2AuthenticationSuccessHandler oauth2SuccessHandler;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/css/**", "/webjars/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz ->
        {
            try {
                authz.requestMatchers("/login", "/register", "/verify/email").permitAll()
                        .and().authorizeHttpRequests().requestMatchers("/").hasAnyAuthority("USER", "ADMIN", "OIDC_USER")
                        .and().formLogin().defaultSuccessUrl("/")
                        .and().oauth2Login()
                        .userInfoEndpoint().oidcUserService(new CustomOidcUserService())
                        .and().successHandler(oauth2SuccessHandler)
                        // TODO : create a strong key and store it in secret store
                        .and().rememberMe().key("remember-me-key").tokenRepository(persistentTokenRepository)
                        .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login")
                        .deleteCookies("remember-me")
                        .and().authorizeHttpRequests().anyRequest().denyAll();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        // TODO : remove these two setting once we have database, these are required for connecting to h2 console
        http.csrf().disable();
        http.headers().frameOptions().disable();
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(usersService);
        authenticationProvider.setPasswordEncoder(encoder);
        return authenticationProvider;
    }
}
