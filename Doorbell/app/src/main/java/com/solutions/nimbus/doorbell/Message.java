package com.solutions.nimbus.doorbell;

/**
 * Created by Arwen on 10/19/2014.
 */
public class Message {
    private String message;
    private String lang;

    public Message() {
    }

    public Message(String message, String lang) {
        this.message = message;
        this.lang = lang;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
