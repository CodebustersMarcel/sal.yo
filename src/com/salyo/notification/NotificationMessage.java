package com.salyo.notification;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by eugen.rieb on 12.05.2016.
 */
public class NotificationMessage {

    public NotificationMessage(long id, String shortMessage, String fullMessage) {
        this.id = id;
        this.shortMessage = shortMessage;
        this.fullMessage = fullMessage;
        this.date=LocalDateTime.now();
    }

    private long id;
    private String shortMessage;
    private String fullMessage;
    private LocalDateTime date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getShortMessage() {
        return shortMessage;
    }

    public void setShortMessage(String shortMessage) {
        this.shortMessage = shortMessage;
    }

    public String getFullMessage() {
        return fullMessage;
    }

    public void setFullMessage(String fullMessage) {
        this.fullMessage = fullMessage;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
