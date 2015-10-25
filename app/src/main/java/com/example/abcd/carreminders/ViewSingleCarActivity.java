package com.example.abcd.carreminders;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ViewSingleCarActivity extends BaseActivity {
    JCGSQLiteHelper db = new JCGSQLiteHelper(this);
    final List listFields = new ArrayList();
    public final List listValues = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single_car);
        super.onCreateDrawer(savedInstanceState);

        int id=-1;

        //retrieving the id from the last intent
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            id = bundle.getInt("id");
        }

        Log.d("DEBUG", "String received in ViewSingleCarActivity is: " + String.valueOf(id));


        //getting the car from the database
        Car car=db.findCar(id);



        listFields.add(0,"Licence Plate");
        listFields.add(1,"Brand, model");
        listFields.add(2,"Usage");
        listFields.add(3,"Insurance");
        listFields.add(4,"Vehicle Inspection");
        listFields.add(5,"Road Tax");
        listFields.add(6,"Fire Extinguisher");
        listFields.add(7,"Medical Kit");
        listFields.add(8,"Rate");

        listValues.add(0,car.getLicence());
        listValues.add(1,car.getBrand());
        listValues.add(2,car.getUsage());
        listValues.add(3,car.getInsurance());
        listValues.add(4,car.getInspection());
        listValues.add(5,car.getTax());
        listValues.add(6,car.getFire());
        listValues.add(7, car.getMedical());
        listValues.add(8, car.getRate());

        updateView();
    }

    private void updateView(){
        ListView m_listview = (ListView) findViewById(R.id.list);

        final ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1, listFields){
                    //overriding the method so we can use both text items
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                        TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                        text1.setText(listFields.get(position).toString());
                        text2.setText(listValues.get(position).toString());
                        return view;
                    }
                };

        m_listview.setAdapter(adapter);

        m_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.d("DEBUG", "adapter: " + String.valueOf(adapter.getItemId(position)));
                switch ((int)adapter.getItemId(position)) {
                    case 0:
                        changeLicencePlate();
                        break;
                    case 1:
                        changeBrand();
                        break;
                    case 2:
                        //TODO
                        break;
                    case 3:
                        showStartDateDialog(view, 3);
                        break;
                    case 4:
                        showStartDateDialog(view, 4);
                        break;
                    case 5:
                        showStartDateDialog(view, 5);
                        break;
                    case 6:
                        showStartDateDialog(view, 6);
                        break;
                    case 7:
                        showStartDateDialog(view, 7);
                        break;
                    case 8:
                        showStartDateDialog(view, 8);
                        break;


                }
            }
        });
    }

    private void changeLicencePlate(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ViewSingleCarActivity.this);
        builder.setTitle("Licence Plate");

        // Set up the input
        final EditText input = new EditText(getApplicationContext());
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        //input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //m_Text = input.getText().toString();
                //changing the old value with the new value(just in the view)
                listValues.remove(0);
                listValues.add(0,input.getText().toString());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void changeBrand(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ViewSingleCarActivity.this);
        builder.setTitle("Brand, Model");

        // Set up the input
        final EditText input = new EditText(getApplicationContext());
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        //input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //m_Text = input.getText().toString();
                //changing the old value with the new value(just in the view)
                listValues.remove(1);
                listValues.add(1,input.getText().toString());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void changeInsurance(View view){
        MyLabelDatePicker licenceEdit = new MyLabelDatePicker(ViewSingleCarActivity.this, listValues);
        licenceEdit.onClick(view);
        //String string = licenceEdit.updateDisplay();
        //Log.d("DEBUG", "The string from changeInsurance is " + string);
/*        listValues.remove(3);
        listValues.add(3, string);*/
        //updateView();
        Log.d("DEBUG", "Exiting changeInsurance");
    }

    public void showStartDateDialog(View v, int position){
        DialogFragment dialogFragment = new StartDatePicker(position);
        dialogFragment.show(getFragmentManager(), "start_date_picker");
    }



    Calendar c= Calendar.getInstance();
    int startYear=c.get(Calendar.YEAR);
    int startMonth=c.get(Calendar.MONTH);
    int startDay=c.get(Calendar.DAY_OF_MONTH);
    public class StartDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        int position;

        public StartDatePicker(int position){
            this.position=position;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // TODO Auto-generated method stub
            // Use the current date as the default date in the picker
            DatePickerDialog dialog = new DatePickerDialog(ViewSingleCarActivity.this, this, startYear, startMonth, startDay);
            return dialog;

        }
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            // Do something with the date chosen by the user
            startYear = year;
            startMonth = monthOfYear;
            startDay = dayOfMonth;
            updateStartDateDisplay();
        }

        public void updateStartDateDisplay(){
            int startMonth2=startMonth+1;
            listValues.add(position, startDay + "/" + startMonth2 + "/" + startYear);
            updateView();
        }
    }
}
