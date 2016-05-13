package com.salyo.data;

import java.util.UUID;

/**
 * Created by david.leyendecker on 12.05.2016.
 */
public class Employee implements ForeignSystemIdItem {
    private UUID id;
    private UUID companyId;
    private UUID departmentId;
    private String foreignSystemId;
    private String firstName;
    private String lastName;

    public Employee() {
    }

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

    public UUID getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(UUID departmentId) {
        this.departmentId = departmentId;
    }

    public String getForeignSystemId() {
        return foreignSystemId;
    }

    public void setForeignSystemId(String foreignSystemId) {
        this.foreignSystemId = foreignSystemId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
