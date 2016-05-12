package com.salyo.timetac;

import com.owlike.genson.annotation.JsonProperty;
import com.salyo.data.Department;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by daniel.blum on 12.05.2016.
 */
public class TimeTacDepartments {
    @JsonProperty("NumResults")
    private int numResults;

    @JsonProperty("Results")
    private List<TimeTacDepartment> results;

    public Collection<Department> toDepartments() {
        List<Department> result = new LinkedList<>();

        if (result != null) {
            for (TimeTacDepartment department : results) {
                result.add(department.toDepartment());
            }
        }

        return result;
    }
}
