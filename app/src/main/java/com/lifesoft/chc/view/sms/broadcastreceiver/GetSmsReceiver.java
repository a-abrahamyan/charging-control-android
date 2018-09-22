package com.lifesoft.chc.view.sms.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.lifesoft.chc.constants.AppConstants;
import com.lifesoft.chc.constants.CardType;
import com.lifesoft.chc.database.engine.DBEngine;
import com.lifesoft.chc.utils.NetworkUtils;
import com.lifesoft.chc.view.activity.MainActivity;
import com.lifesoft.chc.view.sms.model.SmsModel;
import com.lifesoft.chc.view.sms.model.SmsObject;
import com.lifesoft.chc.view.sms.model.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GetSmsReceiver extends BroadcastReceiver {

    private static String TAG = GetSmsReceiver.class.getName();
    private SmsMessage smsMessage;
    private SmsObject smsObject;
    private DBEngine engine;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive:---------> opened");
        smsObject = SmsObject.INSTANCE();
        engine = DBEngine.INSTANCE();
        Bundle pudsBundle = intent.getExtras();
        Object[] pduObject = (Object[]) pudsBundle.get(AppConstants.PDU_BUNDLE_KEY);
        byte[] encodePduObject = (byte[]) Objects.requireNonNull(pduObject)[0];
        smsMessage = SmsMessage.createFromPdu(encodePduObject);
        if (smsMessage != null) {
            createSmsModel(context, smsMessage);
        }
    }

    private void createSmsModel(Context context, SmsMessage smsMessage) {
        Intent smsIntent = new Intent(context, MainActivity.class);
        smsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (smsMessage.getDisplayOriginatingAddress().equals("NOY") || smsMessage.getDisplayOriginatingAddress().equals(CardType.ACCOUNT)) {
            //create post data
            String type = smsMessage.getDisplayMessageBody().equals(CardType.CREDIT.getValue()) ? CardType.CREDIT.getValue() : CardType.ACCOUNT.getValue();
            String messageBody = smsMessage.getMessageBody();
            String date = String.valueOf(smsMessage.getTimestampMillis());
            String id = String.valueOf(smsMessage.getTimestampMillis());
            SmsModel smsModel = new SmsModel();
            List<Transaction> transaction = new ArrayList<>();
            transaction.add(new Transaction(type, messageBody, date, id));
            smsModel.setTransactions(transaction);
            smsObject.setSmsModel(smsModel);
            // check internet connection
            if (NetworkUtils.isConnected(context)) {
                smsIntent.putExtra(AppConstants.IS_NETWORK_AVAILABLE, "true");
            } else {
                smsIntent.putExtra(AppConstants.IS_NETWORK_AVAILABLE, "false");
            }
            context.startActivity(smsIntent);
        } else {
            Log.i(TAG, "goToActivity: Card type is false");
        }
    }

}

