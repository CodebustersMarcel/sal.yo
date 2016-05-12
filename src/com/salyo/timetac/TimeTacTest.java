package com.salyo.timetac;

import com.salyo.apis.TimeTrackingApi;
import com.salyo.apis.TimeTrackingApiWrapper;
import com.salyo.apis.TimeTrackingApiWrapperFactory;
import com.salyo.data.Department;
import com.salyo.data.Employee;
import com.salyo.data.TimeEntry;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

/**
 * Created by daniel.blum on 12.05.2016.
 */
public class TimeTacTest {
    private static final String username = "wolterskluwer";
    private static final String password = "9dpG-RQC4-2kw7-sjxc-ApGm-jPDx";

    private TimeTrackingApiWrapper wrapper;

    @Before
    public void setup() {
        wrapper = TimeTrackingApiWrapperFactory.create(TimeTrackingApi.TimeTac, username, password);
    }

    @Test
    public void testEmployees() {
        Collection<Employee> employees = wrapper.getEmployees();

        for (Employee employee : employees) {
            System.out.println(employee.getForeignSystemId() + " " + employee.getFirstName() + " " + employee.getLastName());
        }
    }

    @Test
    public void testDepartments() {
        Collection<Department> employees = wrapper.getDepartments();

        for (Department department : employees) {
            System.out.println(department.getName());
        }
    }

    @Test
    public void testTimeEntries() {
        Collection<TimeEntry> entries = wrapper.getTimeEntries();
        for (TimeEntry entry : entries) {
            System.out.println(entry.getForeignSystemId() + " " + entry.getStart() + " " + entry.getEnd());
        }
    }
}
