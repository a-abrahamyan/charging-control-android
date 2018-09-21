package com.lifesoft.chc.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class CCAnimation {
    private static String TAG = CCAnimation.class.getName();
    private Activity activity;
    private Context context;

    public CCAnimation(Context context) {
        this.context = context;
        activity = (Activity) context;
    }
    public void splashAnimation(Context context, View view,int animPath){
        Animation splashAnimation = AnimationUtils.loadAnimation(context, animPath);
        splashAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i(TAG, "onAnimationStart: ");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i(TAG, "onAnimationEnd: ");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.i(TAG, "onAnimationRepeat: ");
            }
        });
        view.startAnimation(splashAnimation);
    }
}
