package com.example.admin.week1.util;
/* *
 *  Created by JAY on 18/06/2018
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetWorkUtil {
    private static Context context;

    public static void setContext(Context context) {
        NetWorkUtil.context = context;
    }

    public static boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo;
        if (cm != null) {
            netInfo = cm.getActiveNetworkInfo();
            return  netInfo!= null && netInfo.isConnectedOrConnecting();
        }
        return false;
    }
}
