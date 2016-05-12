package com.salyo;

import com.owlike.genson.Genson;
import com.salyo.data.Company;
import com.salyo.data.Department;
import com.salyo.data.Employee;
import com.salyo.data.TimeEntry;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.UUID;

/**
 * Created by daniel.blum on 12.05.2016.
 */
public class LocalServices {
    public static final String URI = "http://localhost:9998/";
    private static final Genson genson = new Genson();

    private static final String COMPANY_GET_PATH = "companies/get/";
    private static final String ADD_TIMEENTRY_PATH = "timeentries/add/";
    private static final String ADD_DEPARTMENT_PATH = "departments/add";
    private static final String ADD_EMPLOYEE_PATH = "employees/add";

    private static String getJsonString(String path) {
        Client client = ClientBuilder.newClient();

        return client.target(URI)
                .path(path)
                .request("application/json")
                .get(String.class);
    }

    private static <T> T get(Class<T> c, String servicePath, Object parameter) {
        String path = servicePath + parameter;

        String jsonResponse = getJsonString(path);
        return genson.deserialize(jsonResponse, c);
    }

    private static <T> Response post(T obj, String path) {
        Client client = ClientBuilder.newClient();

        String fullPath = URI + path;

        String jsonString = new Genson().serialize(obj);

        Response response = client.target(fullPath)
                .request("application/json")
                .post(Entity.json(jsonString));

        return response;
    }

    public static Company getCompany(UUID companyId) {
        return get(Company.class, COMPANY_GET_PATH, companyId);
    }

    public static Response addTimeEntry(TimeEntry timeEntry) {
        return post(timeEntry, ADD_TIMEENTRY_PATH);
    }

    public static Response addDepartment(Department department) {
        return post(department, ADD_DEPARTMENT_PATH);
    }

    public static Response addEmployee(Employee employee) {
        return post(employee, ADD_EMPLOYEE_PATH);
    }
}
