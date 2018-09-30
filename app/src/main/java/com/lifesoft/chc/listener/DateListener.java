package com.lifesoft.chc.listener;

import com.lifesoft.chc.model.CCTransactions;

public interface DateListener {
    CCTransactions sortDate(CCTransactions transation, String dateType);
}
