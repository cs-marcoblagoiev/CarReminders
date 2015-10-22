package com.example.abcd.carreminders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ViewSingleCarActivity extends BaseActivity {
    JCGSQLiteHelper db = new JCGSQLiteHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single_car);
        super.onCreateDrawer(savedInstanceState);

        final List listFields = new ArrayList();

        listFields.add(0,"Licence Plate");
        listFields.add(1,"Brand, model");
        listFields.add(2,"Usage");
        listFields.add(3,"Insurance");
        listFields.add(4,"Vehicle Inspection");
        listFields.add(5,"Road Tax");
        listFields.add(6,"Fire Extinguisher");
        listFields.add(7,"Medical Kit");
        listFields.add(8,"Rate");

        Car car=db.findCar(1);

/*        TextView licenceText = (TextView) findViewById(R.id.licencePlateAdd);
        licenceText.append(car.getLicence());*/

        //List listCars = new ArrayList();



/*
        for (int i = 0; i < list.size(); i++) {
            listCars.add(i, list.get(i));
        }
*/

        ListView m_listview = (ListView) findViewById(R.id.list);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1, listFields){
                    //overriding the method so we can use both text items
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                        TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                        text1.setText(listFields.get(position).toString());
                        text2.setText(listFields.get(position).toString());
                        return view;
                    }
                };

        m_listview.setAdapter(adapter);

        m_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ViewSingleCarActivity.class);
                //intent.putExtra("id", )
                startActivity(intent);
            }
        });


    }
}
