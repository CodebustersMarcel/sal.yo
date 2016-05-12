package com.salyo;

import com.owlike.genson.ext.jaxrs.GensonJsonConverter;
import com.salyo.data.CompanyResource;
import com.salyo.authentication.AuthenticationResource;
import com.salyo.data.DepartmentResource;
import com.salyo.data.EmployeeResource;
import com.salyo.data.TimeEntriesResources;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by david.leyendecker on 12.05.2016.
 */
public class Main {
    public static void main(String[] args) {

        URI baseUri = UriBuilder.fromUri("http://localhost/")
                .port(9998)
                .build();

        ResourceConfig config = new ResourceConfig(retrieveResourceConfigurations())
                .register(GensonJsonConverter.class);

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);

        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Set<Class<?>> retrieveResourceConfigurations() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(EmployeeResource.class);
        classes.add(DepartmentResource.class);
        classes.add(TimeEntriesResources.class);
        classes.add(AuthenticationResource.class);
        classes.add(CompanyResource.class);
        return classes;
    }
}
