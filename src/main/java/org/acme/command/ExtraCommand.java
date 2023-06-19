package org.acme.command;

import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.multipart.FileUpload;

public abstract class ExtraCommand implements Command {
    public abstract Response handle(FileUpload fileUpload) throws Exception;
    public abstract Response handle() throws Exception;
}
