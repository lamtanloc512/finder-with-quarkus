package org.acme.system.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class File extends Node {
    String name;
    String location;
    String type;
    byte[] bytes;
    double fileSize;
}
