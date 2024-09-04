package io.chris.quarkus;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Arrays;
import java.util.List;

@Path("/temperatures")
public class TemperatureResource {
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Temperature> measures() {
    return Arrays.asList(new Temperature("Lima", 18, 29),
            new Temperature("Buenos aires", 10, 25),
            new Temperature("Medellin", 15, 40));
  }

  @GET
  @Path("/one")
  @Produces(MediaType.APPLICATION_JSON)
  public Temperature measure() {
    return new Temperature("Lima", 18, 29);
  }

}
