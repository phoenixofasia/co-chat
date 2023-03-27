package com.cochat.sso.security.validation;

import com.cochat.sso.security.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private UserRepository repository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email != null && repository.findByEmailIgnoreCase(email.trim()).isEmpty();
    }

}
