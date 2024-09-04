package io.chris.proxy;

import io.chris.model.TvSerie;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/singlesearch")
@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient()
public interface TvSeriesProxy {

    @GET
    @Path("/shows")
    TvSerie get(@QueryParam("q") String title);
}
