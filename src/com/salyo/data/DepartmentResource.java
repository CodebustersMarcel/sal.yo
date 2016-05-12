package com.salyo.data;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by david.leyendecker on 12.05.2016.
 */
@Path("/departments")
public class DepartmentResource {

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDepartments() {
        return Response.ok().build();
    }

}
