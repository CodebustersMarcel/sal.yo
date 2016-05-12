package com.salyo.data;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

/**
 * Created by david.leyendecker on 12.05.2016.
 */
public class DepartmentService {
    private static DepartmentService instance = new DepartmentService();

    private ConcurrentLinkedQueue<Department> departments = new ConcurrentLinkedQueue<>();


    public static DepartmentService getInstance() {
        return instance;
    }

    public List<Department> getAllDepartmentsByCompany(UUID companyId) {
        return departments
                .stream()
                .filter(department -> department.getCompanyId().equals(companyId))
                .collect(Collectors.toList());
    }

    public UUID persistDepartment(Department department) {

        if(department.getId() == null) {
            department.setId(UUID.randomUUID());
        } else {
            departments.removeIf(x -> x.getId().equals(department.getId()));
        }
        departments.add(department);
        return department.getId();
    }

}
