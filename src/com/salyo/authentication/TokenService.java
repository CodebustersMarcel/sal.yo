package com.salyo.authentication;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by david.leyendecker on 12.05.2016.
 */
public class TokenService {
    private static TokenService instance = new TokenService();

    private ConcurrentHashMap<String, String> registeredTokens = new ConcurrentHashMap<>();

    public static TokenService getInstance() {
        return instance;
    }

    public String createToken(String userName) {
        String tokenValue = Hasher.createHash(userName.concat("-").concat(String.valueOf(Instant.now().getEpochSecond())));
        registeredTokens.putIfAbsent(tokenValue, userName);
        return tokenValue;
    }

    public User getUserFromToken(String tokenValue) {
        String username = this.registeredTokens.get(tokenValue);
        if (username == null) {
            throw new NullPointerException();
        }

        return AuthenticationService.getInstance().getUserByUsername(username);
    }
}
