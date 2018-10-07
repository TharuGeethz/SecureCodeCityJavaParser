package com.scc.models;

/**
 * @author Tharushi Geethma Abeysinghe
 */
public class IntelliJData {

    private String lineNumber;
    private String filePathToOpen;

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getFilePathToOpen() {
        return filePathToOpen;
    }

    public void setFilePathToOpen(String filePathToOpen) {
        this.filePathToOpen = filePathToOpen;
    }
}
