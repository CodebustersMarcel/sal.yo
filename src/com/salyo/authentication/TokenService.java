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
        return createHash(userName.concat("-").concat(String.valueOf(Instant.now().getEpochSecond())));
    }

    private String createHash(String value) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(value.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
}
