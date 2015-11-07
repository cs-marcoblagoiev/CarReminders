package com.mmproduction.abcd.carreminders;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
//http://www.banane.com/2014/05/07/simple-example-of-scheduled-self-clearing-android-notifications/
public class AlarmReceiver extends BroadcastReceiver {

    NotificationManager notificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        String type="", licence="", period="";

        if (bundle != null) {
            type = bundle.getString("type");
            licence = bundle.getString("licence");
            period = bundle.getString("period");
        }
        Intent service1 = new Intent(context, AlarmService.class);
        service1.putExtra("type", type);
        service1.putExtra("licence", licence);
        service1.putExtra("period", period);

        context.startService(service1);
        Log.d("DebugAlarm", "In AlarmReceiver");
    }
}