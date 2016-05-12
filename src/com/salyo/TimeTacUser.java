package com.salyo;

/**
 * Created by daniel.blum on 12.05.2016.
 */
public class TimeTacUser {
    private int id;
    private int departmentId;
    String firstname;
    String lastname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public User toUser() {
        User result = new User();

        result.setId(id);
        result.setFirstname(firstname);
        result.setLastname(lastname);

        return result;
    }
}
