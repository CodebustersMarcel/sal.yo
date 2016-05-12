package com.salyo.authentication;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by david.leyendecker on 12.05.2016.
 */
@Path("/auth")
public class AuthenticationResource {

    @PUT
    @Path("/token")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createToken(@HeaderParam("username") String userName,
                                @HeaderParam("password") String password,
                                @HeaderParam("authentication-token") String authenticationToken) {
        if(!AuthenticationService.getInstance().check(userName, password)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        String token = TokenService.getInstance().createToken(userName);
        return Response.ok().entity(token).build();
    }

//    public Response checkToken() {
//        if(TokenService.getInstance().isTokenValid(authenticationToken)) {
//            return Response.ok().build();
//        }
//
//        return Response.status(Response.Status.FORBIDDEN).build();
//    }
}
