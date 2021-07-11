package com.saral.keycloak.client.dtos;

import java.util.Arrays;

public class TokenInfoDto {

    private final String accessToken;
    private final long expiresIn;
    private final String refreshToken;
    private final String tokenType;
    private final long notBeforePolicy;
    private final String sessionState;
    private final String scope;
    private final String[] audience;
    private final long authTime;
    private final String issuedFor;
    private final String username;

    public TokenInfoDto(Builder builder) {
        this.accessToken = builder.accessToken;
        this.audience = builder.audience;
        this.authTime = builder.authTime;
        this.expiresIn = builder.expiresIn;
        this.refreshToken = builder.refreshToken;
        this.issuedFor = builder.issuedFor;
        this.notBeforePolicy = builder.notBeforePolicy;
        this.sessionState = builder.sessionState;
        this.scope = builder.scope;
        this.tokenType = builder.tokenType;
        this.username = builder.username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public long getNotBeforePolicy() {
        return notBeforePolicy;
    }

    public String getSessionState() {
        return sessionState;
    }

    public String getScope() {
        return scope;
    }

    public String[] getAudience() {
        return audience;
    }

    public long getAuthTime() {
        return authTime;
    }

    public String getIssuedFor() {
        return issuedFor;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "TokenInfoDto{" + "accessToken='" + accessToken + '\'' + ", expiresIn=" + expiresIn + ", refreshToken='" + refreshToken + '\'' + ", tokenType='" + tokenType + '\'' + ", notBeforePolicy=" + notBeforePolicy + ", sessionState='" + sessionState + '\'' + ", scope='" + scope + '\'' + ", audience=" + Arrays.toString(
                audience) + ", authTime=" + authTime + ", issuedFor='" + issuedFor + '\'' + ", " +
                "username='" + username + '\'' + '}';
    }

    public static class Builder {
        private String accessToken;
        private long expiresIn;
        private String refreshToken;
        private String tokenType;
        private long notBeforePolicy;
        private String sessionState;
        private String scope;
        private String[] audience;
        private long authTime;
        private String issuedFor;
        private String username;

        public static Builder newInstance(){
            return new Builder();
        }

        public TokenInfoDto.Builder setAccessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public Builder setExpiresIn(long expiresIn) {
            this.expiresIn = expiresIn;
            return this;
        }

        public Builder setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public Builder setTokenType(String tokenType) {
            this.tokenType = tokenType;
            return this;
        }

        public Builder setNotBeforePolicy(long notBeforePolicy) {
            this.notBeforePolicy = notBeforePolicy;
            return this;
        }

        public Builder setSessionState(String sessionState) {
            this.sessionState = sessionState;
            return this;
        }

        public Builder setScope(String scope) {
            this.scope = scope;
            return this;
        }

        public Builder setAudience(String[] audience) {
            this.audience = audience;
            return this;
        }

        public Builder setAuthTime(long authTime) {
            this.authTime = authTime;
            return this;
        }

        public Builder setIssuedFor(String issuedFor) {
            this.issuedFor = issuedFor;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public TokenInfoDto build(){
            return new TokenInfoDto(this);
        }
    }
}
