package com.cochat.sso.security.validation;


import com.cochat.sso.security.model.UserDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordConfirmedValidator implements ConstraintValidator<PasswordConfirmed, Object> {

    @Override
    public boolean isValid(Object user, ConstraintValidatorContext context) {
        String password = ((UserDto) user).getPassword();
        String confirmedPassword = ((UserDto) user).getConfirmPassword();
        return password.equals(confirmedPassword);
    }

}
