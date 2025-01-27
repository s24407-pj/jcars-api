package edu.pjatk.jcarsapi.model.response;

public class ApiResponse {
    private boolean isSuccess;
    private String message;
    private Object data;

    public ApiResponse(boolean isSuccess, String message, Object data) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.data = data;
    }

    // Getters and setters for isSuccess, message, and data
    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    static class ApiException extends Exception {
        public ApiException(String message) {
            super(message);
        }
    }
}
