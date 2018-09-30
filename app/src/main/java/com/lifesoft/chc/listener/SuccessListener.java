package com.lifesoft.chc.listener;

import com.lifesoft.chc.model.CCTransactions;

import java.util.ArrayList;
import java.util.List;

public interface SuccessListener {
    CCTransactions sortSuccess(CCTransactions transation, boolean isTrue);

}
