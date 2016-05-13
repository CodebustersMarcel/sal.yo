package com.salyo.data;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

/**
 * Created by david.leyendecker on 12.05.2016.
 */
@Path("/employees")
public class EmployeeResource {

    @GET
    @Path("/{companyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployeesByCompany(@PathParam("companyId") UUID companyId) {
        if(companyId == null) {
            return Response.serverError().build();
        }
        List<Employee> employees = EmployeeService.getInstance().getAllByCompany(companyId);
        return Response.ok(employees).build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response persistTimeEntry(Employee employee) {
        if(employee == null) {
            return Response.serverError().build();
        }
        UUID id = EmployeeService.getInstance().persist(employee);
        return Response.ok().entity(id).build();
    }

}
