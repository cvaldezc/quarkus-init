package io.chris.quarkus;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import java.util.Optional;

@Path("/greeting")
public class EcoResource {


  @GET
  public String greeting(@QueryParam("message") String message) {
    return Optional.ofNullable(message).orElse("You not know");
  }

  @GET
  @Path("/{name}")
  public String greetingName(@PathParam("name") String name) {
    return "Hello, " + name;
  }

  @GET
  @Path("/morning")
  public String morning() {
    return "good morning";
  }

  @GET
  @Path("/afternoon")
  public String afternoon() {
    return "good afternoon";
  }
  
}
