package com.salyo.authentication;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by david.leyendecker on 12.05.2016.
 */
public class AuthenticationService {
    private static AuthenticationService instance;
    private Map<String, User> credentials = new HashMap<>();

    public static AuthenticationService getInstance() {
        return instance;
    }

    public boolean check(String userName, String password) {
        User user = this.credentials.get(userName);
        return user != null && user.getPasswordHash().equals(password);
    }
}
