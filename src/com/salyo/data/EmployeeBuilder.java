package com.salyo.data;

import java.util.UUID;
import java.util.function.Consumer;

public interface EmployeeBuilder {
    EmployeeBuilder id(UUID value);
    EmployeeBuilder companyId(UUID value);
    EmployeeBuilder foreignSystemId(String value);
    EmployeeBuilder firstName(String value);
    EmployeeBuilder lastName(String value);
    Employee build();
    void buildAndConsume(Consumer<Employee> employeeConsumer);
}
