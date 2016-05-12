package com.salyo.data;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by david.leyendecker on 12.05.2016.
 */
@Path("/timeentries")
public class TimeEntriesResources {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {

        List<TimeEntry> items = Stream.generate(TimeEntry::new).limit(500).collect(Collectors.toList());

        return Response.ok(items).build();
    }

    @GET
    @Path("/{employeeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTimeEntriesByEmployee(@PathParam("employeeId") UUID employeeId) {

        List<TimeEntry> items = TimeEntriesService.getInstance().getAllByEmployee(employeeId);

        return Response.ok(items).build();
    }

    @GET
    @Path("/{employeeId}/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTimeEntriesByEmployeeAndDate(@PathParam("employeeId") UUID employeeId, @PathParam("date") String date) {

        LocalDate localDate;
        try {
            localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        } catch (Exception e) {
            return Response.serverError().entity(e.getLocalizedMessage()).build();
        }

        List<TimeEntry> items = TimeEntriesService.getInstance().getAllByEmployeeAndDate(employeeId, localDate);

        return Response.ok(items).build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response persistTimeEntry(TimeEntry timeEntry) {
        UUID id = TimeEntriesService.getInstance().persist(timeEntry);
        return Response.ok().entity(id).build();
    }
}