package com.salyo.data;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by david.leyendecker on 12.05.2016.
 */
@Path("/employees")
public class EmployeeResource {

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployees() {

        List<Employee> employees = Stream.generate(() -> new Employee("Hans", "Peter Blau"))
                .limit(10)
                .collect(Collectors.toList());

        return Response.ok(employees).build();
    }

}
