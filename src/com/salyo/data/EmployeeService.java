package com.salyo.data;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

/**
 * Created by david.leyendecker on 12.05.2016.
 */
public class EmployeeService {
    private static EmployeeService instance = new EmployeeService();

    private ConcurrentLinkedQueue<Employee> employees = new ConcurrentLinkedQueue<>();

    public static EmployeeService getInstance() {
        return instance;
    }

    public List<Employee> getAllByCompany(UUID companyId) {
        return employees.stream()
                .filter(employee -> employee.getCompanyId().equals(companyId))
                .collect(Collectors.toList());
    }

    public UUID persist(Employee employee) {

        if(employee.getId() == null) {
            employee.setId(UUID.randomUUID());
        } else {
            employees.removeIf(x -> x.getId().equals(employee.getId()));
        }
        employees.add(employee);

        return employee.getId();
    }
}
