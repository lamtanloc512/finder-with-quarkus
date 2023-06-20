package org.acme.system.file;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Folder extends Node implements Serializable{
    private boolean hasChild;
    private List<File> files;
    private Folder child;

    public String getName() {
        return this.name;
    }

    public Folder setName(String name) {
        this.name = name;
        return this;
    }

}
