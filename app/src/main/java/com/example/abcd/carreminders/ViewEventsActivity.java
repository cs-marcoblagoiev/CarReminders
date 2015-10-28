package com.example.abcd.carreminders;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ViewEventsActivity extends BaseActivity {

    JCGSQLiteHelper db = new JCGSQLiteHelper(this);
    List<Car> carList;
    //storing all the infos for the car so we can display them later
    List<String> licencePlatesList;
    List<String> eventType;
    List<String> eventDate;
    //will contain all the dates except the null ones
    List<String> eventDate2;
    //will contain all the dates except the null ones in date format
    List<Date> eventDate3;

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        super.onCreateDrawer(savedInstanceState);

        // get all the cars
        carList=new ArrayList<Car>();
        carList = db.getAllCars();
        licencePlatesList = new ArrayList<String>();
        eventType = new ArrayList<String>();
        eventDate = new ArrayList<String>();
        eventDate2 = new ArrayList<String>();
        eventDate3 = new ArrayList<Date>();


        Iterator it= carList.iterator();

        //iterating trough all the cars in the db
        while (it.hasNext()){
            Car car = (Car)it.next();

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



/*        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
        Iterator it3 = eventDate2.iterator();
        while (it3.hasNext()){
            eventDate3.add(formatter.parse((String) it3.next()));

        }*/

/*        formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = "7/06/2013";
        Date date2=null;
        try {
             date2= formatter.parse(dateInString);
        }
        catch (ParseException e){

        }

        Log.d("DEBUGDate", date2.toString());*/

        //logging them for a test
        Log.d("DEBUGList", licencePlatesList.toString());
        Log.d("DEBUGList", eventType.toString());
        Log.d("DEBUGList", eventDate.toString());
        Log.d("DEBUGList", eventDate2.toString());




    }
}
