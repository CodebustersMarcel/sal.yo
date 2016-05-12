package com.salyo;

import com.owlike.genson.Genson;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 * Created by daniel.blum on 12.05.2016.
 */
public class LocalServices {
    public static final String URI = "http://localhost:9998/";
    private static final Genson genson = new Genson();

    private static String getJsonString(String path) {
        Client client = ClientBuilder.newClient();

        return client.target(URI)
                .path(path)
                .request("application/json")
                .get(String.class);
    }

    public static <T> T get(Class<T> c, String path) {
        String jsonResponse = getJsonString(path);
        return genson.deserialize(jsonResponse, c);
    }
}
