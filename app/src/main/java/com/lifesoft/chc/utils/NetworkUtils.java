package com.lifesoft.chc.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class NetworkUtils {
    private Activity activity;
    private Context context;

    public NetworkUtils(Context context) {
        this.context = context;
        activity = (Activity) context;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public boolean isInternetConnection() {
        ConnectivityManager connMgr =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isWifiConn = false;
        boolean isMobileConn = false;
        for (Network network : connMgr.getAllNetworks()) {
            NetworkInfo networkInfo = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                networkInfo = connMgr.getNetworkInfo(network);
            } else {
                //TODO: need check < LOLLIPOP version
            }
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                isWifiConn |= networkInfo.isConnected();
            }
            if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                isMobileConn |= networkInfo.isConnected();
            }
        }
        return isMobileConn || isWifiConn;
    }
}
