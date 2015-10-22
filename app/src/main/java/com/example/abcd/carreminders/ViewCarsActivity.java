package com.example.abcd.carreminders;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ViewCarsActivity extends BaseActivity {
    JCGSQLiteHelper db = new JCGSQLiteHelper(this);
    List<Car> list;
    ArrayAdapter myAdapter;

    private ListView m_listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cars);
        super.onCreateDrawer(savedInstanceState);

        // drop this database if already exists
        //db.onUpgrade(db.getWritableDatabase(), 1, 2);


/*        Car car = new Car();
        car.setLicence("Car1");
        car.setBrand("Brand1");
        db.createCar(car);

        Car car2 = new Car();
        car2.setLicence("Car2");
        car2.setBrand("Brand2");
        db.createCar(car);*/

        // get all the cars
        list = db.getAllCars();
        List listCars = new ArrayList();



        for (int i = 0; i < list.size(); i++) {
            listCars.add(i, list.get(i));
        }



        //myAdapter = new ArrayAdapter(this, R.layout.row_layout, R.id.listText, listTitle);

       ListView m_listview = (ListView) findViewById(R.id.list);
/*         Log.d("DEBUG", car.getBrand());
        Log.d("DEBUG",car.getLicence());
        String[] items = new String[] {car.getBrand(), car.getLicence(), car2.getLicence(), car2.getBrand()};*/
        //car.getBrand(), car.getLicence(), car2.getBrand(), car2.getBrand()

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1, listCars){
                    //overridning the method so we can use both text items
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                        TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                        text1.setText(list.get(position).getLicence());
                        text2.setText(list.get(position).getBrand());
                        return view;
                    }
                };

        m_listview.setAdapter(adapter);


        //getListView().setOnItemClickListener(this);
        //setListAdapter(myAdapter);
    }



    }



