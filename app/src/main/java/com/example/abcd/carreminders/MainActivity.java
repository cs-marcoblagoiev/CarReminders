package com.example.abcd.carreminders;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends BaseActivity {
    JCGSQLiteHelper db = new JCGSQLiteHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        super.onCreateDrawer(savedInstanceState);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //edittexts
        final EditText editTextLicencePlate = (EditText) findViewById(R.id.editTextLicencePlate);
        //to open the keyboard
        editTextLicencePlate.requestFocus();
        final EditText editTextBrand = (EditText) findViewById(R.id.editTextBrand);
        final EditText editTextInsurance = (EditText) findViewById(R.id.editTextInsurance);
        final EditText editTextInspection = (EditText) findViewById(R.id.editTextInspection);
        final EditText editTextTax = (EditText) findViewById(R.id.editTextTax);
        final EditText editTextFire = (EditText) findViewById(R.id.editTextFire);
        final EditText editTextMedical = (EditText) findViewById(R.id.editTextMedical);
        final EditText editTextRate = (EditText) findViewById(R.id.editTextRate);

        //button
        Button addButton = (Button)findViewById(R.id.addButton);

        //radiobuttons
        final RadioGroup radioTypeGroup = (RadioGroup) findViewById(R.id.radioGroup);

        //insurance click listener
        editTextInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //use mainactivity.this instead of getApplicationContext, otherwise: Unable to add window -- token null is not for an application
                MyEditTextDatePicker licenceEdit = new MyEditTextDatePicker(MainActivity.this, editTextInsurance);
                licenceEdit.onClick(v);
                Log.d("DEBUG", "In calling updateDisplay from Main Activity ");
/*                //licenceEdit.updateDisplay();
                Log.d("DEBUG", "Done calling updateDisplay from MianActivity ");*/


            }
        });

        //inspection click listener
        editTextInspection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //use mainactivity.this instead of getApplicationContext, otherwise: Unable to add window -- token null is not for an application
                MyEditTextDatePicker licenceEdit = new MyEditTextDatePicker(MainActivity.this, editTextInspection );
                licenceEdit.onClick(v);
            }
        });

        //tax click listener
        editTextTax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //use mainactivity.this instead of getApplicationContext, otherwise: Unable to add window -- token null is not for an application
                MyEditTextDatePicker licenceEdit = new MyEditTextDatePicker(MainActivity.this, editTextTax );
                licenceEdit.onClick(v);
            }
        });

        //fire click listener
        editTextFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //use mainactivity.this instead of getApplicationContext, otherwise: Unable to add window -- token null is not for an application
                MyEditTextDatePicker licenceEdit = new MyEditTextDatePicker(MainActivity.this, editTextFire );
                licenceEdit.onClick(v);
            }
        });

        //medical click listener
        editTextMedical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //use mainactivity.this instead of getApplicationContext, otherwise: Unable to add window -- token null is not for an application
                MyEditTextDatePicker licenceEdit = new MyEditTextDatePicker(MainActivity.this, editTextMedical );
                licenceEdit.onClick(v);
            }
        });

        //rate click listener
        editTextRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //use mainactivity.this instead of getApplicationContext, otherwise: Unable to add window -- token null is not for an application
                MyEditTextDatePicker licenceEdit = new MyEditTextDatePicker(MainActivity.this, editTextRate );
                licenceEdit.onClick(v);
            }
        });


        //button listener
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getting all the strings
                final String licencePlate = editTextLicencePlate.getText().toString();
                final String brand = editTextBrand.getText().toString();
                final String insurance = editTextInsurance.getText().toString();
                final String inspection = editTextInspection.getText().toString();
                final String tax = editTextTax.getText().toString();
                final String fire = editTextFire.getText().toString();
                final String medical = editTextMedical.getText().toString();
                final String rate = editTextRate.getText().toString();


                if (licencePlate == null || licencePlate.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Licence plate cannot be empty", Toast.LENGTH_LONG).show();
                    Log.d("DEBUG", "Licence plate empty");
                    Log.d("DEBUG", "licencePlate" + licencePlate.toString());
                    Log.d("DEBUG", "editTextLicencePlate" + editTextLicencePlate.getText().toString());
                } else if (brand == null || brand.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Brand and model cannot be empty", Toast.LENGTH_LONG).show();
                    Log.d("DEBUG", "Brand and model empty");
                    Log.d("DEBUG", "brand" + brand.toString());
                    Log.d("DEBUG", "editTextBrand" + editTextBrand.getText().toString());
                } else {
                    //TODO verifiy that a radiobutton is selected
                    //got the radiobuttons code from here http://www.mkyong.com/android/android-radio-buttons-example/
                    //get selected radio button from radioGroup
                    int selectedId = radioTypeGroup.getCheckedRadioButtonId();

                    // find the radiobutton by returned id
                    RadioButton selectedRadioButton = (RadioButton) findViewById(selectedId);

                    Log.d("DEBUG", "Selected car type is " + selectedRadioButton.getText());
                    Car car = new Car(licencePlate, brand, selectedRadioButton.getText().toString(), insurance,
                            inspection, tax, fire, medical, rate);

                    db.createCar(car);

                    //setting all the alarms
                    ManageAlarms manageAlarms = new ManageAlarms();
                    manageAlarms.setAlarm(getApplicationContext(), insurance);
                    manageAlarms.setAlarm(getApplicationContext(), inspection);
                    manageAlarms.setAlarm(getApplicationContext(), tax);
                    manageAlarms.setAlarm(getApplicationContext(), fire);
                    manageAlarms.setAlarm(getApplicationContext(), medical);
                    manageAlarms.setAlarm(getApplicationContext(), rate);

                    Toast.makeText(getApplicationContext(), R.string.toast_car_added, Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    public class ManageAlarms {
        SharedPreferences sharedpreferences;
        public static final String MyPREFERENCES = "MyPrefs" ;


        public void setAlarm(Context context, String date){
            android.app.AlarmManager alarmManager = (android.app.AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
            Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, 0);

            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                cal.setTime(sdf.parse(date));// all done
            } catch (Exception e){ }


            cal.set(Calendar.HOUR_OF_DAY, 10);
            cal.set(Calendar.MINUTE, 00);
            cal.set(Calendar.SECOND, 0);
            if (sharedpreferences.getBoolean("month", true)){
                Calendar cal2 = cal;
                cal2.add(Calendar.MONTH, -1);
                alarmManager.set(android.app.AlarmManager.RTC, cal2.getTimeInMillis(), pendingIntent);
            }

            if (sharedpreferences.getBoolean("week", true)){
                Calendar cal3 = cal;
                cal3.add(Calendar.DAY_OF_MONTH, -7);
                alarmManager.set(android.app.AlarmManager.RTC, cal3.getTimeInMillis(), pendingIntent);
            }

            if (sharedpreferences.getBoolean("day", true)){
                Calendar cal4 = cal;
                cal4.add(Calendar.DAY_OF_MONTH, -1);
                alarmManager.set(android.app.AlarmManager.RTC, cal4.getTimeInMillis(), pendingIntent);
            }
            alarmManager.set(android.app.AlarmManager.RTC, cal.getTimeInMillis(), pendingIntent);
            Log.d("DebugAlarm", "Exiting setAlarm");
        }


    }

}
