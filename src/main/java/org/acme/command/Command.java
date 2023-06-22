package org.acme.command;


import jakarta.ws.rs.core.Response;

public interface Command {
    Response handle() throws  Exception;
}