package com.salyo.data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

/**
 * Created by david.leyendecker on 12.05.2016.
 */
public class TimeEntriesService {
    private static TimeEntriesService instance = new TimeEntriesService();

    private ConcurrentLinkedQueue<TimeEntry> entries = new ConcurrentLinkedQueue<>();

    public static TimeEntriesService getInstance() {
        return instance;
    }

    public UUID persist(TimeEntry timeEntry) {

        if(timeEntry.getId() == null) {
            timeEntry.setId(UUID.randomUUID());
        } else {
            entries.removeIf(x -> x.getId().equals(timeEntry.getId()));
        }
        entries.add(timeEntry);

        return timeEntry.getId();
    }

    public List<TimeEntry> getAllByEmployee(UUID employeeId) {
        return entries
                .stream()
                .filter(timeEntry -> timeEntry.getEmployeeId().equals(employeeId))
                .collect(Collectors.toList());
    }

    public List<TimeEntry> getAllByEmployeeAndDate(UUID employeeId, LocalDate date) {
        return entries
                .stream()
                .filter(timeEntry -> timeEntry.getEmployeeId().equals(employeeId))
                .filter(timeEntry -> timeEntry.getStartDateTime().toLocalDate().equals(date) || timeEntry.getEndDateTime().toLocalDate().equals(date))
                .collect(Collectors.toList());
    }
}
