package com.salyo.data;

import com.salyo.LocalServices;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.UUID;

/**
 * Created by david.leyendecker on 13.05.2016.
 */
@Path("/timeline")
public class TimelineResource {

    @GET
    @Path("/employee/{employeeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTimelineByEmployee(@PathParam("employeeId")UUID employeeId) {

        JSONArray ja = new JSONArray();

        for (TimeEntry timeEntry : LocalServices.getTimeEntries(employeeId)) {
            try {
                JSONObject item = new JSONObject();
                item.put("title", "Entry for ".concat(timeEntry.getStartDateTime().toLocalDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))));
                item.put("content", Duration.between(timeEntry.getStartDateTime(), timeEntry.getEndDateTime()).toString().concat(" hours worked in office"));
                ja.put(item);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return Response.ok().entity(ja).build();
    }

}
