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

        Collection<Department> mergedDepartments = merge(departments, LocalServices.getDepartments(companyId));

        for (Department department : mergedDepartments) {
            department.setCompanyId(companyId);
            Response response = LocalServices.addDepartment(department);

            if (response.getStatus() != 200) {
                return response;
            }
        }

        Collection<Employee> mergedEmployees = merge(employees, LocalServices.getEmployees(companyId));

        for (Employee employee : mergedEmployees) {
            employee.setCompanyId(companyId);
            Response response = LocalServices.addEmployee(employee);

            if (response.getStatus() != 200) {
                return response;
            }
        }

        Collection<TimeEntry> mergedTimeEntries = merge(timeEntries, LocalServices.getTimeEntries(companyId));

        for (TimeEntry timeEntry : mergedTimeEntries) {
            Response response = LocalServices.addTimeEntry(timeEntry);

            if (response.getStatus() != 200) {
                return response;
            }
        }

        return Response.ok().build();
    }

    private static <T extends ForeignSystemIdItem> Collection<T> merge(Collection<T> foreignSystem, Collection<T> localSystem) {
        List<T> result = new ArrayList<>();
        for (T item : foreignSystem) {
            result.add(localSystem.stream()
                    .filter(i -> i.getForeignSystemId().equals(item.getForeignSystemId()))
                    .findFirst()
                    .map(x -> {
                        item.setId(x.getId());
                        return item;
                    }).orElse(item));
        }
        return result;
    }
}
