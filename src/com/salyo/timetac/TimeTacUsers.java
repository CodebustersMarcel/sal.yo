package com.salyo.timetac;

import com.owlike.genson.annotation.JsonProperty;
import com.salyo.data.Employee;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by daniel.blum on 12.05.2016.
 */
public class TimeTacUsers {
    @JsonProperty("NumResults")
    private int numResults;

    @JsonProperty("Results")
    private List<TimeTacUser> results;

    public List<TimeTacUser> getResults() {
        return results;
    }

    public void setResults(List<TimeTacUser> results) {
        this.results = results;
    }

    public int getNumResults() {
        return numResults;
    }

    public void setNumResults(int numResults) {
        this.numResults = numResults;
    }

    public Collection<Employee> toEmployees() {
        List<Employee> result = new LinkedList<>();

        if (results != null) {
            for (TimeTacUser timeTacUser : results) {
                result.add(timeTacUser.toEmployee());
            }
        }

        return result;
    }
}
