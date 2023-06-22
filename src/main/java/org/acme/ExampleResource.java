package org.acme;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Path("/hello")
public class ExampleResource {

    @Inject
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @GET
    @Path("reactive")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> helloWorldReactive() {
        return Uni.createFrom()
                .completionStage(CompletableFuture.supplyAsync(() -> Map.of("payload", "Hello")))
                .onItem()
                .transform(x -> Response.ok(x).build());
    }
}
