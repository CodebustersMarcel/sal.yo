package com.salyo.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Created by david.leyendecker on 12.05.2016.
 */
public class TimeEntry {
    private UUID id;
    private UUID employeeId;
    private String start;
    private String end;
    private int type;
    private String foreignSystemId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public LocalDateTime getStartDateTime() {
        return LocalDateTime.parse(getStart(), DateTimeFormatter.ISO_DATE_TIME);
    }

    public LocalDateTime getEndDateTime() {
        return LocalDateTime.parse(getEnd(), DateTimeFormatter.ISO_DATE_TIME);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getForeignSystemId() {
        return foreignSystemId;
    }

    public void setForeignSystemId(String foreignSystemId) {
        this.foreignSystemId = foreignSystemId;
    }
}
