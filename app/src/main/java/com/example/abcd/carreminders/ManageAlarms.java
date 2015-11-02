package com.example.abcd.carreminders;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ManageAlarms{
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    static int i=0;


    public void setAlarm(Context context, String date, String type, String licence){
        sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        i=sharedpreferences.getInt("lastId", 0);
        Log.d("DebugAlarm", "Getting lastId "+ i);
        android.app.AlarmManager alarmManager = (android.app.AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        alarmIntent.putExtra("type", type);
        alarmIntent.putExtra("licence", licence);
        //the second param was 0
        PendingIntent pendingIntent;

        Log.d("DebugAlarm", "date is " + date);

        //converting the string to a calendar
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            cal.setTime(sdf.parse(date));// all done
        } catch (Exception e){
            Log.d("DebugAlarm", "!!!!!!!!!!!!!In exception " );
        }
        Log.d("DebugAlarm", "cal is " + cal.toString());

        cal.set(Calendar.HOUR_OF_DAY, 10);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 0);

        Calendar calTest = Calendar.getInstance();
        //Log.d("DebugAlarm", "calTest" + calTest.toString());

        Calendar cal2 = (Calendar)cal.clone();
        cal2.add(Calendar.MONTH, -1);
        Calendar cal3 = (Calendar)cal.clone();
        cal3.add(Calendar.DAY_OF_MONTH, -7);
        Calendar cal4 = (Calendar)cal.clone();
        cal4.add(Calendar.DAY_OF_MONTH, -1);

        Log.d("DebugAlarm", "cal is " + cal.toString());
        Log.d("DebugAlarm", "cal2 is " + cal2.toString());
        Log.d("DebugAlarm", "cal3 is " + cal3.toString());
        Log.d("DebugAlarm", "cal4 is " + cal4.toString());
        Log.d("DebugAlarm", "calTest is " + calTest.toString());

        //Random r=new Random();

        if (sharedpreferences.getBoolean("month", true) && cal2.after(calTest)){
            //i=r.nextInt(100000);
            pendingIntent = PendingIntent.getBroadcast(context, i++, alarmIntent, 0);
            alarmManager.set(android.app.AlarmManager.RTC, cal2.getTimeInMillis(), pendingIntent);
            Log.d("DebugAlarm", "Added alarm for a month");
            Log.d("DebugAlarm", "i is "+ i);
        }

        if (sharedpreferences.getBoolean("week", true) && cal3.after(calTest)){
            //i=r.nextInt(100000);
            pendingIntent = PendingIntent.getBroadcast(context, i++, alarmIntent, 0);
            alarmManager.set(android.app.AlarmManager.RTC, cal3.getTimeInMillis(), pendingIntent);
            Log.d("DebugAlarm", "Added alarm for a week");
            Log.d("DebugAlarm", "i is "+ i);
        }

        if (sharedpreferences.getBoolean("day", true) && cal4.after(calTest)){
            //i=r.nextInt(100000);
            pendingIntent = PendingIntent.getBroadcast(context, i++, alarmIntent, 0);
            alarmManager.set(android.app.AlarmManager.RTC, cal4.getTimeInMillis(), pendingIntent);
            Log.d("DebugAlarm", "Added alarm for a day");
            Log.d("DebugAlarm", "i is "+ i);
        }
        SharedPreferences.Editor editor = sharedpreferences.edit();
        Log.d("DebugAlarm", "Setting lastId to "+ i);
        editor.putInt("lastId", i);
        editor.commit();
        Log.d("DebugAlarm", "Exiting setAlarm");
    }

    public void deleteAlarm(Context context){
        sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        int j=0;
        int i = sharedpreferences.getInt("lastId", 1);
        android.app.AlarmManager alarmManager = (android.app.AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        //the second param was 0
        PendingIntent pendingIntent;

        while (j<=i) {
            pendingIntent = PendingIntent.getBroadcast(context, j, alarmIntent, 0);
            alarmManager.cancel(pendingIntent);
            pendingIntent.cancel();
            Log.d("DebugAlarm", "Canceling the event with the id " + j);
            j++;
        }
        i=0;
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt("lastId", i);
        editor.commit();
    }

    public void setAlarmForAllCars(Context context){


    }

}