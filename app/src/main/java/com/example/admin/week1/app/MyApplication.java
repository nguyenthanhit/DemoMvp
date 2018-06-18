package com.example.admin.week1.app;
/* *
 *  Created by JAY on 18/06/2018
 */

import android.app.Application;

import com.example.admin.week1.helper.DbHelper;
import com.example.admin.week1.util.NetWorkUtil;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DbHelper.newInstance(this);
        NetWorkUtil.setContext(this);
    }
}
