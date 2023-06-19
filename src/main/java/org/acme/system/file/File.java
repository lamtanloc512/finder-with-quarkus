package org.acme.system.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class File extends Node implements Serializable {
    String name;
    String location;
    String type;
    byte[] bytes;
    double fileSize;
    public static final class FileDTOBuilder {
        private String name;
        private String location;
        private String type;
        private byte[] bytes;
        private double fileSize;

        private FileDTOBuilder() {
        }

        public static FileDTOBuilder newFileDTOBuilder() {
            return new FileDTOBuilder();
        }
        public FileDTOBuilder withName(String name) {
            this.name = name;
            return this;
        }
        public FileDTOBuilder withLocation(String location) {
            this.location = location;
            return this;
        }
        public FileDTOBuilder withType(String type) {
            this.type = type;
            return this;
        }
        public FileDTOBuilder withBytes(byte[] bytes) {
            this.bytes = bytes;
            return this;
        }
        public FileDTOBuilder withFileSize(double fileSize) {
            this.fileSize = fileSize;
            return this;
        }
        public File build() {
            File file = new File();
            file.fileSize = this.fileSize;
            file.type = this.type;
            file.bytes = this.bytes;
            file.location = this.location;
            file.name = this.name;
            return file;
        }
    }
}
