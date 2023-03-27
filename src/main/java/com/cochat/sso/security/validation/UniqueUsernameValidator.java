package com.cochat.sso.security.validation;

import com.cochat.sso.security.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private UserRepository repository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return username != null && repository.findByUsernameIgnoreCase(username.trim()).isEmpty();
    }

}
