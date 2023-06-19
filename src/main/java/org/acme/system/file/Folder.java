package org.acme.system.file;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Folder extends Node {
    private boolean hasChild;
    public String getName() {
        return this.name;
    }
    public Folder setName(String name) {
        this.name = name;
        return this;
    }
    public boolean isHasChild() {
        return hasChild;
    }

    public Folder setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
        return this;
    }
}
