package com.lifesoft.chc.listener;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lifesoft.chc.model.CCTransactions;

import java.util.ArrayList;
import java.util.List;

public class CCFilter implements SuccessListener {
    private Activity activity;
    private Context context;
    private View view;
    private FragmentTransaction fragmentTransaction;

    public CCFilter(Context context) {
        this.context = context;
        activity = (Activity) context;
    }

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public CCTransactions sortSuccess(CCTransactions transations, boolean isTrue) {
        String sortValue = String.valueOf(isTrue);
        CCTransactions.Transaction temp;
        int index = 0;
        for (int i = 0; i < transations.getTransactions().size(); i++) {
            if (transations.getTransactions().get(i).getSuccess().equals(sortValue)) {
                temp = transations.getTransactions().get(index);
                transations.getTransactions().set(index, transations.getTransactions().get(i));
                transations.getTransactions().set(i, temp);
                index++;
            }
        }
        CCTransactions list = transations;

        return list;
    }
}
