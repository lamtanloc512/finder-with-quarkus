package org.acme.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.acme.config.Config;
import org.acme.enums.NodeType;
import org.acme.system.file.File;
import org.acme.system.file.Folder;
import org.acme.system.file.Node;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class BrowseService {
    @Inject
    Config config;

    public Uni<?> handleBrowse(String location) throws IOException {
        Path folder = Paths.get(location);
        Map<String, List<Node>> finalResponse = new HashMap<>();
        if (Files.exists(folder) && Files.isDirectory(folder)) {
            try (Stream<Path> streamFolder = Files.list(folder)) {
                finalResponse = streamFolder.map(Path::toFile)
                        .map(y -> {
                            if (y.isDirectory()) {
                                var newFolder = new Folder();
                                newFolder.setType(NodeType.FOLDER.toString());
                                newFolder.setName(y.getName());
                                return newFolder;
                            } else if (y.isFile()) {
                                var newFile = new File();
                                newFile.setType(NodeType.FILE.toString());
                                return newFile;
                            }
                            return null;
                        })
                        .collect(Collectors.groupingBy(y -> y != null ? y.getType() : NodeType.FILE.toString()));

                // var listFolder = streamFolder
                // .map(Path::toFile)
                // .filter(x -> (!x.getName().startsWith(".") &&
                // !x.getName().endsWith(".cache")) || !x.isHidden())
                // .filter(java.io.File::isDirectory)
                // .map(el -> {
                // var folderName = el.getName();
                // Folder newFolder = Folder.builder().build();
                // newFolder.setName(folderName);
                // newFolder.setType(NodeType.FOLDER.toString());
                // return newFolder;
                // }).toList();
                // try (Stream<Path> anotherStreamFolder = Files.list(folder)) {
                // var listFile = anotherStreamFolder
                // .map(Path::toFile)
                // .filter(x -> (!x.getName().startsWith(".") &&
                // !x.getName().endsWith(".cache"))
                // || !x.isHidden())
                // .filter(java.io.File::isFile)
                // .map(el -> {
                // try {
                // var newFile = File.builder()
                // .name(el.getName())
                // .bytes(new byte[] {})
                // .fileSize(Files.size(el.toPath()))
                // .location(el.toPath().toString())
                // .build();
                // newFile.setType(NodeType.FILE.toString());
                // return newFile;
                // } catch (IOException e) {
                // e.printStackTrace();
                // }
                // return null;
                // })
                // .toList();

                // finalResponse = Stream.concat(listFolder.stream(),
                // listFile.stream()).toList();

            } catch (IOException e) {
                System.out.println("Failed to list files: " + e.getMessage());
                throw new IOException("Failed to list files: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid folder path or folder does not exist. JAVA");
            return Uni.createFrom()
                    .completionStage(CompletableFuture.supplyAsync(() -> Map.of("Error", "Folder doesn't exists!")))
                    .onItem()
                    .transform(x -> Response.ok(x).build());
        }
        return Uni.createFrom().item(finalResponse);
    }

}
