package com.example.abcd.carreminders;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //edittexts
        final EditText editTextLicencePlate = (EditText) findViewById(R.id.editTextLicencePlate);
        EditText editTextBrand = (EditText) findViewById(R.id.editTextBrand);
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


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
                //got the radiobuttons code from here http://www.mkyong.com/android/android-radio-buttons-example/
                // get selected radio button from radioGroup
                int selectedId = radioTypeGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                RadioButton selectedRadioButton = (RadioButton) findViewById(selectedId);

                Log.d("DEBUG", "Selected car type is " + selectedRadioButton.getText());
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //class borrowed from http://stackoverflow.com/questions/14933330/datepicker-how-to-popup-datepicker-when-click-on-edittext
    public class MyEditTextDatePicker  implements View.OnClickListener, OnDateSetListener {
        EditText _editText;
        private int _day;
        private int _month;
        private int _birthYear;
        private Context _context;

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
            //TODO make the picker display todays date
            DatePickerDialog dialog =  new DatePickerDialog(_context, this, 2015, 10, 20);
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
