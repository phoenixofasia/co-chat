package com.cochat.sso.security.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RecaptchaValidator.class)
public @interface Recaptcha {
    String message() default  "Invalid captcha";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
