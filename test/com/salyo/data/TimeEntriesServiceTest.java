package com.salyo.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by david.leyendecker on 12.05.2016.
 */
public class TimeEntriesServiceTest {

    private final static UUID EMPLOYEEID_BEN = UUID.fromString("a83c6553-0df0-463b-a663-d055482a4bfd");
    private final static UUID EMPLOYEEID_MARK = UUID.fromString("b8934841-e833-4a17-b150-d7fb0f173250");

    @Before
    public void setup() {
        TimeEntriesService.getInstance().persist(createTimeEntry(EMPLOYEEID_BEN, LocalDateTime.of(2016, 5, 14, 7, 30), LocalDateTime.of(2016, 5, 14, 12, 30)));
        TimeEntriesService.getInstance().persist(createTimeEntry(EMPLOYEEID_BEN, LocalDateTime.of(2016, 5, 15, 13, 0), LocalDateTime.of(2016, 5, 16, 17, 0)));

        TimeEntriesService.getInstance().persist(createTimeEntry(EMPLOYEEID_MARK, LocalDateTime.of(2016, 5, 16, 7, 30), LocalDateTime.of(2016, 5, 16, 12, 30)));
        TimeEntriesService.getInstance().persist(createTimeEntry(EMPLOYEEID_MARK, LocalDateTime.of(2016, 5, 16, 12, 30), LocalDateTime.of(2016, 5, 16, 16, 30)));

    }

    @Test
    public void getAllByUser() throws Exception {
        Assert.assertEquals(2, TimeEntriesService.getInstance().getAllByEmployee(EMPLOYEEID_BEN).size());
        Assert.assertEquals(2, TimeEntriesService.getInstance().getAllByEmployee(EMPLOYEEID_MARK).size());
    }

    private static TimeEntry createTimeEntry(UUID employeeId, LocalDateTime start, LocalDateTime end) {
        TimeEntry timeEntry = new TimeEntry();
        timeEntry.setEmployeeId(employeeId);
        timeEntry.setStart(start);
        timeEntry.setEnd(end);
        return timeEntry;
    }

}