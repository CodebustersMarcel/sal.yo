package com.salyo;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by daniel.blum on 12.05.2016.
 */
public class TimeTrackingApiWrapperFactory {
    public static TimeTrackingApiWrapper create(TimeTrackingApi api, String username, String password) {
        switch (api) {
            case TimeTac:
                return new TimeTacApiWrapperWrapper(username, password);

            default:
                throw new NotImplementedException();
        }
    }
}
