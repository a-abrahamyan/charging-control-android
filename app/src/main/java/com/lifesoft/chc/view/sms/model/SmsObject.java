package com.lifesoft.chc.view.sms.model;

public class SmsObject {
    private static SmsObject INSTANCE = null;
    private SmsModel smsModel;


    public SmsModel getSmsModel() {
        return smsModel;
    }

    public void setSmsModel(SmsModel smsModel) {
        this.smsModel = smsModel;
    }

    private SmsObject() {
    }

    public static SmsObject INSTANCE() {
        return INSTANCE == null ? INSTANCE = new SmsObject() : INSTANCE;
    }
}
