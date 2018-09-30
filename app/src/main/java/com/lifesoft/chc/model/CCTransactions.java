package com.lifesoft.chc.model;

import java.io.Serializable;
import java.util.List;

public class CCTransactions implements Serializable {
    private String count;
    private List<CCTransactions.Transaction> transactions;

    public CCTransactions(String count, List<CCTransactions.Transaction> transactions) {
        this.count = count;
        this.transactions = transactions;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<CCTransactions.Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<CCTransactions.Transaction> transactions) {
        this.transactions = transactions;
    }
  public   class Transaction {
        private String date;
        private String type;
        private String amount;
        private String before;
        private String after;
        private String success;
        private String id;

        public Transaction() {
        }

        public Transaction(String date, String type, String amount, String before, String after, String success, String id) {
            this.date = date;
            this.type = type;
            this.amount = amount;
            this.before = before;
            this.after = after;
            this.success = success;
            this.id = id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getBefore() {
            return before;
        }

        public void setBefore(String before) {
            this.before = before;
        }

        public String getAfter() {
            return after;
        }

        public void setAfter(String after) {
            this.after = after;
        }

        public String getSuccess() {
            return success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
