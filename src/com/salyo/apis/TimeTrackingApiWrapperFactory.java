package com.salyo.apis;

import com.salyo.timetac.TimeTacApiWrapper;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by daniel.blum on 12.05.2016.
 */
public class TimeTrackingApiWrapperFactory {
    public static TimeTrackingApiWrapper create(TimeTrackingApi api, String username, String password) {
        switch (api) {
            case TimeTac:
                return new TimeTacApiWrapper(username, password);

            default:
                throw new NotImplementedException();
        }
    }
}
