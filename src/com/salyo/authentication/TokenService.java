package com.salyo.authentication;

import java.time.Instant;

/**
 * Created by david.leyendecker on 12.05.2016.
 */
public class TokenService {
    private static TokenService instance = new TokenService();

    public static TokenService getInstance() {
        return instance;
    }

    public String createToken(String userName) {
        return Hasher.createHash(userName.concat("-").concat(String.valueOf(Instant.now().getEpochSecond())));
    }
}
