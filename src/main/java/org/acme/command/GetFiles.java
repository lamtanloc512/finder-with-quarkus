package org.acme.command;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestScoped
public class GetFiles implements Command {
    private final Logger log = LoggerFactory.getLogger(GetFiles.class);
    @Override
    public Response handle() throws Exception {
        log.info("List file command");
        return Response.ok("Hello WOrld").build();
    }
}
