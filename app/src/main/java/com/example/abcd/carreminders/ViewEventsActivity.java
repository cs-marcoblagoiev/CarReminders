package com.example.abcd.carreminders;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ViewEventsActivity extends BaseActivity {

    JCGSQLiteHelper db = new JCGSQLiteHelper(this);
    List<Car> carList;
    List<Event> eventList;
    List<Event> eventList2;
    //storing all the infos for the car so we can display them later
/*
    List<String> licencePlatesList;
    List<String> eventType;
    List<String> eventDate;
    //will contain all the dates except the null ones
    List<String> eventDate2;
    //will contain all the dates except the null ones in date format
    List<Date> eventDate3;

    int i = 0;
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        super.onCreateDrawer(savedInstanceState);

        // get all the cars
        carList = new ArrayList<Car>();
        carList = db.getAllCars();
        eventList = new ArrayList<Event>();
        eventList2 = new ArrayList<Event>();
 /*       licencePlatesList = new ArrayList<String>();
        eventType = new ArrayList<String>();
        eventDate = new ArrayList<String>();
        eventDate2 = new ArrayList<String>();
        eventDate3 = new ArrayList<Date>();*/


        Iterator it = carList.iterator();

        //iterating trough all the cars in the db
        while (it.hasNext()) {
            Car car = (Car) it.next();
            eventList.add(new Event(car.getLicence(), car.getInsurance(), "Insurance"));
            eventList.add(new Event(car.getLicence(), car.getInspection(), "Inspection"));
            eventList.add(new Event(car.getLicence(), car.getTax(), "Road Tax"));
            eventList.add(new Event(car.getLicence(), car.getFire(), "Fire Extinguisher"));
            eventList.add(new Event(car.getLicence(), car.getMedical(), "Medical Kit"));
            eventList.add(new Event(car.getLicence(), car.getRate(), "Rate"));

        }

        Log.d("DEBUGList", eventList.toString());

        Iterator it2 = eventList.iterator();

        while(it2.hasNext()){
            Event event = (Event) it2.next();
            if (!event.getDate().equals("")){
                eventList2.add(event);
            }
            //Log.d("DEBUGList", event.getDate());
        }

        Log.d("DEBUGList", eventList2.toString());


/*
            //hardcoding all the data we need
            licencePlatesList.add(i, car.getLicence());
            eventType.add(i, "Insurance");
            eventDate.add(i, car.getInsurance());
            i++;

            licencePlatesList.add(i, car.getLicence());
            eventType.add(i, "Inspection");
            eventDate.add(i, car.getInspection());
            i++;

            licencePlatesList.add(i, car.getLicence());
            eventType.add(i, "Road Tax");
            eventDate.add(i, car.getTax());
            i++;

            licencePlatesList.add(i, car.getLicence());
            eventType.add(i, "Fire Extinguisher");
            eventDate.add(i, car.getFire());
            i++;

            licencePlatesList.add(i, car.getLicence());
            eventType.add(i, "Medical Kit");
            eventDate.add(i, car.getMedical());
            i++;

            licencePlatesList.add(i, car.getLicence());
            eventType.add(i, "Rate");
            eventDate.add(i, car.getRate());
            i++;

        }

        Iterator it2 = eventDate.iterator();
        while(it2.hasNext()){
            String temp = (String)it2.next();
            if (!temp.equals("")){
                eventDate2.add(temp);
            }
        }



        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
        Iterator it3 = eventDate2.iterator();
        while (it3.hasNext()){
            try {
                eventDate3.add(formatter.parse((String) it3.next()));
            }
            catch (ParseException e){

            }
        }

        //logging them for a test
        Log.d("DEBUGList", licencePlatesList.toString());
        Log.d("DEBUGList", eventType.toString());
        Log.d("DEBUGList", eventDate.toString());
        Log.d("DEBUGList", eventDate2.toString());
        Log.d("DEBUGList", eventDate3.toString());

        //sorting the list cronologically
        Collections.sort(eventDate3);


        Iterator it4 = eventDate3.iterator();
        while (it4.hasNext()){

        }


        Log.d("DEBUGList", eventDate3.toString());


*/
        }

}
