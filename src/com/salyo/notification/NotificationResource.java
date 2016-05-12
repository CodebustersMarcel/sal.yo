package com.salyo.notification;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
/**
 * Created by eugen.rieb on 12.05.2016.
 */
@Path("/notifications")
public class NotificationResource {
    static List<NotificationMessage> notifications = new LinkedList<>();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessageById(@PathParam("id") long id) {

        Optional<NotificationMessage> optNotification = notifications.stream().filter(n -> n.getId() == id).findFirst();
        if (optNotification.isPresent()) {
            return Response.ok(optNotification.get().getFullMessage()).build();
        } else {
            return Response.serverError().entity("Notification Message not found.").build();
        }
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response putMessage(@HeaderParam("short") String shortMessage,
                               @HeaderParam("full") String fullMessage) {
        NotificationMessage notificationMessage = new NotificationMessage(nextId(), shortMessage, fullMessage);
        if (MailNotificator.SendEmail(notificationMessage)) {
            notifications.add(notificationMessage);
            return Response.ok(notificationMessage.getId()).build();
        }

        return Response.serverError().entity("Sending failed!").build();
    }

    private long nextId() {
        if (notifications.isEmpty()) {
            return 1;
        }
        long lastId = notifications.stream()
                .max(Comparator.comparing(notificationMessage -> notificationMessage.getId())).get().getId();
        return ++lastId;
    }

    @GET
    @Path("/by/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessageByDate(@PathParam("date") String date) {
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        } catch (Exception e) {
            return Response.serverError().entity(e.getLocalizedMessage()).build();
        }

        List<NotificationMessage> foundedNotifications = notifications.stream().filter(n -> n.getDate().toLocalDate().equals(localDate)).collect(Collectors.toList());
        if (!notifications.isEmpty()) {
            String responseMessage = "";
            for (NotificationMessage notification : notifications) {
                responseMessage += notification.getFullMessage() + "\n";
            }
            return Response.ok(responseMessage).build();
        } else {
            return Response.serverError().entity("Notification Message not found.").build();
        }
    }
}
