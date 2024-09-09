package io.chris;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/")
@RegisterRestClient
public interface Client {

    @GET
    @Path("/server")
    @Produces(MediaType.TEXT_PLAIN)
    String call();

}
