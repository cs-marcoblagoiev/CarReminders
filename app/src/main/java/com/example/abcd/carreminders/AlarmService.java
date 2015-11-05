package com.example.abcd.carreminders;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.util.Random;

public class AlarmService extends Service {
    private static int NOTIFICATION_ID=0;
    private NotificationManager notificationManager;
    private PendingIntent pendingIntent;

    @Override
    public IBinder onBind(Intent arg0)
    {
        Log.d("DebugAlarm", "In AlarmService, onBind");
        return null;
    }

    @SuppressWarnings("static-access")
    @Override
    public void onStart(Intent intent, int startId)
    {
        super.onStart(intent, startId);

        Bundle bundle = intent.getExtras();
        String type="", licence="", period="";

        if (bundle != null) {
            type = bundle.getString("type");
            licence = bundle.getString("licence");
            period = bundle.getString("period");
        }

        Context context = this.getApplicationContext();
        Random r=new Random();
        NOTIFICATION_ID = r.nextInt(100000);
        notificationManager = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
        Intent mIntent = new Intent(this, ViewEventsActivity.class);
        pendingIntent = PendingIntent.getActivity(context, 0, mIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Car Reminders");
        builder.setContentText(licence + "'s " + type + " is going to expire in one " + period);
        builder.setSmallIcon(R.drawable.menuicon);
        builder.setContentIntent(pendingIntent);

        Log.d("DebugAlarm", "IN ALARMSERVICE " + licence + "'s " + type + " is going to expire in one " + period);

        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
        Log.d("DebugAlarm", "In AlarmService, onStart");
        Log.d("DebugAlarm", "The start id was " + startId);
    }
}
