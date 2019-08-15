package de.learning.demo.model;

import org.springframework.stereotype.Component;

@Component
public class AuthorizationToken {

    private String tokenValue;

    public String getTokenValue() {

        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {

        this.tokenValue = tokenValue;
    }
}
