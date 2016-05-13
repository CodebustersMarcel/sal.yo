package com.salyo;

import com.owlike.genson.GenericType;
import com.owlike.genson.Genson;
import com.salyo.data.Company;
import com.salyo.data.Department;
import com.salyo.data.Employee;
import com.salyo.data.TimeEntry;
import com.salyo.notification.NotificationMessage;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created by daniel.blum on 12.05.2016.
 */
public class LocalServices {
    public static final String URI = "http://localhost:9998/";
    private static final Genson genson = new Genson();

    private static final String COMPANY_GET_PATH = "companies/get/";
    private static final String ADD_TIMEENTRY_PATH = "timeentries/add/";
    private static final String ADD_DEPARTMENT_PATH = "departments/add/";
    private static final String ADD_EMPLOYEE_PATH = "employees/add/";
    private static final String ADD_NOTIFICATION_PATH ="notifications/add/";

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

    public static Collection<Department> getDepartments(UUID companyId) {
        String path = "departments/" + companyId.toString();

        String jsonResponse = getJsonString(path);
        return genson.deserialize(jsonResponse, new GenericType<List<Department>>() {
        });
    }

    public static Collection<Employee> getEmployees(UUID companyId)
    {
        String path = "employees/" + companyId.toString();

        String jsonResponse = getJsonString(path);
        return genson.deserialize(jsonResponse, new GenericType<List<Employee>>() {
        });
    }

    public static Collection<TimeEntry> getTimeEntries(UUID companyId) {
        String path = "timeentries/" + companyId.toString();

        String jsonResponse = getJsonString(path);
        return genson.deserialize(jsonResponse, new GenericType<List<TimeEntry>>() {
        });
    }

    public static Collection<NotificationMessage> getNotifications() {
        String path = "/notifications/timestamp/10000";
        String jsonResponse = getJsonString(path);
        return genson.deserialize(jsonResponse, new GenericType<List<NotificationMessage>>() {
        });
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

    public static Response addNotification(String shortMessage, String fullMessage) {
        return ClientBuilder.newClient().target(URI + ADD_NOTIFICATION_PATH)
                .request(MediaType.APPLICATION_JSON)
                .header("short", shortMessage)
                .header("full", fullMessage)
                .post(null);
    }
}
