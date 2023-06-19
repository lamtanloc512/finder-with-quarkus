package org.acme.command;

import io.quarkus.runtime.util.StringUtil;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.acme.config.Config;
import org.acme.exception.GeneralException;
import org.acme.system.file.File;
import org.apache.commons.io.FilenameUtils;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;

@RequestScoped
public class Upload extends ExtraCommand {
    private final Logger log = LoggerFactory.getLogger(Upload.class);
    final String[] supportedImageExtensions = {"jpg", "png", "webp"};
    @Inject
    Config config;
    private FileUpload fileUpload;

    public Response handle(FileUpload fileUpload) throws Exception {
        this.fileUpload = fileUpload;
        return handle();
    }

    @Override
    public Response handle() throws Exception {
        if (fileUpload == null) throw new GeneralException("File is not found", 500);
        if (StringUtil.isNullOrEmpty(String.valueOf(config.location())))
            throw new GeneralException("Location is not set", 500);
        String fileExtension = FilenameUtils.getExtension(fileUpload.fileName());
        File file = null;
        Path destination = Path.of(config.location()).resolve(fileUpload.fileName());
        if (!Arrays.asList(supportedImageExtensions).contains(fileExtension)) { // normal file
            try {
                InputStream inputStream = Files.newInputStream(destination);
                Files.copy(inputStream, destination, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                log.error("What is this error: {}", e.toString());
            } finally {
                file = File.FileDTOBuilder.newFileDTOBuilder()
                        .withName(fileUpload.fileName())
                        .withLocation(destination.toString())
                        .withFileSize(fileUpload.size())
                        .withType(fileUpload.contentType())
                        .withBytes(new byte[]{})
                        .build();
            }
            log.info("fileDTO {}", file);
        }
        return file == null ? null : Response.ok(file).build();
    }

    private String generateUniqueFilename(String filename) {
        String baseName = FilenameUtils.getBaseName(filename);
        String extension = FilenameUtils.getExtension(filename);
        return baseName + "_" + UUID.randomUUID().toString() + "." + extension;
    }
}
