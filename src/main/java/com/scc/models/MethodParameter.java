package com.scc.models;

public class MethodParameter {

    private String methodName;
    private String startingLine;
    private String endingLine;

    public MethodParameter() {
    }

    public MethodParameter(String methodName, String startingLine, String endingLine) {
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
