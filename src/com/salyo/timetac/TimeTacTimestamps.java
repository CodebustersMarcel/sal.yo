package com.salyo.timetac;

import com.owlike.genson.annotation.JsonProperty;
import com.salyo.data.TimeEntry;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by daniel.blum on 12.05.2016.
 */
public class TimeTacTimestamps {
    @JsonProperty("NumResults")
    private int numResults;

    @JsonProperty("Results")
    private List<TimeTacTimestamp> results;

    public Collection<TimeEntry> toTimeEntries() {
        List<TimeEntry> result = new LinkedList<>();

        if (result != null) {
            for (TimeTacTimestamp timestamp : results) {
                result.add(timestamp.toTimeEntry());
            }
        }

        return result;
    }
}
