package com.lifesoft.chc.listener;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.provider.Telephony;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.lifesoft.chc.model.CCTransactions;
import com.lifesoft.chc.view.sms.model.SmsObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CCFilter implements SuccessListener, DateListener {
    private static final String TAG = CCFilter.class.getSimpleName();
    private Activity activity;
    private Context context;
    private View view;
    private SmsObject smsObject;

    public CCFilter(Context context) {
        this.context = context;
        activity = (Activity) context;
        smsObject = SmsObject.INSTANCE();
    }

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public CCTransactions sortSuccess(CCTransactions transations, String successType) {
        if (successType.equals(FilterTypes.SUCCESS_NONE.getValue())) {
            return smsObject.getCcTransactions();
        }
        CCTransactions.Transaction temp;
        int index = 0;
        for (int i = 0; i < transations.getTransactions().size(); i++) {
            if (transations.getTransactions().get(i).getSuccess().equals(successType.toLowerCase())) {
                temp = transations.getTransactions().get(index);
                transations.getTransactions().set(index, transations.getTransactions().get(i));
                transations.getTransactions().set(i, temp);
                index++;
            }
        }
        CCTransactions list = transations;

        return list;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public CCTransactions sortDate(CCTransactions transation, String dateType) {
        // Bubble Sort
        try {
            if (dateType.equals(FilterTypes.DATE_NON.getValue())) {
                return smsObject.getCcTransactions();
            } else if (dateType.equals(FilterTypes.DATE_ASC.getValue())) {
                CCTransactions.Transaction temp = null;
                for (int i = 0; i < transation.getTransactions().size() - 1; i++) {
                    for (int j = 0; j < transation.getTransactions().size() - i - 1; j++) {
                        int index1 = transation.getTransactions().get(j).getDate().indexOf('-');
                        int index2 = transation.getTransactions().get(j + 1).getDate().indexOf('-');
                        String date1 = transation.getTransactions().get(j).getDate();
                        String date2 = transation.getTransactions().get(j + 1).getDate();
                        int firstDate = Integer.parseInt(date1.substring(0, index1) + date1.substring(index1 + 1, index1 + 3));
                        int lastDate = Integer.parseInt(date2.substring(0, index2) + date2.substring(index2 + 1, index2 + 3));
                        temp = transation.getTransactions().get(j);
                        if (firstDate > lastDate) {
                            transation.getTransactions().set(j, transation.getTransactions().get(j + 1));
                            transation.getTransactions().set(j + 1, temp);
                        }
                    }
                }
            } else if (dateType.equals(FilterTypes.DATE_DESC.getValue())) {
                CCTransactions.Transaction temp = null;
                for (int i = 0; i < transation.getTransactions().size() - 1; i++) {
                    for (int j = 0; j < transation.getTransactions().size() - i - 1; j++) {
                        int index1 = transation.getTransactions().get(j).getDate().indexOf('-');
                        int index2 = transation.getTransactions().get(j + 1).getDate().indexOf('-');
                        String date1 = transation.getTransactions().get(j).getDate();
                        String date2 = transation.getTransactions().get(j + 1).getDate();
                        int firstDate = Integer.parseInt(date1.substring(0, index1) + date1.substring(index1 + 1, index1 + 3));
                        int lastDate = Integer.parseInt(date2.substring(0, index2) + date2.substring(index2 + 1, index2 + 3));
                        temp = transation.getTransactions().get(j);
                        if (firstDate < lastDate) {
                            transation.getTransactions().set(j, transation.getTransactions().get(j + 1));
                            transation.getTransactions().set(j + 1, temp);
                        }
                    }
                }
            }
        } catch (NumberFormatException e) {
            Log.i(TAG, "sortDate: ERROR=======>>>>>> " + e.getLocalizedMessage());
            return transation;
        }
        return transation;
    }
}
