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
    public Response getTimelineByEmployee(@PathParam("employeeId") UUID employeeId) {

        JSONArray ja = new JSONArray();
        try {
            for (TimeEntry timeEntry : LocalServices.getTimeEntries(employeeId)) {
                JSONObject item = new JSONObject();
                item.put("badgeClass", "success");
                item.put("badgeIconClass", "");
                item.put("title", "Entry for ".concat(timeEntry.getStartDateTime().toLocalDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))));

                StringBuilder content = new StringBuilder();
                Duration duration = Duration.between(timeEntry.getStartDateTime(), timeEntry.getEndDateTime());
                long hours = duration.toMinutes() / 60;
                long minutes = duration.toMinutes() % 60;
                if(hours == 1) {
                    content.append(String.valueOf(hours)).append(" hour");
                } else if(duration.toHours() > 1) {
                    content.append(String.valueOf(hours)).append(" hours");
                }
                if(minutes == 1) {
                    if(content.length() > 0) {
                        content.append(" and ");
                    }
                    content.append(String.valueOf(minutes)).append(" minute");
                } else if(minutes > 1) {
                    if(content.length() > 0) {
                        content.append(" and ");
                    }
                    content.append(String.valueOf(minutes)).append(" minutes");
                }

                item.put("content", content.toString() + " worked at the office");
                ja.put(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return Response.ok().entity(ja.toString()).build();
    }

}
