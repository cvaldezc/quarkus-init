package io.chris;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/message")
public class MessageResource {

    @RestClient
    MessageClient client;

    @GET
    @Timeout(200)
    /*@CircuitBreaker(
            requestVolumeThreshold = 4,
            failureRatio = 0.5,
            delay = 8000,
            successThreshold = 2
    )*/
    @Retry(maxRetries = 5)
    @Fallback(fallbackMethod = "fallbackMessage")
    public String message() {
        return client.get();
    }

    private String fallbackMessage() {
        return "Fallback message";
    }
}
