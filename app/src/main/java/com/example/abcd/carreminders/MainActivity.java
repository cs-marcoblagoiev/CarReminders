package com.example.abcd.carreminders;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends BaseActivity {
    JCGSQLiteHelper db = new JCGSQLiteHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        super.onCreateDrawer(savedInstanceState);

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
                licenceEdit.updateDisplay();
            }
        });

        //inspection click listener
        editTextInspection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //use mainactivity.this instead of getApplicationContext, otherwise: Unable to add window -- token null is not for an application
                MyEditTextDatePicker licenceEdit = new MyEditTextDatePicker(MainActivity.this, editTextInspection );
                licenceEdit.onClick(v);
                licenceEdit.updateDisplay();
            }
        });

        //tax click listener
        editTextTax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //use mainactivity.this instead of getApplicationContext, otherwise: Unable to add window -- token null is not for an application
                MyEditTextDatePicker licenceEdit = new MyEditTextDatePicker(MainActivity.this, editTextTax );
                licenceEdit.onClick(v);
                licenceEdit.updateDisplay();
            }
        });

        //fire click listener
        editTextFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //use mainactivity.this instead of getApplicationContext, otherwise: Unable to add window -- token null is not for an application
                MyEditTextDatePicker licenceEdit = new MyEditTextDatePicker(MainActivity.this, editTextFire );
                licenceEdit.onClick(v);
                licenceEdit.updateDisplay();
            }
        });

        //medical click listener
        editTextMedical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //use mainactivity.this instead of getApplicationContext, otherwise: Unable to add window -- token null is not for an application
                MyEditTextDatePicker licenceEdit = new MyEditTextDatePicker(MainActivity.this, editTextMedical );
                licenceEdit.onClick(v);
                licenceEdit.updateDisplay();
            }
        });

        //rate click listener
        editTextRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //use mainactivity.this instead of getApplicationContext, otherwise: Unable to add window -- token null is not for an application
                MyEditTextDatePicker licenceEdit = new MyEditTextDatePicker(MainActivity.this, editTextRate );
                licenceEdit.onClick(v);
                licenceEdit.updateDisplay();
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


                if (licencePlate == null || licencePlate.length()==0 ){
                    Toast.makeText(getApplicationContext(), "Licence plate cannot be empty" , Toast.LENGTH_LONG).show();
                    Log.d("DEBUG", "Licence plate empty");
                    Log.d("DEBUG", "licencePlate" + licencePlate.toString());
                    Log.d("DEBUG", "editTextLicencePlate" + editTextLicencePlate.getText().toString());
                } else if (brand == null || brand.length()==0 ){
                    Toast.makeText(getApplicationContext(), "Brand and model cannot be empty", Toast.LENGTH_LONG).show();
                    Log.d("DEBUG", "Brand and model empty");
                    Log.d("DEBUG", "brand" + brand.toString());
                    Log.d("DEBUG", "editTextBrand" + editTextBrand.getText().toString());
                } else{
                    //got the radiobuttons code from here http://www.mkyong.com/android/android-radio-buttons-example/
                    //get selected radio button from radioGroup
                    int selectedId = radioTypeGroup.getCheckedRadioButtonId();

                    // find the radiobutton by returned id
                    RadioButton selectedRadioButton = (RadioButton) findViewById(selectedId);

                    Log.d("DEBUG", "Selected car type is " + selectedRadioButton.getText());
                    Car car=new Car(licencePlate, brand, selectedRadioButton.getText().toString(), insurance,
                            inspection, tax, fire, medical, rate);

                    db.createCar(car);

                    Toast.makeText(getApplicationContext(), "Car added to database", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    //TODO limit the days to future days
    //class borrowed from http://stackoverflow.com/questions/14933330/datepicker-how-to-popup-datepicker-when-click-on-edittext
    public class MyEditTextDatePicker  implements View.OnClickListener, OnDateSetListener {
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
        }
        @Override
        public void onClick(View v) {
            DatePickerDialog dialog =  new DatePickerDialog(_context, this, c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
            dialog.getDatePicker().setMinDate(new Date().getTime());
            dialog.show();

        }

        // updates the date in the birth date EditText
        private void updateDisplay() {

            _editText.setText(new StringBuilder()
                    // Month is 0 based so add 1
                    .append(_day).append("/").append(_month + 1).append("/").append(_birthYear).append(" "));
        }
    }


}
