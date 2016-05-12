package com.salyo.data;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

/**
 * Created by david.leyendecker on 12.05.2016.
 */
@Path("/departments")
public class DepartmentResource {

    @GET
    @Path("/{companyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDepartmentsByCompany(@PathParam("companyId") UUID companyId) {
        List<Department> departments = DepartmentService.getInstance().getAllDepartmentsByCompany(companyId);
        return Response.ok().entity(departments).build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response persistTimeEntry(Department department) {
        if(department == null) {
            return Response.serverError().build();
        }
        UUID id = DepartmentService.getInstance().persistDepartment(department);
        return Response.ok().entity(id).build();
    }

}
