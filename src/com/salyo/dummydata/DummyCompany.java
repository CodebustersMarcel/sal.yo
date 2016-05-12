package com.salyo.dummydata;

import com.salyo.apis.TimeTrackingApi;
import com.salyo.data.Company;

/**
 * Created by daniel.blum on 12.05.2016.
 */
public class DummyCompany {
    public static Company get() {
        Company result = new Company();

        result.setId(Constants.DEFAULT_COMPANY_ID);
        result.setName("Test Company");
        result.setApi(TimeTrackingApi.TimeTac);
        result.setUsername(Constants.TIMETAC_USERNAME);
        result.setPassword(Constants.TIMETAC_PASSWORD);
        result.setUserId(Constants.DEFAULT_USER_ID);

        return result;
    }
}
