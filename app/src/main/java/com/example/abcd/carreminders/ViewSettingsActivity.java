package com.example.abcd.carreminders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Locale;

public class ViewSettingsActivity extends BaseActivity {

    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        super.onCreateDrawer(savedInstanceState);

        final CheckBox checkBoxMonth = (CheckBox) findViewById(R.id.checkBox);
        final CheckBox checkBoxWeek = (CheckBox) findViewById(R.id.checkBox2);
        final CheckBox checkBoxDay = (CheckBox) findViewById(R.id.checkBox3);

        final RadioGroup radioTypeGroup = (RadioGroup) findViewById(R.id.radioGroupSettings);


        Button button = (Button) findViewById(R.id.buttonSettings);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        if (sharedpreferences.getBoolean("month", true)){
            checkBoxMonth.setChecked(true);
        }

        if (sharedpreferences.getBoolean("week", true)){
            checkBoxWeek.setChecked(true);
        }

        if (sharedpreferences.getBoolean("day", true)){
            checkBoxDay.setChecked(true);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putBoolean("month", checkBoxMonth.isChecked());
                editor.putBoolean("week", checkBoxWeek.isChecked());
                editor.putBoolean("day", checkBoxDay.isChecked());

                editor.commit();

                int selectedId = radioTypeGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                RadioButton selectedRadioButton = (RadioButton) findViewById(selectedId);

                Log.d("DEBUG", "Selected language is " + selectedRadioButton.getId());

                String lang = "en";
                if (selectedRadioButton.getId() == R.id.radioButtonEnglish){
                    setLocale("en");
                } else if (selectedRadioButton.getId() ==  R.id.radioButtonGerman) {
                    setLocale("de");
                } else if (selectedRadioButton.getId() ==  R.id.radioButtonFrench) {
                    setLocale("fr");
                } else if (selectedRadioButton.getId() ==  R.id.radioButtonRomanian){
                    setLocale("ro");
                }

                //editor.putString("language", lang);

                ManageAlarms ma = new ManageAlarms();
                ma.deleteAlarms(getApplicationContext());

                Toast.makeText(ViewSettingsActivity.this, R.string.saved_setting , Toast.LENGTH_LONG).show();


            }
        });
    }

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, ViewSettingsActivity.class);
        startActivity(refresh);
        finish();
    }
}
