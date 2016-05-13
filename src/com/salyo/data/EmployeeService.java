package com.salyo.data;

import com.salyo.dummydata.Constants;

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

    private EmployeeService() {
        // Create dummy data from test system
        Employee.builder().companyId(Constants.DEFAULT_COMPANY_ID).foreignSystemId("1")
                .firstName("Olga").lastName("Puck").id(Constants.OLGA_PUCK_ID).buildAndConsume(employees::add);
        Employee.builder().companyId(Constants.DEFAULT_COMPANY_ID).foreignSystemId("10")
                .firstName("Stefanie").lastName("Berger").id(Constants.STEFANIE_BERGER_ID).buildAndConsume(employees::add);
        Employee.builder().companyId(Constants.DEFAULT_COMPANY_ID).foreignSystemId("11")
                .firstName("Sophie").lastName("Steiner").id(Constants.SOPHIE_STEINER_ID).buildAndConsume(employees::add);
    }

    public List<Employee> getAllByCompany(UUID companyId) {
        return employees.stream()
                .filter(employee -> employee.getCompanyId().equals(companyId))
                .collect(Collectors.toList());
    }

    public UUID persist(Employee employee) {

        if (employee.getId() == null) {
            employee.setId(UUID.randomUUID());
        } else {
            employees.removeIf(x -> x.getId().equals(employee.getId()));
        }
        employees.add(employee);

        return employee.getId();
    }
}
