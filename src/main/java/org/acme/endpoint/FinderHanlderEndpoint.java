package org.acme.endpoint;

import org.acme.command.CreateFolder;
import org.acme.exception.GeneralException;
import org.acme.services.BrowseService;
import org.acme.services.UploadService;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/finder")
public class FinderHanlderEndpoint {
    private static final Logger log = LoggerFactory.getLogger(FinderHanlderEndpoint.class);

    @Inject
    UploadService uploadService;

    @Inject
    BrowseService browseService;
    
    @Inject
    CreateFolder createFolder;

    @POST
    @Path("/upload")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<?> uploadHandler(@RestForm("file") FileUpload file) throws Exception {
        try {
            return Uni.createFrom().item(uploadService.handleSingleUpload(file));
        } catch (Exception e) {
            throw new GeneralException("No match action!", 404);
        }
    }

    @POST
    @Path("/browse")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<?> browseHandler(@QueryParam("location") String location) throws Exception {
        log.info("browse handler: {}", location);
        return browseService.handleBrowse(location);
    }

}
