package com.cochat.sso.security.validation;

import com.cochat.sso.security.service.GoogleRecaptchaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@RequiredArgsConstructor
public class RecaptchaValidator implements ConstraintValidator<Recaptcha, Object> {

    private final HttpServletRequest request;
    private final GoogleRecaptchaService recaptchaService;

    @Override
    public void initialize(Recaptcha constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String recaptchaResponse = request.getParameter("g-recaptcha-response");
        if(recaptchaResponse == null || recaptchaResponse.isEmpty()) {
            return false;
        }
        return recaptchaService.verify(request.getRemoteAddr(), recaptchaResponse);
    }
}
