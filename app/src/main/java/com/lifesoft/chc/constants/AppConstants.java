package com.lifesoft.chc.constants;

public interface AppConstants {
    String APP_NAME = "Charging Control";
    String GET_BASE_URL = "http://5.9.1.58:8080/chc/";
    String POST_BASE_URL = "http://5.9.1.58:8080/chc/transaction/create";
    String IS_NETWORK_AVAILABLE = "is_network_available";
    String GET_PDU = "get_pdu";
    String MESSAGE_FROM = "message_from";
    String MESSAGE_BODY = "message_body";
    String PDU_BUNDLE_KEY = "pdus";
    int SMS_REQUEST_CODE = 1001;
    int NOTIFICATION_PRIMARY = 1100;
    String NOTIFICATION_TITLE = "Charging Control";
    String NOTIFICATION_CONTENT = "You have a transaction !!!";

}


