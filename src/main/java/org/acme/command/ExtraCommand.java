package org.acme.command;

import org.jboss.resteasy.reactive.multipart.FileUpload;

import jakarta.ws.rs.core.Response;

public abstract class ExtraCommand implements Command {
    public abstract Response handle(FileUpload fileUpload) throws Exception;
    public abstract Response handle() throws Exception;
}
