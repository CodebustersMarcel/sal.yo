package com.salyo.data;

import com.salyo.apis.TimeTrackingApi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by daniel.blum on 12.05.2016.
 */
@Path("/companies")
public class CompanyResource {
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCompanies() {

        List<Company> companies = new LinkedList<>();

        Company defaultCompany = new Company();

        defaultCompany.setId(UUID.fromString("49f81137-bd35-4537-b50e-6bd3cb868d4c"));
        defaultCompany.setName("Test Company");
        defaultCompany.setApi(TimeTrackingApi.TimeTac);
        defaultCompany.setUsername("wolterskluwer");
        defaultCompany.setPassword("9dpG-RQC4-2kw7-sjxc-ApGm-jPDx");

        companies.add(defaultCompany);

        return Response.ok(companies).build();
    }
}
