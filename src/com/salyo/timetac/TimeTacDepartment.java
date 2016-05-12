package com.salyo.timetac;

import com.salyo.data.Department;

/**
 * Created by daniel.blum on 12.05.2016.
 */
public class TimeTacDepartment {
    private int id;
    private String department_name;
    private int supervisor_id;
    private int supervisor_assistant_id;
    private int mother_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public int getSupervisor_id() {
        return supervisor_id;
    }

    public void setSupervisor_id(int supervisor_id) {
        this.supervisor_id = supervisor_id;
    }

    public int getSupervisor_assistant_id() {
        return supervisor_assistant_id;
    }

    public void setSupervisor_assistant_id(int supervisor_assistant_id) {
        this.supervisor_assistant_id = supervisor_assistant_id;
    }

    public int getMother_id() {
        return mother_id;
    }

    public void setMother_id(int mother_id) {
        this.mother_id = mother_id;
    }

    public Department toDepartment() {
        Department result = new Department();

        result.setName(department_name);
        result.setForeignSystemId(Integer.toString(id));
        result.setForeignSystemSupervisorId(Integer.toString(supervisor_id));
        result.setForeignSystemSupervisorAssistantId(Integer.toString(supervisor_assistant_id));

        return result;
    }
}
