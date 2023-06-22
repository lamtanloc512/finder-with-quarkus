package org.acme.enums;

public enum NodeType {
    FILE("file"), FOLDER("folder");

    String name;

    NodeType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
