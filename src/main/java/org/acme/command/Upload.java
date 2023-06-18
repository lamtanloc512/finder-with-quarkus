package org.acme.command;

import io.quarkus.runtime.util.StringUtil;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.acme.config.Config;
import org.acme.dto.FileDTO;
import org.acme.exception.GeneralException;
import org.apache.commons.io.FilenameUtils;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestScoped
public class Upload implements Command {
    private final Logger log = LoggerFactory.getLogger(Upload.class);
    final String[] supportedImageExtensions = {"jpg", "png", "webp"};
    @Inject
    Config config;

    @Override
    public RestResponse<?> handle(FileUpload fileUpload) throws Exception {
        if (fileUpload == null) throw new GeneralException("File is not found", 500);
        if (StringUtil.isNullOrEmpty(String.valueOf(config.location())))
            throw new GeneralException("Location is not set", 500);
        String fileExtension = FilenameUtils.getExtension(fileUpload.fileName());
        FileDTO fileDTO = null;
        Path destination = Path.of(config.location()).resolve(fileUpload.fileName());
        if (!Arrays.asList(supportedImageExtensions).contains(fileExtension)) { // normal file
            try {
                InputStream inputStream = Files.newInputStream(fileUpload.filePath());
                if (Files.exists(destination)) {
                     Files.copy(inputStream, destination, StandardCopyOption.REPLACE_EXISTING);
                }
                Files.copy(inputStream, destination);
            } catch (Exception e) {
                log.error("What is this error: {}", e.toString());
            } finally {
                fileDTO = FileDTO.FileDTOBuilder.newFileDTOBuilder()
                        .withName(fileUpload.fileName())
                        .withLocation(destination.toString())
                        .withFileSize(fileUpload.size())
                        .withType(fileUpload.contentType())
                        .withBytes(new byte[]{})
                        .build();
            }


            log.info("fileDTO {}", fileDTO);

        }
        return RestResponse.ok(fileDTO);
    }


    private String generateUniqueFilename(String filename) {
        String baseName = FilenameUtils.getBaseName(filename);
        String extension = FilenameUtils.getExtension(filename);
        String uniqueName = baseName + "_" + UUID.randomUUID().toString() + "." + extension;
        return uniqueName;
    }
}
