package com.salyo.data;

import com.salyo.dummydata.DummyCompany;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by daniel.blum on 12.05.2016.
 */
@Path("/companies")
public class CompanyResource {
    public CompanyResource() {
        companies.add(DummyCompany.get());
    }

    private final List<Company> companies = new LinkedList<>();

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCompanies() {

        List<Company> companies = new LinkedList<>();

        companies.add(DummyCompany.get());

        return Response.ok(companies).build();
    }

    @GET
    @Path("/get/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompany(@PathParam("userId") UUID userId) {
        if (userId == null) {
            return Response.serverError().build();
        }

        Company company = companies.stream().filter(c -> Objects.equals(c.getId(), userId)).findFirst().orElse(null);

        return Response.ok(company).build();
    }
}
