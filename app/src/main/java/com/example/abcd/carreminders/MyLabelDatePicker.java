package com.example.abcd.carreminders;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

//TODO limit the days to future days
//TODO make the deaful value the date of today
//class borrowed from http://stackoverflow.com/questions/14933330/datepicker-how-to-popup-datepicker-when-click-on-edittext
public class MyLabelDatePicker  implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    List listvalue;
    private int _day;
    private int _month;
    private int _birthYear;
    private int position;
    private Context _context;
    Calendar c = Calendar.getInstance();

    public MyLabelDatePicker(Context context, List listvalue)
    {
        //Activity act = (Activity)context;
        this._context = context;
        this.listvalue=listvalue;
        //this.position = position;
        //this._editText=new EditText(context);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        _birthYear = year;
        _month = monthOfYear;
        _day = dayOfMonth;
        String string = updateDisplay();
        listvalue.remove(3);
        listvalue.add(3, string);

        Log.d("DEBUG", "Im in onDateSet, the string is " + string + " and listValue[0], listavalue[1] "+
        listvalue.get(0)+" "+ listvalue.get(1)  +listvalue.get(3));
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
    public String updateDisplay() {
        //_editText.setText("");
        String string = new StringBuilder().append(_day).append("/").append(_month + 1).append("/").append(_birthYear).append(" ").toString();
        Log.d("DEBUG", "The string in updateDisplay is " + string);
        return string;
    }

    public List getList(){
        return listvalue;
    }
}

