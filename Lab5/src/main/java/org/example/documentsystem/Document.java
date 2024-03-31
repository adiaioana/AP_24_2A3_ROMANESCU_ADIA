package org.example.documentsystem;

public record Document(String name) {
    static String fileType;

    public String getFileType() {
        return name.substring(name.indexOf('.')+1);
    }

    public String getName() {
        return name.substring(0,name.indexOf('.'));
    }
}