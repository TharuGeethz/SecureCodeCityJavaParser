package com.scc.common;

public class ErrorResponse {
    private int httpStatusCode;
    private String httpStatus;
    private String errorDescription;

    public ErrorResponse(int httpStatusCode, String httpStatus, String errorDescription) {
        this.httpStatusCode = httpStatusCode;
        this.httpStatus = httpStatus;
        this.errorDescription = errorDescription;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
