package org.acme.endpoint;

import io.quarkus.runtime.util.StringUtil;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.inject.Any;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.command.Command;
import org.acme.command.CreateFolder;
import org.acme.command.GetFiles;
import org.acme.command.Upload;
import org.acme.enums.CommandEnum;
import org.acme.exception.GeneralException;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.util.Optional;

@Path("/finder")
public class FinderHanlderEndpoint {
    @Inject
    Upload upload;
    @Inject
    GetFiles getFiles;
    @Inject
    CreateFolder createFolder;

    @POST
    @Path("/{action}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> uploadHandler(@RestPath("action") String action, @RestForm("file") FileUpload file) throws Exception {
        if (StringUtil.isNullOrEmpty(action)) throw new GeneralException("No action in request", 404);
        try {
            switch (CommandEnum.valueOf(action.toUpperCase())) {
                case UPLOAD -> {
                    return Uni.createFrom().item(upload.handle(file));
                }
                case LIST -> {
                    return Uni.createFrom().item(getFiles.handle());
                }
                case CREATE_FOLDER -> {
                    return Uni.createFrom().item(createFolder.handle());
                }
                default -> { return  Uni.createFrom().item(Response.status(Response.Status.BAD_REQUEST)
                        .entity("Invalid request!").build()); }
            }
        } catch (Exception e) {
            throw new GeneralException("No match action!", 404);
        }
    }

}
