package com.cochat.sso.security.service;

import com.cochat.sso.security.model.GoogleResponse;
import com.cochat.sso.security.property.GoogleRecaptchaProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GoogleRecaptchaService {

    private final GoogleRecaptchaProperty property;
    private final RestTemplate restTemplate;

    private static final String VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify"
            + "?secret={secret}&remoteip={remoteip}&response={response}";

    public boolean verify(String ip, String captchaResponse) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("remoteip", ip);
        paramMap.put("secret", property.getSecret());
        paramMap.put("response", captchaResponse);

        GoogleResponse googleResponse = restTemplate.getForEntity(VERIFY_URL, GoogleResponse.class, paramMap).getBody();
        return googleResponse != null && googleResponse.isSuccess() && googleResponse.getAction().equals("submit")
                && (googleResponse.getScore() > property.getThreshold()) && !googleResponse.hasClientError();
    }
}
