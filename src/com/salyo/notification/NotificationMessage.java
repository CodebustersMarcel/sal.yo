package com.salyo.notification;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

/**
 * Created by eugen.rieb on 12.05.2016.
 */
public class NotificationMessage {

    public NotificationMessage(long id, String shortMessage, String fullMessage) {
        this.id = id;
        this.shortMessage = shortMessage;
        this.fullMessage = fullMessage;
        this.dateTime = LocalDateTime.now();
    }

    private long id;
    private String shortMessage;
    private String fullMessage;
    private LocalDateTime dateTime;
    private boolean isChecked = false;

    public static long nextId(List<NotificationMessage> notifications) {
        if (notifications.isEmpty()) {
            return 1;
        }
        long lastId = notifications.stream()
                .max(Comparator.comparing(notificationMessage -> notificationMessage.getId())).get().getId();
        return ++lastId;
    }

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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
