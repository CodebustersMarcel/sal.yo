package com.salyo.authentication;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by david.leyendecker on 12.05.2016.
 */
public class AuthenticationService {
    private static AuthenticationService instance = new AuthenticationService();
    private Map<String, User> credentials = new HashMap<>();

    public static AuthenticationService getInstance() {
        return instance;
    }

    private AuthenticationService() {
        credentials.put("admin", User.create("admin", "admin", UserRole.Admin));
        credentials.put("user", User.create("user", "user", UserRole.Admin));
        credentials.put("supervisor", User.create("supervisor", "supervisor", UserRole.Admin));
    }

    public boolean check(String userName, String password) {
        User user = this.credentials.get(userName);
        return user != null && user.getPasswordHash().equals(password);
    }

    public User getUserByUsername(String username) {
        return this.credentials.get(username);
    }
}
