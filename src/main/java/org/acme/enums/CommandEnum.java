package org.acme.enums;

public enum CommandEnum {
    UPLOAD("upload"),
    LIST("list"),
    CREATE_FOLDER("create_folder"),
    DELETE("delete");
    String name;
    CommandEnum(String name) {
    }
    @Override
    public String toString() {
        return name;
    }
}
