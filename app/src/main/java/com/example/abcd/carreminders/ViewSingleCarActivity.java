package com.example.abcd.carreminders;

import android.os.Bundle;
import android.widget.TextView;

public class ViewSingleCarActivity extends BaseActivity {
    JCGSQLiteHelper db = new JCGSQLiteHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single_car);
        super.onCreateDrawer(savedInstanceState);

        Car car=db.findCar(1);

        TextView licenceText = (TextView) findViewById(R.id.licencePlateAdd);
        licenceText.append(car.getLicence());

    }
}
