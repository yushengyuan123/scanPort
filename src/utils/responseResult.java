package utils;

import java.util.List;

public class responseResult {
    private String code;
    private String message;
    private final List<?> data;

    public responseResult(String code, String message, List<?> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<?> getData() {
        return data;
    }

}
