package org.acme.system.file;

import java.io.Serializable;

public abstract class Node implements Serializable {
    protected String name;
    protected String type;

    public Node() {
    }

    public abstract String getName();

    public abstract String getType();

}