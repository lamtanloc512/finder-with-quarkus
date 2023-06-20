package org.acme.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import org.acme.config.Config;
import org.acme.exception.GeneralException;
import org.acme.system.file.File;
import org.apache.commons.io.FilenameUtils;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.runtime.util.StringUtil;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@RequestScoped
public class UploadService {

    private final Logger log = LoggerFactory.getLogger(UploadService.class);

    private static final String[] supportedImageExtensions = { "jpg", "png", "webp" };

    @Inject
    Config config;

    public Response handleSingleUpload(FileUpload fileUpload)
            throws IllegalArgumentException, GeneralException, IOException {
        if (fileUpload == null)
            throw new GeneralException("File is not found", 500);

        if (StringUtil.isNullOrEmpty(String.valueOf(config.location())))
            throw new GeneralException("Location is not set", 500);

        String fileExtension = FilenameUtils.getExtension(fileUpload.fileName());
        File file = null;
        Path destination = Path.of(config.location()).resolve(fileUpload.fileName());

        if (!Arrays.asList(supportedImageExtensions).contains(fileExtension)) { // normal file
            try {
                InputStream inputStream = Files.newInputStream(fileUpload.filePath());
                if (Files.exists(destination)) {
                    String newFilename = generateUniqueFilename(fileUpload.fileName());
                    destination = Path.of(config.location()).resolve(newFilename);
                }
                Files.copy(inputStream, destination);
            } catch (IOException e) {
                log.error("What is this error: {}", e.getMessage());
                throw new IOException("Error at process copy file to disk " + e.getMessage().toString());
            } finally {
                file = File.FileDTOBuilder.newFileDTOBuilder()
                        .withName(fileUpload.fileName())
                        .withLocation(destination.toString())
                        .withFileSize(fileUpload.size())
                        .withType(fileUpload.contentType())
                        .withBytes(new byte[] {})
                        .build();
                log.info("fileDTO {}", file);
            }
        }
        return Response.ok(Optional.of(file)).build();
    }

    private String generateUniqueFilename(String filename) {
        String baseName = FilenameUtils.getBaseName(filename);
        String extension = FilenameUtils.getExtension(filename);
        String uniqueName = baseName + "_" + UUID.randomUUID().toString() + "." + extension;
        return uniqueName;
    }

}
