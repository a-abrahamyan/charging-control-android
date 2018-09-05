package com.lifesoft.chc.constants;

public enum CardType {
    ACCOUNT("Account"),
    CREDIT("Credit");
    private String value;

    CardType(String getType) {
        this.value = getType;
    }

    public String getValue() {
        return value;
    }
}