package com.cochat.sso.security.service;

import com.cochat.sso.security.entity.Verification;
import com.cochat.sso.security.repository.VerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VerificationService {

    private final VerificationRepository repository;

    public Optional<String> getVerificationIdByUsername(String username) {
        return repository.findByUsername(username).map(Verification::getId);
    }

    public String createVerification(String username) {
        return getVerificationIdByUsername(username).orElse(
                repository.save(new Verification(username)).getId()
        );
    }

    public Optional<String> getUsernameById(String id) {
        return repository.findById(id).map(Verification::getUsername);
    }
}
