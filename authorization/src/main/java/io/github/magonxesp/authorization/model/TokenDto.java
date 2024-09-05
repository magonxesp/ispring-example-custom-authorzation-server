package io.github.magonxesp.authorization.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenDto {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("expires_in")
    private long expiresIn;

    public TokenDto(String accessToken, String tokenType, long expiresIn) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
