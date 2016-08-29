package com.demo.entity.AccessToken;

import java.io.Serializable;
import java.util.Date;

public class AccessToken implements Serializable {
    private String accessToken;

    private Date expiresIn;

    private static final long serialVersionUID = 1L;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    public Date getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Date expiresIn) {
        this.expiresIn = expiresIn;
    }
}