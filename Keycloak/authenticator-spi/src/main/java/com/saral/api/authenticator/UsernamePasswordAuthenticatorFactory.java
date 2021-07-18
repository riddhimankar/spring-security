package com.saral.api.authenticator;

import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.authenticators.browser.UsernamePasswordFormFactory;
import org.keycloak.models.KeycloakSession;

public class UsernamePasswordAuthenticatorFactory extends UsernamePasswordFormFactory {

    public static final String PROVIDER_ID = "custom-auth-username-password-form";
    public static final UsernamePasswordAuthenticator SINGLETON = new UsernamePasswordAuthenticator();

    @Override
    public Authenticator create(KeycloakSession session) {
        return SINGLETON;
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }

    @Override
    public String getDisplayType() {
        return "Custom Username Password Form";
    }

    @Override
    public String getHelpText() {
        return "Validates a username and password from login form.";
    }
}
