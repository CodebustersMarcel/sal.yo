package com.salyo;

import org.junit.Test;

import java.util.Collection;

/**
 * Created by daniel.blum on 12.05.2016.
 */
public class TimeTacTest {
    @Test
    public void test() {
        String username = "wolterskluwer";
        String password = "9dpG-RQC4-2kw7-sjxc-ApGm-jPDx";

        TimeTrackingApiWrapper wrapper = TimeTrackingApiWrapperFactory.create(TimeTrackingApi.TimeTac, username, password);

        Collection<User> users = wrapper.getUsers();

        for (User user : users) {
            System.out.println(user.getId() + " " + user.getFirstname() + " " + user.getLastname());
        }
    }
}
