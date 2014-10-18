package com.solutions.nimbus.doorbell;

/**
 * Created by Arwen on 10/18/2014.
 */
public class DoorbellEntry {
    private long id;
    private String date;
    private String message;


    public DoorbellEntry() {
    }

    public DoorbellEntry(String date, String message) {
        this.date = date;
        this.message = message;
    }

    public DoorbellEntry(long id, String date, String message) {
        this.id = id;
        this.date = date;
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(date ).append("\n").append(message);

        return sb.toString();
    }
}
