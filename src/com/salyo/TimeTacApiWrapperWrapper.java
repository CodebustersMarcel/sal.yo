package com.salyo;

import com.owlike.genson.Genson;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.Collection;

/**
 * Created by daniel.blum on 12.05.2016.
 */
public class TimeTacApiWrapperWrapper implements TimeTrackingApiWrapper {
    public TimeTacApiWrapperWrapper(String username, String password) {
        String usernameAndPassword = username + ":" + password;

        this.authorizationHeaderName = "Authorization";
        this.authorizationHeaderValue = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(usernameAndPassword.getBytes());
    }

    private final String authorizationHeaderName;
    private final String authorizationHeaderValue;

    private final Genson genson = new Genson();

    private static final String apiUrl = "https://he-cleve-go.timetac.com/wolterskluwer/api/v1/";
    private static final String getUsersPath = "users/get";

    public Collection<User> getUsers() {
        TimeTacUsers timeTacUsers = get(TimeTacUsers.class, getUsersPath);
        return timeTacUsers.toUsers();
    }

    private String getJson(String path) {
        Client client = ClientBuilder.newClient();

        String jsonResponse = client.target(apiUrl)
                .path(path)
                .request("application/json")
                .header(authorizationHeaderName, authorizationHeaderValue)
                .get(String.class);

        return jsonResponse;
    }

    private <T> T get(Class<T> c, String path) {
        String jsonResponse = getJson(path);
        T result = genson.deserialize(jsonResponse, c);

        return result;
    }
}
