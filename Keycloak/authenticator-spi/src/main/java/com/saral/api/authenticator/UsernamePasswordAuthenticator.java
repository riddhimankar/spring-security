package com.saral.api.authenticator;

import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.authenticators.browser.UsernamePasswordForm;
import org.keycloak.protocol.oidc.OIDCLoginProtocol;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.services.managers.AuthenticationManager;

import javax.ws.rs.core.MultivaluedMap;

public class UsernamePasswordAuthenticator extends UsernamePasswordForm {

    @Override
    public void action(AuthenticationFlowContext context) {
        super.action(context);
    }

    @Override
    protected boolean validateForm(AuthenticationFlowContext context, MultivaluedMap<String, String> formData) {
        String username = (String) formData.getFirst(AuthenticationManager.FORM_USERNAME);
        String password = (String) formData.getFirst(CredentialRepresentation.PASSWORD);
        log.info("Username: " + username + ", password: " + password);
        return super.validateForm(context, formData);
    }

    @Override
    public void authenticate(AuthenticationFlowContext context) {
        String loginHint = context
                .getAuthenticationSession()
                .getClientNote(OIDCLoginProtocol.LOGIN_HINT_PARAM);

        String rememberMeUsername = AuthenticationManager
                .getRememberMeUsername(context.getRealm(),
                                       context.getHttpRequest().getHttpHeaders());

        log.info("login_hint: " + loginHint + ", remember_me_username: " + rememberMeUsername);
        super.authenticate(context);
    }

}
