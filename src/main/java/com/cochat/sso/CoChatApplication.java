package com.cochat.sso;

import com.cochat.sso.security.entity.ChatUser;
import com.cochat.sso.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
public class CoChatApplication {

    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(CoChatApplication.class, args);
    }

    // TODO : Remove this code once we have database
    @EventListener(ApplicationReadyEvent.class)
    public void initializeUserData() {
        String password = new BCryptPasswordEncoder().encode("pa55word");
        userRepository.save(new ChatUser("asolanke", password, "Amol", "Solanke", "asolanke@gmail.com", true, true, Set.of("ADMIN")));
        userRepository.save(new ChatUser("asolanke1", password, "Amol", "Solanke", "asolanke1@gmail.com", true, true, Set.of("USER")));
        userRepository.save(new ChatUser("asolanke2", password, "Amol", "Solanke", "asolanke2@gmail.com", true, true, Set.of("SUPPORT")));
    }
}
