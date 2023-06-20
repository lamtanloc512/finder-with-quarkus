package org.acme.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class BrowseService {

    public Uni<?> handleBrowse(String location) throws Exception {
        Path folder = Paths.get(location);
        List<String> finalResponse = new ArrayList<>();
        if (Files.exists(folder) && Files.isDirectory(folder)) {
            try (Stream<Path> streamFolder = Files.walk(folder)) {
                streamFolder.filter(x -> Files.isRegularFile(x) && !x.getFileName().toString().endsWith(".cache"))
                        .forEach(x -> {
                            finalResponse.add(x.toFile().getName());
                        });
            } catch (Exception e) {
                System.out.println("Failed to list files: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid folder path or folder does not exist.");
        }
        return Uni.createFrom().item(finalResponse);
    }
}
