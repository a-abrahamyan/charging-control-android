package com.lifesoft.chc.utils;

import android.app.Activity;
import android.content.Context;

public class CCAnimation {
    private Activity activity;
    private Context context;

    public CCAnimation(Context context) {
        this.context = context;
        activity = (Activity) context;
    }
}
