package com.salyo.apis;

import com.salyo.data.Department;
import com.salyo.data.Employee;

import java.util.Collection;

/**
 * Created by daniel.blum on 12.05.2016.
 */
public interface TimeTrackingApiWrapper {
    Collection<Employee> getEmployees();
    Collection<Department> getDepartments();
}
