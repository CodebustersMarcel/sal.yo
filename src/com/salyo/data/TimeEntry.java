package com.salyo.data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by david.leyendecker on 12.05.2016.
 */
public class TimeEntry {
    private UUID id;
    private LocalDateTime start;
    private LocalDateTime ned;
    private int type;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getNed() {
        return ned;
    }

    public void setNed(LocalDateTime ned) {
        this.ned = ned;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
