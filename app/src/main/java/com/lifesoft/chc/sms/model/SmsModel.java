package com.lifesoft.chc.sms.model;

public class SmsModel {
    private String messageType;
    private String messageBody;
    private Object data;

    public SmsModel(String messageType, String messageBody, Object data) {
        this.messageType = messageType;
        this.messageBody = messageBody;
        this.data = data;
    }

    public String getMessageType() {
        return messageType;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return "SmsModel{" +
                "messageType='" + messageType + '\'' +
                ", messageBody='" + messageBody + '\'' +
                ", data=" + data +
                '}';
    }
}
