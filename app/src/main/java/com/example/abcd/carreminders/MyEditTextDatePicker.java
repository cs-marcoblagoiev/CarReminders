package com.example.abcd.carreminders;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

//TODO limit the days to future days
//TODO make the deaful value the date of today
//class borrowed from http://stackoverflow.com/questions/14933330/datepicker-how-to-popup-datepicker-when-click-on-edittext
public class MyEditTextDatePicker  implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    EditText _editText;
    private int _day;
    private int _month;
    private int _birthYear;
    private Context _context;
    Calendar c = Calendar.getInstance();

    public MyEditTextDatePicker(Context context, EditText editTextViewID)
    {
        //Activity act = (Activity)context;
        this._editText = editTextViewID;
        this._editText.setOnClickListener(this);
        this._context = context;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        _birthYear = year;
        _month = monthOfYear;
        _day = dayOfMonth;
        updateDisplay();
        Log.d("DEBUG", "Im in onDateSet");
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        Log.d("DEBUG", stackTraceElements[0].toString());
        Log.d("DEBUG", stackTraceElements[1].toString());
        Log.d("DEBUG", stackTraceElements[2].toString());
        Log.d("DEBUG", stackTraceElements[3].toString());
        Log.d("DEBUG", stackTraceElements[4].toString());
        Log.d("DEBUG", stackTraceElements[5].toString());
        Log.d("DEBUG", stackTraceElements[6].toString());
        Log.d("DEBUG", stackTraceElements[7].toString());
        Log.d("DEBUG", stackTraceElements[8].toString());
        Log.d("DEBUG", stackTraceElements[9].toString());
        Log.d("DEBUG", stackTraceElements[10].toString());
        Log.d("DEBUG", stackTraceElements[11].toString());

    }
    @Override
    public void onClick(View v) {
        DatePickerDialog dialog =  new DatePickerDialog(_context, this, c.get(Calendar.YEAR),
                c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        dialog.getDatePicker().setMinDate(new Date().getTime());
        dialog.show();
        Log.d("DEBUG", "In onClick ");

    }

    // updates the date in the birth date EditText
    public void updateDisplay() {

        _editText.setText(new StringBuilder()
                // Month is 0 based so add 1
                .append(_day).append("/").append(_month + 1).append("/").append(_birthYear).append(" "));
        Log.d("DEBUG", "In updateDiplay ");
    }
}

