package com.salyo.data;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {

        List<TimeEntry> items = Stream.generate(TimeEntry::new).limit(500).collect(Collectors.toList());

        return Response.ok(items).build();
    }

    @GET
    @Path("/{employeeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTimeEntriesByEmployee(@PathParam("employeeId") UUID employeeId) {

        List<TimeEntry> items = Stream.generate(TimeEntry::new).limit(500).collect(Collectors.toList());

        return Response.ok(items).build();
    }

    @Path("/{employeeId}/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTimeEntriesByEmployeeAndDate(@PathParam("employeeId") UUID employeeId, @PathParam("date") String date) {

        LocalDate localDate;
        try {
            localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        } catch (Exception e) {
            return Response.serverError().entity(e.getLocalizedMessage()).build();
        }

        List<TimeEntry> items = Stream.generate(TimeEntry::new).limit(500).collect(Collectors.toList());

        return Response.ok(items).build();
    }

}