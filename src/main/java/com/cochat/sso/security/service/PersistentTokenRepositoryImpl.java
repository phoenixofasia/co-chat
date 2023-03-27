package com.cochat.sso.security.service;

import com.cochat.sso.security.entity.PersistentLoginToken;
import com.cochat.sso.security.repository.PersistentLoginTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class PersistentTokenRepositoryImpl implements PersistentTokenRepository {

    private final PersistentLoginTokenRepository repository;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        repository.save(
                new PersistentLoginToken(
                        token.getUsername(),
                        token.getSeries(),
                        token.getTokenValue(),
                        token.getDate()
                )
        );
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        PersistentLoginToken loginToken = repository.findBySeries(series)
                .orElseThrow(() -> new SecurityException("Invalid persistent login token update"));
        repository.save(
                new PersistentLoginToken(
                        loginToken.getUsername(),
                        series,
                        tokenValue,
                        lastUsed
                )
        );
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        PersistentLoginToken currentToken = repository.findBySeries(seriesId)
                .orElseThrow(() -> new SecurityException("Invalid seriesId retrieval"));
        return new PersistentRememberMeToken(currentToken.getUsername(),
                currentToken.getSeries(),
                currentToken.getToken(),
                currentToken.getLastUsed());
    }

    @Override
    public void removeUserTokens(String username) {
        repository.findById(username).ifPresent(repository::delete);
    }
}
