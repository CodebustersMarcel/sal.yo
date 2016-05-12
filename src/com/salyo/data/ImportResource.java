package com.salyo.data;

import com.salyo.LocalServices;
import com.salyo.apis.TimeTrackingApiWrapper;
import com.salyo.apis.TimeTrackingApiWrapperFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.UUID;

/**
 * Created by daniel.blum on 12.05.2016.
 */
@Path("/import")
public class ImportResource {
    @POST
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

        /* for (Department department : departments) {
            Response response = LocalServices.addDepartment(department);

            if (response.getStatus() != 200) {
                return response;
            }
        }

        for (Employee employee : employees) {
            Response response = LocalServices.addEmployee(employee);

            if (response.getStatus() != 200) {
                return response;
            }
        } */

        for (TimeEntry timeEntry : timeEntries) {
            Response response = LocalServices.addTimeEntry(timeEntry);

            if (response.getStatus() != 200) {
                return response;
            }
        }

        return Response.ok().build();
    }
}
