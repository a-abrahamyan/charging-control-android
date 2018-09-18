package com.lifesoft.chc.view.sms.model;

import java.io.Serializable;
import java.util.List;

public class SmsModel implements Serializable {
    private List<Transaction> transactions = null;

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
