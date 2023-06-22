package org.acme.endpoint;

import org.acme.exception.GeneralException;
import org.acme.services.BrowseService;
import org.acme.services.FolderService;
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
public class FinderHandlerEndpoint {
    private static final Logger log = LoggerFactory.getLogger(FinderHandlerEndpoint.class);

    @Inject
    UploadService uploadService;

    @Inject
    BrowseService browseService;

    @Inject
    FolderService folderService;

    @POST
    @Path("/upload")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<?> uploadHandler(@RestForm("file") FileUpload file) throws Exception {
        try {
            return Uni.createFrom().item(uploadService.handleSingleUpload(file));
        } catch (Exception e) { //
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

    @POST
    @Path("/folder")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<?> folderHandler(@QueryParam("folderName") String folderName,
            @QueryParam("action") String action) throws Exception {

        log.info("folder handler: {}", action);
        return folderService.handleCreateFolder(folderName, action);
        
    }

}
