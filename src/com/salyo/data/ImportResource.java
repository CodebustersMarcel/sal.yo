package com.salyo.data;

import com.salyo.LocalServices;
import com.salyo.apis.TimeTrackingApiWrapper;
import com.salyo.apis.TimeTrackingApiWrapperFactory;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

        Collection<Department> departments = wrapper.getDepartments();
        Collection<Employee> employees = wrapper.getEmployees();
        Collection<TimeEntry> timeEntries = wrapper.getTimeEntries();

        Collection<Department> mergedDepartments = mergeDepartments(departments, LocalServices.getDepartments(companyId));

        for (Department department : departments) {
            department.setCompanyId(companyId);
            Response response = LocalServices.addDepartment(department);

            if (response.getStatus() != 200) {
                return response;
            }
        }

        for (Employee employee : employees) {
            employee.setCompanyId(companyId);
            Response response = LocalServices.addEmployee(employee);

            if (response.getStatus() != 200) {
                return response;
            }
        }

        for (TimeEntry timeEntry : timeEntries) {
            Response response = LocalServices.addTimeEntry(timeEntry);

            if (response.getStatus() != 200) {
                return response;
            }
        }

        return Response.ok().build();
    }

    private static Collection<Department> mergeDepartments(Collection<Department> foreignSystem, Collection<Department> localSystem) {
        List<Department> result = new ArrayList<>();
        for (Department department : foreignSystem) {
            result.add(localSystem.stream()
                    .filter(i -> i.getForeignSystemId().equals(department.getForeignSystemId()))
                    .findFirst()
                    .map(x -> {
                        department.setId(x.getId());
                        return department;
                    }).orElse(department));
        }
        return result;
    }
}
