package com.salyo.data;

import com.salyo.apis.TimeTrackingApi;

import java.util.UUID;

/**
 * Created by daniel.blum on 12.05.2016.
 */
public class Company {
    private UUID id;
    private String name;
    private TimeTrackingApi api;
    private String username;
    private String password;
    private UUID userId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TimeTrackingApi getApi() {
        return api;
    }

    public void setApi(TimeTrackingApi api) {
        this.api = api;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
