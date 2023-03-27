package com.cochat.sso.security.model;

import com.cochat.sso.security.validation.PasswordConfirmed;
import com.cochat.sso.security.validation.PasswordPolicy;
import com.cochat.sso.security.validation.Recaptcha;
import com.cochat.sso.security.validation.UniqueEmail;
import com.cochat.sso.security.validation.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@PasswordConfirmed
@Recaptcha
public class UserDto {

    @NotEmpty(message = "Please enter your firstname")
    private String firstname;
    @NotEmpty(message = "Please enter your lastname")
    private String lastname;
    @NotEmpty(message = "Please enter a username")
    @UniqueUsername
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Username should be alphanumeric without special characters with maximum 32 length")
    private String username;
    @NotEmpty(message = "Please enter an email")
    @UniqueEmail
    @Email(message = "Email is not valid")
    private String email;
    @NotEmpty(message = "Please enter password")
    @PasswordPolicy
    private String password;
    @NotEmpty(message = "Please confirm your password")
    private String confirmPassword;

}
