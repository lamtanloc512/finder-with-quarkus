package org.acme.command;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.acme.config.Config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequestScoped
public class CreateFolder implements Command {
    @Inject
    Config config;

    @Override
    public Response handle() throws Exception {
        try {
            String folderPath = config.location();
            Path folder = Paths.get(folderPath);
            if (!Files.exists(folder)) {
                Files.createDirectory(folder);
                System.out.println("Folder created successfully.");
            } else {
                Path destination = Path.of(config.location()).resolve("New Folder");
                Files.createDirectory(destination);
                System.out.println("Folder already exists.");
            }
            return Response.ok("Folder created").build();
        } catch (IOException e) {
            System.out.println(e);
            throw new IOException(e);
        }
    }
}
