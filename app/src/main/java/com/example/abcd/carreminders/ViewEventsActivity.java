package com.example.abcd.carreminders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ViewEventsActivity extends BaseActivity {

    JCGSQLiteHelper db = new JCGSQLiteHelper(this);
    List<Car> carList;
    List<Event> eventList;
    List<Event> eventList2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        super.onCreateDrawer(savedInstanceState);

        // get all the cars
        List listevents = new ArrayList();
        carList = new ArrayList<Car>();
        carList = db.getAllCars();
        eventList = new ArrayList<Event>();
        eventList2 = new ArrayList<Event>();

        Iterator it = carList.iterator();

        //iterating trough all the cars in the db
        while (it.hasNext()) {
            Car car = (Car) it.next();
            eventList.add(new Event(car.getLicence(), car.getInsurance(), getResources().getString(R.string.insurance)));
            eventList.add(new Event(car.getLicence(), car.getInspection(), getResources().getString(R.string.vehicle_inspection)));
            eventList.add(new Event(car.getLicence(), car.getTax(), getResources().getString(R.string.road_tax)));
            eventList.add(new Event(car.getLicence(), car.getFire(), getResources().getString(R.string.fire_extinguisher)));
            eventList.add(new Event(car.getLicence(), car.getMedical(), getResources().getString(R.string.medical_kit)));
            eventList.add(new Event(car.getLicence(), car.getRate(), getResources().getString(R.string.rate)));

        }

        Log.d("DEBUGList", eventList.toString());

        Iterator it2 = eventList.iterator();

        while(it2.hasNext()){
            Event event = (Event) it2.next();
            if (!event.getDate().equals("")){
                eventList2.add(event);
                listevents.add(event);
            }
            //Log.d("DEBUGList", event.getDate());
        }

        Log.d("DEBUGList", eventList2.toString());

        Collections.sort(eventList2, new CustomComparator());
        //now we have only the events we need, in the order that we need in eventList2
        Log.d("DEBUGList", eventList2.toString());




        ListView m_listview = (ListView) findViewById(R.id.list);

        final ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1, listevents){
                    //overriding the method so we can use both text items
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                        TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                        Log.d("DEBUGList", "The size is " + eventList2.size());

                        text1.setText(eventList2.get(position).getDate());
                        text2.setText(eventList2.get(position).getName() + " " + eventList2.get(position).getLicence());
                        return view;
                    }
                };

        m_listview.setAdapter(adapter);

        }

}
