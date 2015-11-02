package com.example.abcd.carreminders;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class ManageAlarms{
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;


    public void setAlarm(Context context, String date){
        int i=0;
        android.app.AlarmManager alarmManager = (android.app.AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        //the second param was 0
        PendingIntent pendingIntent;

        sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        //converting the string to a calendar
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            cal.setTime(sdf.parse(date));// all done
        } catch (Exception e){ }


        cal.set(Calendar.HOUR_OF_DAY, 10);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 0);

        Calendar calTest = Calendar.getInstance();
        //Log.d("DebugAlarm", "calTest" + calTest.toString());

        Calendar cal2 = cal;
        cal2.add(Calendar.MONTH, -1);
        Calendar cal3 = cal;
        cal3.add(Calendar.DAY_OF_MONTH, -7);
        Calendar cal4 = cal;
        cal4.add(Calendar.DAY_OF_MONTH, -1);

        Random r=new Random();

        if (sharedpreferences.getBoolean("month", true) && cal2.after(calTest)){
            i=r.nextInt(100000);
            pendingIntent = PendingIntent.getBroadcast(context, i, alarmIntent, 0);
            alarmManager.set(android.app.AlarmManager.RTC, cal2.getTimeInMillis(), pendingIntent);
            Log.d("DebugAlarm", "Aded alarm for a month");
        }

        if (sharedpreferences.getBoolean("week", true) && cal3.after(calTest)){
            i=r.nextInt(100000);
            pendingIntent = PendingIntent.getBroadcast(context, i, alarmIntent, 0);
            alarmManager.set(android.app.AlarmManager.RTC, cal3.getTimeInMillis(), pendingIntent);
            Log.d("DebugAlarm", "Aded alarm for a week");
        }

        if (sharedpreferences.getBoolean("day", true) && cal4.after(calTest)){
            i=r.nextInt(100000);
            pendingIntent = PendingIntent.getBroadcast(context, i, alarmIntent, 0);
            alarmManager.set(android.app.AlarmManager.RTC, cal4.getTimeInMillis(), pendingIntent);
            Log.d("DebugAlarm", "Aded alarm for a day");
        }
        Log.d("DebugAlarm", "Exiting setAlarm");
    }

    public void deleteAlarm(Context context, int idCar){

    }

}