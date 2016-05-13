package com.salyo.data;

import java.util.UUID;

/**
 * Defines an department
 *
 * Created by david.leyendecker on 12.05.2016.
 */
public class Department {
    private UUID id;
    private UUID companyId;
    private String name;
    private UUID supervisorId;
    private UUID supervisorAssistantId;
    private UUID parenId;
    private String foreignSystemId;
    private String foreignSystemSupervisorId;
    private String foreignSystemSupervisorAssistantId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCompanyId() {
        return companyId;
    }

    public void setCompanyId(UUID companyId) {
        this.companyId = companyId;
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

    public String getForeignSystemId() {
        return foreignSystemId;
    }

    public void setForeignSystemId(String foreignSystemId) {
        this.foreignSystemId = foreignSystemId;
    }

    public String getForeignSystemSupervisorId() {
        return foreignSystemSupervisorId;
    }

    public void setForeignSystemSupervisorId(String foreignSystemSupervisorId) {
        this.foreignSystemSupervisorId = foreignSystemSupervisorId;
    }

    public String getForeignSystemSupervisorAssistantId() {
        return foreignSystemSupervisorAssistantId;
    }

    public void setForeignSystemSupervisorAssistantId(String foreignSystemSupervisorAssistantId) {
        this.foreignSystemSupervisorAssistantId = foreignSystemSupervisorAssistantId;
    }
}
