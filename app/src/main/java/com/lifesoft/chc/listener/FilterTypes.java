package com.lifesoft.chc.listener;

public enum FilterTypes {
    //types
    SUCCESS("success"),
    DATE("date"),
    // success
    SUCCESS_NONE("None"),
    SUCCESS_TRUE("True"),
    SUCCESS_FALSE("False"),
    SUCCESS_BUNDLE_KEY("success.bundle.key"),
    // date
    DATE_NON("None"),
    DATE_ASC("Asc"),
    DATE_DESC("Desc"),
    DATE_BUNDLE_KEY("date.bundle.key");

    private String value;

    FilterTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
