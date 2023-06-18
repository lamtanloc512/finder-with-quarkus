package org.acme.endpoint;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.command.Command;
import org.acme.exception.GeneralException;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.multipart.FileUpload;

@Path("/finder")
public class FinderHanlderEndpoint {
    @Inject
    Command command;

    @POST
    @Path("/upload")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> uploadHandler(@RestForm("file") FileUpload file) throws Exception {
        try (RestResponse<?> restResponse = command.handle(file)) {
            return Uni.createFrom().item(restResponse.toResponse());
        } catch (Exception e) {
            throw new GeneralException("Upload failed", 500);
        }
    }
}
