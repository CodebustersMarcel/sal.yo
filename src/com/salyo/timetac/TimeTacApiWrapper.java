package com.salyo.timetac;

import com.owlike.genson.Genson;
import com.salyo.apis.TimeTrackingApiWrapper;
import com.salyo.data.Department;
import com.salyo.data.Employee;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.Collection;

/**
 * Created by daniel.blum on 12.05.2016.
 */
public class TimeTacApiWrapper implements TimeTrackingApiWrapper {
    private static final String apiUrl = "https://he-cleve-go.timetac.com/wolterskluwer/api/v1/";
    private static final String getUsersPath = "users/get";
    private static final String getDepartmentsPath = "departments/get";
    private final String authorizationHeaderName;
    private final String authorizationHeaderValue;
    private final Genson genson = new Genson();

    public TimeTacApiWrapper(String username, String password) {
        String usernameAndPassword = username + ":" + password;

        this.authorizationHeaderName = "Authorization";
        this.authorizationHeaderValue = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(usernameAndPassword.getBytes());
    }

    private String getJson(String path) {
        Client client = ClientBuilder.newClient();

        return client.target(apiUrl)
                .path(path)
                .request("application/json")
                .header(authorizationHeaderName, authorizationHeaderValue)
                .get(String.class);
    }

    private <T> T get(Class<T> c, String path) {
        String jsonResponse = getJson(path);
        return genson.deserialize(jsonResponse, c);
    }

    public Collection<Employee> getEmployees() {
        TimeTacUsers timeTacUsers = get(TimeTacUsers.class, getUsersPath);
        return timeTacUsers.toEmployees();
    }

    public Collection<Department> getDepartments() {
        TimeTacDepartments timeTacDepartments = get(TimeTacDepartments.class, getDepartmentsPath);
        return timeTacDepartments.toDepartments();
    }
}
