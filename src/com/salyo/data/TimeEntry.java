package com.salyo.data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by david.leyendecker on 12.05.2016.
 */
public class TimeEntry {
    private UUID id;
    private UUID employeeId;
    private LocalDateTime start;
    private LocalDateTime end;
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

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public String getStartText() {
        return start.toString();
    }

    public void setStartText(String value) {
        start = LocalDateTime.parse(value);
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public String getEndText() {
        return end.toString();
    }

    public void setEndText(String value) {
        end = LocalDateTime.parse(value);
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
