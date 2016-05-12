package com.salyo.notification;

import com.sun.org.apache.xerces.internal.util.URI;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by eugen.rieb on 12.05.2016.
 */
@Path("/notifications")
public class NotificationResource {
    static List<NotificationMessage> notifications = new LinkedList<>();
    static{
        notifications.add(new NotificationMessage(1,"short Message1","full Message1"));
        notifications.add(new NotificationMessage(2,"short Message2","full Message2"));
        notifications.add(new NotificationMessage(3,"short Message3","full Message3"));
        notifications.add(new NotificationMessage(4,"short Message4","full Message4"));
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessageById(@PathParam("id") long id) {

        Optional<NotificationMessage> optNotification = notifications.stream().filter(n->n.getId() == id).findFirst();
        if(optNotification.isPresent()){
            return Response.ok(optNotification.get()).build();
        }else{
            return Response.serverError().entity("Notification Message not found.").build();
        }
    }

    @PUT
    @Path("/result")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putMessage() {
        return Response.ok().build();
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

        List<NotificationMessage> foundedNotifications = notifications.stream().filter(n->n.getDate().toLocalDate().equals(localDate)).collect(Collectors.toList());
        if(!notifications.isEmpty()){
            return Response.ok(foundedNotifications).build();
        }else{
            return Response.serverError().entity("Notification Message not found.").build();
        }
    }
}
