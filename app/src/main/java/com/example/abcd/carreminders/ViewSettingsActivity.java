package com.example.abcd.carreminders;

import android.os.Bundle;

public class ViewSettingsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        super.onCreateDrawer(savedInstanceState);
    }
}
