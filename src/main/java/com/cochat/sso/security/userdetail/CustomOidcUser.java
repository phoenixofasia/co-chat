package com.cochat.sso.security.userdetail;

import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public class CustomOidcUser extends DefaultOidcUser implements CustomAuthenticatedPrincipal {

    public CustomOidcUser(OidcUser user) {
        super(user.getAuthorities(), user.getIdToken());
    }

    @Override
    public String getFirstName() {
        return super.getGivenName();
    }

    @Override
    public String getLastName() {
        return super.getFamilyName();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }
}
