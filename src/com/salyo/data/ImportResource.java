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
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by daniel.blum on 12.05.2016.
 */
@Path("/import")
public class ImportResource {
    private static LocalDateTime limit = null;

    private static final Duration increment = Duration.ofDays(15);

    @POST
    @Path("/{companyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response importCompany(@PathParam("companyId") UUID companyId) {
        LocalDateTime timestamp = LocalDateTime.now();

        Company company = LocalServices.getCompany(companyId);

        if (company == null) {
            return Response.serverError().build();
        }

        TimeTrackingApiWrapper wrapper = TimeTrackingApiWrapperFactory.create(company.getApi(), company.getUsername(), company.getPassword());

        Collection<Department> departments = wrapper.getDepartments();
        Collection<Employee> employees = wrapper.getEmployees();
        Collection<TimeEntry> timeEntries = wrapper.getTimeEntries();

        if (limit == null) {
            limit = getMinimumDate(timeEntries);
        }

        incrementLimit();

        timeEntries = filterTimeEntries(timeEntries, limit);

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

        for (TimeEntry timeEntry : timeEntries) {
            if (timeEntry.getTimestamp() == null) {
                timeEntry.setTimestamp(timestamp.toString());
            }

            mergedEmployees.stream()
                    .filter(e -> Objects.equals(e.getForeignSystemId(), timeEntry.getForeignSystemEmployeeId()))
                    .findFirst()
                    .ifPresent(e -> timeEntry.setEmployeeId(e.getId()));

            Response response = LocalServices.addTimeEntry(timeEntry);

            if (response.getStatus() != 200) {
                return response;
            }
        }

        LocalServices.addNotification("Import successfull", "The import for " + company.getName() +
                " finished successfully. The import conatained " + timeEntries.size() + "time entries.");

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

    private static LocalDateTime getMinimumDate(Collection<TimeEntry> timeEntries) {
        if (timeEntries == null) {
            return null;
        }

        Optional<TimeEntry> min = timeEntries.stream().min(Comparator.comparing(TimeEntry::getStartDateTime));

        if (min.isPresent()) {
            return min.get().getStartDateTime().minus(increment);
        } else {
            return null;
        }
    }

    private static Collection<TimeEntry> filterTimeEntries(Collection<TimeEntry> entries, LocalDateTime limit) {
        if (limit == null) {
            return Collections.emptyList();
        } else {
            LocalDateTime first = limit.minus(increment);
            return entries.stream()
                    .filter(e -> e.getStartDateTime() == null || (e.getStartDateTime().isAfter(first) && e.getStartDateTime().isBefore(limit)))
                    .collect(Collectors.toList());
        }
    }

    private static void incrementLimit() {
        if (limit != null) {
            limit = limit.plus(increment);
        }
    }
}
