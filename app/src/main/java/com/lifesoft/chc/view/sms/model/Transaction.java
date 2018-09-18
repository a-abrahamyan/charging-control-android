package com.lifesoft.chc.view.sms.model;

import java.io.Serializable;

public class Transaction implements Serializable {
    private String type;
    private String text;
    private String date;
    private String id;

    public Transaction(String type, String text, String date, String id) {
        this.type = type;
        this.text = text;
        this.date = date;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
