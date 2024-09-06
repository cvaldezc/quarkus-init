package io.chris;

import io.chris.model.TvSerie;
import io.chris.proxy.TvSeriesProxy;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;

@Path("/tvseries")
public class TvSeriesResource {

    @RestClient
    TvSeriesProxy proxy;

    private List<TvSerie> tvSeries = new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@QueryParam("title") String title) {
        TvSerie tvSerie = getTvSeries(title);
        tvSeries.add(tvSerie);
        return Response.ok(tvSeries).build();
    }

    @Fallback(fallbackMethod = "fallbackGetTvSeries")
    public TvSerie getTvSeries(String title) {
        return proxy.get(title);
    }

    private TvSerie fallbackGetTvSeries(String title) {
        return new TvSerie();
    }
}
