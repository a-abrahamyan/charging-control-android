package com.lifesoft.chc.listener;

public enum FilterTypes {
    SUCCESS_NONE("None"),
    SUCCESS_TRUE("True"),
    SUCCESS_FALSE("False"),
    SUCCESS_BUNDLE_KEY("success.bundle.key");

    private String value;

    FilterTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
