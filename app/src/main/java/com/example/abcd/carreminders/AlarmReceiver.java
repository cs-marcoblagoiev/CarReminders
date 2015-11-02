package com.example.abcd.carreminders;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
//http://www.banane.com/2014/05/07/simple-example-of-scheduled-self-clearing-android-notifications/
public class AlarmReceiver extends BroadcastReceiver {

    NotificationManager notificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service1 = new Intent(context, AlarmService.class);
        context.startService(service1);
        Log.d("DebugAlarm", "In AlarmReceiver");
    }
}