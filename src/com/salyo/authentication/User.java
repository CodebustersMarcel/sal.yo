package com.salyo.authentication;

import java.util.UUID;

/**
 * Created by david.leyendecker on 12.05.2016.
 */
public class User {
    private UUID id;
    private String username;
    private String passwordHash;
    private UserRole role;

    public User(String username, String passwordHash, UserRole role) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public static User create(String username, String passwordHash, UserRole role) {
        return new User(username, passwordHash, role);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}

