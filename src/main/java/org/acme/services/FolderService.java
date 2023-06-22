package org.acme.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.acme.config.Config;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class FolderService {

    @Inject
    Config config;

    public Uni<Response> handleCreateFolder(String folderName, String action) throws Exception {
        try {
            String newFolderPath = config.location() + "/" + folderName;
            Path folder = Path.of(newFolderPath);
            if (!Files.exists(folder)) {
                Files.createDirectory(folder);
                System.out.println("Folder created successfully.");
            } else {
                Path destination = Path.of(config.location()).resolve("New Folder");
                Files.createDirectory(destination);
                System.out.println("Folder already exists.");
            }
            return Uni.createFrom().item(Response.ok("Folder created").build());
        } catch (IOException e) {
            System.out.println(e);
            throw new IOException(e);
        }
    }

}
