package com.salyo.authentication;

import java.time.Duration;
import java.time.Instant;

/**
 * Created by david.leyendecker on 12.05.2016.
 */
public class AuthenticationToken {
    private String value;
    private long validUntil;

    private AuthenticationToken(String value) {
        this.value = value;
    }

    public static AuthenticationToken create(String value) {
        return new AuthenticationToken(value);
    }

    public String getValue() {
        return this.value;
    }

    public long getValidUntil() {
        return this.validUntil;
    }

    public void update() {
        this.validUntil = Instant.now().plus(Duration.ofMinutes(2)).getEpochSecond();
    }
}
