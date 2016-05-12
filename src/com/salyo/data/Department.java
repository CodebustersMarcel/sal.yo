package com.salyo.data;

import java.util.UUID;

/**
 * Defines an department
 *
 * Created by david.leyendecker on 12.05.2016.
 */
public class Department {
    private UUID id;
    private String name;
    private UUID supervisorId;
    private UUID supervisorAssistantId;
    private UUID parenId;

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

    public UUID getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(UUID supervisorId) {
        this.supervisorId = supervisorId;
    }

    public UUID getSupervisorAssistantId() {
        return supervisorAssistantId;
    }

    public void setSupervisorAssistantId(UUID supervisorAssistantId) {
        this.supervisorAssistantId = supervisorAssistantId;
    }

    public UUID getParenId() {
        return parenId;
    }

    public void setParenId(UUID parenId) {
        this.parenId = parenId;
    }
}
