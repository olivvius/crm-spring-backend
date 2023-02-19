package fr.m2i.apifilrougecrm.response;

import java.util.Calendar;

public class ErrorResponse {

    private Calendar timestamp;
    private String error;
    private int status;
    private String path;

    public ErrorResponse(String error, int status, String path) {
        this.timestamp = Calendar.getInstance();
        this.error = error;
        this.status = status;
        this.path = path;
    }

    public Calendar getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Calendar timestamp) {
        this.timestamp = timestamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
