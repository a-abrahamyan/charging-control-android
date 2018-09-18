package com.lifesoft.chc.view.sms.model;

import com.lifesoft.chc.model.CCTransactions;

public class SmsObject {
    private static SmsObject INSTANCE = null;
    private SmsModel smsModel;
    private CCTransactions ccTransactions;
    public SmsModel getSmsModel() {
        return smsModel;
    }
    public void setSmsModel(SmsModel smsModel) {
        this.smsModel = smsModel;
    }

    public CCTransactions getCcTransactions() {
        return ccTransactions;
    }

    public void setCcTransactions(CCTransactions ccTransactions) {
        this.ccTransactions = ccTransactions;
    }

    private SmsObject() {
    }

    public static SmsObject INSTANCE() {
        return INSTANCE == null ? INSTANCE = new SmsObject() : INSTANCE;
    }
}
