package com.scc.models;

public class MethodParams {

    private String methodName;
    private String startingLine;
    private String endingLine;

    public MethodParams() {
    }

    public MethodParams(String methodName, String startingLine, String endingLine) {
        this.methodName = methodName;
        this.startingLine = startingLine;
        this.endingLine = endingLine;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getStartingLine() {
        return startingLine;
    }

    public void setStartingLine(String startingLine) {
        this.startingLine = startingLine;
    }

    public String getEndingLine() {
        return endingLine;
    }

    public void setEndingLine(String endingLine) {
        this.endingLine = endingLine;
    }
}
