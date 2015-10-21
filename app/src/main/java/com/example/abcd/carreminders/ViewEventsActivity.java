package com.example.abcd.carreminders;

import android.os.Bundle;

public class ViewEventsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        super.onCreateDrawer(savedInstanceState);
    }
}
