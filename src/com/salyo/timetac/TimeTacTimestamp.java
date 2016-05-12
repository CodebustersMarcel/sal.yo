package com.salyo.timetac;

import com.salyo.data.TimeEntry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by daniel.blum on 12.05.2016.
 */
public class TimeTacTimestamp {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private int id;
    private int user_id;
    private String start_time;
    private String end_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public TimeEntry toTimeEntry() {
        TimeEntry result = new TimeEntry();

        result.setForeignSystemId(Integer.toString(id));

        LocalDateTime start = LocalDateTime.parse(start_time, formatter);
        LocalDateTime end = LocalDateTime.parse(end_time, formatter);

        result.setStart(start.format(DateTimeFormatter.ISO_DATE_TIME));
        result.setEnd(end.format(DateTimeFormatter.ISO_DATE_TIME));

        return result;
    }
}

