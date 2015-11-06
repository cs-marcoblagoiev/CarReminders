package com.example.abcd.carreminders;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ViewSingleCarActivity extends BaseActivity {
    JCGSQLiteHelper db = new JCGSQLiteHelper(this);
    final List listFields = new ArrayList();
    final List listValuesDisplay = new ArrayList();
    public final List listValues = new ArrayList();
    Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single_car);
        super.onCreateDrawer(savedInstanceState);

        int id = -1;

        //retrieving the id from the last intent
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            id = bundle.getInt("id");
        }

        Log.d("DEBUG", "String received in ViewSingleCarActivity is: " + String.valueOf(id));


        //getting the car from the database
        car = db.findCar(id);


        listFields.add(0, getResources().getString(R.string.licence_plate));
        listFields.add(1, getResources().getString(R.string.brand_model));
        listFields.add(2, getResources().getString(R.string.usage));
        listFields.add(3, getResources().getString(R.string.insurance));
        listFields.add(4, getResources().getString(R.string.vehicle_inspection));
        listFields.add(5, getResources().getString(R.string.road_tax));
        listFields.add(6, getResources().getString(R.string.fire_extinguisher));
        listFields.add(7, getResources().getString(R.string.medical_kit));
        listFields.add(8, getResources().getString(R.string.rate));

        listValues.add(0, car.getLicence());
        listValues.add(1, car.getBrand());
        listValues.add(2, car.getUsage());
        listValues.add(3, car.getInsurance());
        listValues.add(4, car.getInspection());
        listValues.add(5, car.getTax());
        listValues.add(6, car.getFire());
        listValues.add(7, car.getMedical());
        listValues.add(8, car.getRate());

        listValuesDisplay.add(0, listValues.get(0));
        listValuesDisplay.add(1, listValues.get(1));
        if (car.getUsage()==null || car.getUsage().equals("")|| car.getUsage().equals(" "))
            listValuesDisplay.add(2, getResources().getString(R.string.click_for_value));
        else
            listValuesDisplay.add(2, listValues.get(2));
        if (car.getInsurance()==null || car.getInsurance().equals("") || car.getInsurance().equals(""))
            listValuesDisplay.add(3, getResources().getString(R.string.click_for_value));
        else
            listValuesDisplay.add(3, listValues.get(3));
        if (car.getInspection()==null || car.getInspection().equals("") || car.getInspection().equals(""))
            listValuesDisplay.add(4, getResources().getString(R.string.click_for_value));
        else
            listValuesDisplay.add(4, listValues.get(4));
        if (car.getTax()==null || car.getTax().equals("") || car.getTax().equals(""))
            listValuesDisplay.add(5, getResources().getString(R.string.click_for_value));
        else
            listValuesDisplay.add(5, listValues.get(5));
        if (car.getFire()==null || car.getFire().equals("") || car.getFire().equals(""))
            listValuesDisplay.add(6, getResources().getString(R.string.click_for_value));
        else
            listValuesDisplay.add(6, listValues.get(6));
        if (car.getMedical()==null || car.getMedical().equals("") || car.getMedical().equals(""))
            listValuesDisplay.add(7, getResources().getString(R.string.click_for_value));
        else
            listValuesDisplay.add(7, listValues.get(7));
        if (car.getRate()==null || car.getRate().equals("") || car.getRate().equals(""))
            listValuesDisplay.add(8, getResources().getString(R.string.click_for_value));
        else
            listValuesDisplay.add(8, listValues.get(8));

        Button updateButton = (Button) findViewById(R.id.updateButton);
        Button deleteButton = (Button) findViewById(R.id.deleteButton);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updateCar(car);
                Toast.makeText(getApplicationContext(), R.string.toast_car_updated, Toast.LENGTH_LONG).show();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo return to the old view
                db.deleteCar(car);
                ManageAlarms ma = new ManageAlarms();
                ma.deleteAlarms(getApplicationContext());
                Toast.makeText(getApplicationContext(), R.string.toast_car_deleted, Toast.LENGTH_LONG).show();

            }
        });

        updateView();
    }

    private void updateView() {
        Log.d("ListView2", "Now in updateview");
        ListView m_listview = (ListView) findViewById(R.id.list);

        final ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, R.layout.simple_list_item_3, android.R.id.text1, listFields) {
                    //overriding the method so we can use both text items
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                        TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                        text1.setText(listFields.get(position).toString());
                        text2.setText(listValuesDisplay.get(position).toString());
                        return view;
                    }
                };

        m_listview.setAdapter(adapter);

        m_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.d("DEBUG", "adapter: " + String.valueOf(adapter.getItemId(position)));
                switch ((int) adapter.getItemId(position)) {
                    case 0:
                        changeLicencePlate();
                        break;
                    case 1:
                        changeBrand();
                        break;
                    case 2:
                        editUsage();
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

    private void changeLicencePlate() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ViewSingleCarActivity.this);
        builder.setTitle("Licence Plate");

        // Set up the input
        final EditText input = new EditText(getApplicationContext());
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        //input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        input.setTextColor(Color.parseColor("#000000"));
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //m_Text = input.getText().toString();
                //changing the old value with the new value(just in the view)
               listValues.remove(0);
                listValues.add(0, input.getText().toString());
                car.setLicence(input.getText().toString());
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

    public void changeBrand() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ViewSingleCarActivity.this);
        builder.setTitle("Brand, Model");

        // Set up the input
        final EditText input = new EditText(getApplicationContext());
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        //input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        //input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setTextColor(Color.parseColor("#000000"));
        builder.setView(input);


        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //m_Text = input.getText().toString();
                //changing the old value with the new value(just in the view)
                listValues.remove(1);
                listValues.add(1, input.getText().toString());
                car.setBrand(input.getText().toString());
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

    public void editUsage() {
        final Dialog dialog = new Dialog(ViewSingleCarActivity.this);
        dialog.setContentView(R.layout.radio_layout);
        dialog.setTitle("Select the usage");
        dialog.setCancelable(true);
        // there are a lot of settings, for dialog, check them all out!
        // set up radiobutton

        Button selectButton = (Button) dialog.findViewById(R.id.selectButton);
        final RadioGroup radioTypeGroup = (RadioGroup) dialog.findViewById(R.id.radioGroup);

        //button listener
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //got the radiobuttons code from here http://www.mkyong.com/android/android-radio-buttons-example/
                //get selected radio button from radioGroup
                int selectedId = radioTypeGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                RadioButton selectedRadioButton = (RadioButton) dialog.findViewById(selectedId);

                listValues.set(2, selectedRadioButton.getText().toString());
                car.setUsage(selectedRadioButton.getText().toString());
                Log.d("DEBUG", selectedRadioButton.getText().toString());
                updateView();
                dialog.cancel();
            }

        });


        dialog.show();
    }

    public void showStartDateDialog(View v, int position) {
        DialogFragment dialogFragment = new StartDatePicker(position);
        dialogFragment.show(getFragmentManager(), "start_date_picker");
    }


    Calendar c = Calendar.getInstance();
    int startYear = c.get(Calendar.YEAR);
    int startMonth = c.get(Calendar.MONTH);
    int startDay = c.get(Calendar.DAY_OF_MONTH);

    public class StartDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        int position;

        public StartDatePicker(int position) {
            this.position = position;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // TODO Auto-generated method stub
            // Use the current date as the default date in the picker
            DatePickerDialog dialog = new DatePickerDialog(ViewSingleCarActivity.this, this, startYear, startMonth, startDay);
            dialog.getDatePicker().setMinDate(new Date().getTime());
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

        public void updateStartDateDisplay() {
            int startMonth2 = startMonth + 1;
            listValues.remove(position);
            String finalDate=startDay + "/" + startMonth2 + "/" + startYear;
            listValues.add(position, finalDate);
            if (position==3){
                car.setInsurance(finalDate);
            }else if (position==4){
                car.setInspection(finalDate);
            }else if(position==5){
                car.setTax(finalDate);
            }else if(position==6){
                car.setFire(finalDate);
            }else if (position==7){
                car.setMedical(finalDate);
            }else if (position==8){
                car.setRate(finalDate);
            }
            updateView();
        }
    }
}
