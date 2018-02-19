package models;

import java.io.Serializable;

public class ResponseMessage implements Serializable {

    static final long serialVersionUID = -6879914928840503L;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
