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
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by david.leyendecker on 13.05.2016.
 */
@Path("/timeline")
public class TimelineResource {

    @GET
    @Path("/employee/{employeeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTimelineByEmployee(@PathParam("employeeId") UUID employeeId) {

        Collection<TimeEntry> sorted = LocalServices.getTimeEntries(employeeId).stream().sorted(Comparator.comparing(TimeEntry::getTimestamp)).collect(Collectors.toList());

        String lastTimestamp = null;
        Duration duration = Duration.ZERO;

        JSONArray ja = new JSONArray();
        try {
            for (TimeEntry timeEntry : sorted) {

                if (!Objects.equals(lastTimestamp, timeEntry.getTimestamp())) {
                    if (lastTimestamp != null) {
                        ja.put(createSummaryJsonObject(duration));
                    }

                    duration = Duration.ZERO;

                    lastTimestamp = timeEntry.getTimestamp();

                    JSONObject ts = new JSONObject();
                    ts.put("badgeClass", "info");
                    ts.put("badgeIconClass", "");
                    ts.put("title", "Import from TimeTac");
                    ts.put("content", timeEntry.getTimestamp());
                    ja.put(ts);
                }

                duration = duration.plus(calculateDuration(timeEntry));

                JSONObject item = new JSONObject();
                item.put("badgeClass", "success");
                item.put("badgeIconClass", "");
                item.put("title", "Entry for ".concat(timeEntry.getStartDateTime().toLocalDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))));
                item.put("content", buildContentString(timeEntry));
                ja.put(item);
            }

            if (lastTimestamp != null) {
                ja.put(createSummaryJsonObject(duration));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return Response.ok().entity(ja.toString()).build();
    }

    private JSONObject createSummaryJsonObject(Duration duration) throws JSONException {
        JSONObject summary = new JSONObject();
        summary.put("badgeClass", "warning");
        summary.put("badgeIconClass", "");
        summary.put("title", "Summary");
        summary.put("content", buildDurationString(duration));
        return summary;
    }

    private String buildContentString(TimeEntry timeEntry) {
        Duration duration = Duration.between(timeEntry.getStartDateTime(), timeEntry.getEndDateTime());
        return buildDurationString(duration);
    }

    private String buildDurationString(Duration duration) {
        StringBuilder content = new StringBuilder();
        long hours = duration.toMinutes() / 60;
        long minutes = duration.toMinutes() % 60;
        content.append("Worked at the office for ");
        if (hours == 1) {
            content.append(String.valueOf(hours)).append(" hour");
        } else if (duration.toHours() > 1) {
            content.append(String.valueOf(hours)).append(" hours");
        }
        if (minutes == 1) {
            if (hours > 0) {
                content.append(" and ");
            }
            content.append(String.valueOf(minutes)).append(" minute");
        } else if (minutes > 1) {
            if (hours > 0) {
                content.append(" and ");
            }
            content.append(String.valueOf(minutes)).append(" minutes");
        }
        return content.toString();
    }

    private Duration calculateDuration(TimeEntry timeEntry) {
        return Duration.between(timeEntry.getStartDateTime(), timeEntry.getEndDateTime());
    }

    @GET
    @Path("/notifications")
    @Produces(MediaType.APPLICATION_JSON)
    public Response geTimelineForNotifications() {
        return Response.ok().entity(LocalServices.getNotifications()).build();
    }
}
