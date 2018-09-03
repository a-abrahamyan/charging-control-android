package com.lifesoft.chc.sms.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.lifesoft.chc.AppConstants;
import com.lifesoft.chc.activity.MainActivity;
import com.lifesoft.chc.sms.model.SmsModel;
import com.lifesoft.chc.sms.model.SmsObject;

import java.util.Date;
import java.util.Objects;

public class GetSmsReceiver extends BroadcastReceiver {

    private static String TAG = GetSmsReceiver.class.getName();
    private SmsMessage smsMessage;
    private SmsObject smsObject;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive:---------> opened");
        smsObject = SmsObject.INSTANCE();
        Bundle pudsBundle = intent.getExtras();
        Object[] pduObject = (Object[]) pudsBundle.get(AppConstants.PDU_BUNDLE_KEY);
        byte[] encodePduObject = (byte[]) Objects.requireNonNull(pduObject)[0];
        smsMessage = SmsMessage.createFromPdu(encodePduObject);
        if (smsMessage != null) {
            goToActivity(context, smsMessage);
        }
    }

    private void goToActivity(Context context, SmsMessage smsMessage) {
        Intent smsIntent = new Intent(context, MainActivity.class);
        smsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        smsIntent.putExtra(AppConstants.MESSAGE_FROM, smsMessage.getOriginatingAddress());
        smsIntent.putExtra(AppConstants.MESSAGE_BODY, smsMessage.getMessageBody());
        if (smsMessage.getDisplayOriginatingAddress().equals(AppConstants.BANK_CARD_TYPE[0]) || smsMessage.getDisplayOriginatingAddress().equals(AppConstants.BANK_CARD_TYPE[1])) {
            Date date = new Date(smsMessage.getTimestampMillis());
            SmsModel smsModel = new SmsModel(smsMessage.getDisplayOriginatingAddress(), smsMessage.getMessageBody(), date);
            smsObject.setSmsModel(smsModel);
            context.startActivity(smsIntent);
            Log.i(TAG, "onReceive:---------> go to MainActivity");
        } else {
            Log.i(TAG, "goToActivity: Card type is false");
        }
    }

}

