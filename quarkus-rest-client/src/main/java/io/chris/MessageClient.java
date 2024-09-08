package io.chris;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/message")
@RegisterRestClient
public interface MessageClient {

    @GET
    String get();
}
