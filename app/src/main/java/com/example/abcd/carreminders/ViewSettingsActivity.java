package com.example.abcd.carreminders;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

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

                Toast.makeText(ViewSettingsActivity.this, "Settings saved", Toast.LENGTH_LONG).show();


            }
        });
    }
}
