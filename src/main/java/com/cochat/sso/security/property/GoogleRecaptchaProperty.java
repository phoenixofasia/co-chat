package com.cochat.sso.security.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "google.recaptcha.key")
@Getter
@Setter
public class GoogleRecaptchaProperty {

    private String site;
    private String secret;
    private float threshold;

}
