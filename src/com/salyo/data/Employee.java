package com.salyo.data;

import java.util.UUID;
import java.util.function.Consumer;

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

    public static EmployeeBuilder builder() {
        return new EmployeeBuilder() {
            private UUID id;
            private UUID companyId;
            private String foreignSystemId;
            private String firstName;
            private String lastName;

            @Override
            public EmployeeBuilder id(UUID value) {
                this.id = value;
                return this;
            }

            @Override
            public EmployeeBuilder companyId(UUID value) {
                this.companyId = value;
                return this;
            }

            @Override
            public EmployeeBuilder foreignSystemId(String value) {
                this.foreignSystemId = value;
                return this;
            }

            @Override
            public EmployeeBuilder firstName(String value) {
                this.firstName = value;
                return this;
            }

            @Override
            public EmployeeBuilder lastName(String value) {
                this.lastName = value;
                return this;
            }

            @Override
            public Employee build() {
                Employee employee = new Employee();
                employee.setId(this.id != null ? this.id : UUID.randomUUID());
                employee.setCompanyId(this.companyId);
                employee.setLastName(this.lastName);
                employee.setFirstName(this.firstName);
                employee.setForeignSystemId(this.foreignSystemId);
                return employee;
            }

            @Override
            public void buildAndConsume(Consumer<Employee> employeeConsumer) {
                employeeConsumer.accept(build());
            }
        };
    }

}