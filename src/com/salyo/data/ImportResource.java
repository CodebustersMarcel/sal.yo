package com.salyo.data;

import com.salyo.LocalServices;
import com.salyo.apis.TimeTrackingApiWrapper;
import com.salyo.apis.TimeTrackingApiWrapperFactory;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.UUID;

/**
 * Created by daniel.blum on 12.05.2016.
 */
@Path("/import")
public class ImportResource {
    @PUT
    @Path("/{companyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response importCompany(@PathParam("companyId") UUID companyId) {
        Company company = LocalServices.getCompany(companyId);

        if (company == null) {
            return Response.serverError().build();
        }

        TimeTrackingApiWrapper wrapper = TimeTrackingApiWrapperFactory.create(company.getApi(), company.getUsername(), company.getPassword());

        Collection<Employee> employees = wrapper.getEmployees();
        Collection<Department> departments = wrapper.getDepartments();
        Collection<TimeEntry> timeEntries = wrapper.getTimeEntries();

        for (TimeEntry timeEntry : timeEntries) {
            LocalServices.addTimeEntry(timeEntry);
        }

        return Response.ok().build();
    }
}
