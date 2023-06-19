package org.acme.command;


import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.multipart.FileUpload;

public interface Command {
    Response handle() throws  Exception;
}