package com.when.threemb.when;

/**
 * Created by User on 9/24/2016.
 */
public class MessageObj {
    private String username;
    private String message;

    public MessageObj(String message, String username) {
        this.message = message;
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
