package com.example.abcd.carreminders;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
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
import java.util.List;

public class MainActivity extends BaseActivity {
    JCGSQLiteHelper db = new JCGSQLiteHelper(this);
    int firstPosition;

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        super.onCreateDrawer(savedInstanceState);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //edittexts
        final EditText editTextLicencePlate = (EditText) findViewById(R.id.editTextLicencePlate);
        //to open the keyboard
        editTextLicencePlate.requestFocus();
        final EditText editTextBrand = (EditText) findViewById(R.id.editTextBrand);
        final EditText editTextInsurance = (EditText) findViewById(R.id.editTextInsurance);
        final EditText editTextInspection = (EditText) findViewById(R.id.editTextInspection);
        final EditText editTextTax = (EditText) findViewById(R.id.editTextTax);
        final EditText editTextFire = (EditText) findViewById(R.id.editTextFire);
        final EditText editTextMedical = (EditText) findViewById(R.id.editTextMedical);
        final EditText editTextRate = (EditText) findViewById(R.id.editTextRate);

        //button
        Button addButton = (Button)findViewById(R.id.addButton);

        //radiobuttons
        final RadioGroup radioTypeGroup = (RadioGroup) findViewById(R.id.radioGroup);

        //insurance click listener
        editTextInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //use mainactivity.this instead of getApplicationContext, otherwise: Unable to add window -- token null is not for an application
                MyEditTextDatePicker licenceEdit = new MyEditTextDatePicker(MainActivity.this, editTextInsurance);
                licenceEdit.onClick(v);
                Log.d("DEBUG", "In calling updateDisplay from Main Activity ");
*//*                //licenceEdit.updateDisplay();
                Log.d("DEBUG", "Done calling updateDisplay from MianActivity ");*//*


            }
        });

        //inspection click listener
        editTextInspection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //use mainactivity.this instead of getApplicationContext, otherwise: Unable to add window -- token null is not for an application
                MyEditTextDatePicker licenceEdit = new MyEditTextDatePicker(MainActivity.this, editTextInspection );
                licenceEdit.onClick(v);
            }
        });

        //tax click listener
        editTextTax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //use mainactivity.this instead of getApplicationContext, otherwise: Unable to add window -- token null is not for an application
                MyEditTextDatePicker licenceEdit = new MyEditTextDatePicker(MainActivity.this, editTextTax );
                licenceEdit.onClick(v);
            }
        });

        //fire click listener
        editTextFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //use mainactivity.this instead of getApplicationContext, otherwise: Unable to add window -- token null is not for an application
                MyEditTextDatePicker licenceEdit = new MyEditTextDatePicker(MainActivity.this, editTextFire );
                licenceEdit.onClick(v);
            }
        });

        //medical click listener
        editTextMedical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //use mainactivity.this instead of getApplicationContext, otherwise: Unable to add window -- token null is not for an application
                MyEditTextDatePicker licenceEdit = new MyEditTextDatePicker(MainActivity.this, editTextMedical );
                licenceEdit.onClick(v);
            }
        });

        //rate click listener
        editTextRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //use mainactivity.this instead of getApplicationContext, otherwise: Unable to add window -- token null is not for an application
                MyEditTextDatePicker licenceEdit = new MyEditTextDatePicker(MainActivity.this, editTextRate );
                licenceEdit.onClick(v);
            }
        });


        //button listener
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getting all the strings
                final String licencePlate = editTextLicencePlate.getText().toString();
                final String brand = editTextBrand.getText().toString();
                final String insurance = editTextInsurance.getText().toString();
                final String inspection = editTextInspection.getText().toString();
                final String tax = editTextTax.getText().toString();
                final String fire = editTextFire.getText().toString();
                final String medical = editTextMedical.getText().toString();
                final String rate = editTextRate.getText().toString();


                if (licencePlate == null || licencePlate.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Licence plate cannot be empty", Toast.LENGTH_LONG).show();
                    Log.d("DEBUG", "Licence plate empty");
                    Log.d("DEBUG", "licencePlate" + licencePlate.toString());
                    Log.d("DEBUG", "editTextLicencePlate" + editTextLicencePlate.getText().toString());
                } else if (brand == null || brand.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Brand and model cannot be empty", Toast.LENGTH_LONG).show();
                    Log.d("DEBUG", "Brand and model empty");
                    Log.d("DEBUG", "brand" + brand.toString());
                    Log.d("DEBUG", "editTextBrand" + editTextBrand.getText().toString());
                } else {
                    //got the radiobuttons code from here http://www.mkyong.com/android/android-radio-buttons-example/
                    //get selected radio button from radioGroup
                    int selectedId = radioTypeGroup.getCheckedRadioButtonId();

                    // find the radiobutton by returned id
                    RadioButton selectedRadioButton = (RadioButton) findViewById(selectedId);
                    String radioButtonText;


                    if (selectedRadioButton==null){
                        radioButtonText="";
                    } else {
                        radioButtonText= selectedRadioButton.getText().toString();
                     }
                    //Log.d("DRadio", selectedRadioButton.getText().toString());

                    Log.d("DEBUG", "Selected car type is " + radioButtonText);
                    Car car = new Car(licencePlate, brand, radioButtonText, insurance,
                            inspection, tax, fire, medical, rate);

                    db.createCar(car);

                    //setting all the alarms
                    ManageAlarms manageAlarms = new ManageAlarms();
                    if (!insurance.isEmpty())
                        manageAlarms.setAlarm(getApplicationContext(), insurance, "insurance", licencePlate);

                    if (!inspection.isEmpty())
                    manageAlarms.setAlarm(getApplicationContext(), inspection, "inspection", licencePlate);

                    if(!tax.isEmpty())
                    manageAlarms.setAlarm(getApplicationContext(), tax, "tax", licencePlate);

                    if(!fire.isEmpty())
                    manageAlarms.setAlarm(getApplicationContext(), fire, "fire", licencePlate);

                    if(!medical.isEmpty())
                    manageAlarms.setAlarm(getApplicationContext(), medical, "medical", licencePlate);

                    if(!rate.isEmpty())
                    manageAlarms.setAlarm(getApplicationContext(), rate, "rate", licencePlate);

                    Log.d("DebugAlarm", "The rate is " +Integer.getInteger(rate));
                    Log.d("DebugAlarm", Boolean.toString(rate.isEmpty()));

                    Toast.makeText(getApplicationContext(), R.string.toast_car_added, Toast.LENGTH_LONG).show();
                }
            }
        });

    }
*/


    final List listFields = new ArrayList();
    final List listValuesDisplay = new ArrayList();
    public final List listValues = new ArrayList();
    Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        super.onCreateDrawer(savedInstanceState);

        listFields.add(0, getResources().getString(R.string.licence_plate));
        listFields.add(1, getResources().getString(R.string.brand_model));
        listFields.add(2, getResources().getString(R.string.usage));
        listFields.add(3, getResources().getString(R.string.insurance));
        listFields.add(4, getResources().getString(R.string.vehicle_inspection));
        listFields.add(5, getResources().getString(R.string.road_tax));
        listFields.add(6, getResources().getString(R.string.fire_extinguisher));
        listFields.add(7, getResources().getString(R.string.medical_kit));
        listFields.add(8, getResources().getString(R.string.rate));
        listFields.add(9, getResources().getString(R.string.oil));
        listFields.add(10, getResources().getString(R.string.parking));
        listFields.add(11, getResources().getString(R.string.eairfilter));
        listFields.add(12, getResources().getString(R.string.cairfilter));
        listFields.add(13, getResources().getString(R.string.battery));
        listFields.add(14, getResources().getString(R.string.antifreeze));
        listFields.add(15, getResources().getString(R.string.tire));
        listFields.add(16, getResources().getString(R.string.wiper));

        listValues.add(0, "");
        listValues.add(1, "");
        listValues.add(2, "");
        listValues.add(3, "");
        listValues.add(4, "");
        listValues.add(5, "");
        listValues.add(6, "");
        listValues.add(7, "");
        listValues.add(8, "");
        listValues.add(9, "");
        listValues.add(10, "");
        listValues.add(11, "");
        listValues.add(12, "");
        listValues.add(13, "");
        listValues.add(14, "");
        listValues.add(15, "");
        listValues.add(16, "");

        listValuesDisplay.add(0, getResources().getString(R.string.click_for_value));
        listValuesDisplay.add(1, getResources().getString(R.string.click_for_value));
        listValuesDisplay.add(2, getResources().getString(R.string.click_for_value));
        listValuesDisplay.add(3, getResources().getString(R.string.click_for_value));
        listValuesDisplay.add(4, getResources().getString(R.string.click_for_value));
        listValuesDisplay.add(5, getResources().getString(R.string.click_for_value));
        listValuesDisplay.add(6, getResources().getString(R.string.click_for_value));
        listValuesDisplay.add(7, getResources().getString(R.string.click_for_value));
        listValuesDisplay.add(8, getResources().getString(R.string.click_for_value));
        listValuesDisplay.add(9, getResources().getString(R.string.click_for_value));
        listValuesDisplay.add(10, getResources().getString(R.string.click_for_value));
        listValuesDisplay.add(11, getResources().getString(R.string.click_for_value));
        listValuesDisplay.add(12, getResources().getString(R.string.click_for_value));
        listValuesDisplay.add(13, getResources().getString(R.string.click_for_value));
        listValuesDisplay.add(14, getResources().getString(R.string.click_for_value));
        listValuesDisplay.add(15, getResources().getString(R.string.click_for_value));
        listValuesDisplay.add(16, getResources().getString(R.string.click_for_value));

        Button addButton = (Button) findViewById(R.id.addButton);

        //button listener
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (listValues.get(0) == null || listValues.get(0).toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Licence plate cannot be empty", Toast.LENGTH_LONG).show();
                    Log.d("DEBUG", "Licence plate empty");
/*                    Log.d("DEBUG", "licencePlate" + licencePlate.toString());
                    Log.d("DEBUG", "editTextLicencePlate" + editTextLicencePlate.getText().toString());*/
                } else if (listValues.get(1) == null || listValues.get(1).toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Brand and model cannot be empty", Toast.LENGTH_LONG).show();
                    Log.d("DEBUG", "Brand and model empty");
/*                    Log.d("DEBUG", "brand" + brand.toString());
                    Log.d("DEBUG", "editTextBrand" + editTextBrand.getText().toString())*/
                    ;
                } else {

                    //Log.d("DEBUG", "Selected car type is " + radioButtonText);
                    Car car = new Car(listValues.get(0).toString(), listValues.get(1).toString(), listValues.get(2).toString(),
                            listValues.get(3).toString(), listValues.get(4).toString(), listValues.get(5).toString(), listValues.get(6).toString(),
                            listValues.get(7).toString(), listValues.get(8).toString(), listValues.get(9).toString(),
                            listValues.get(10).toString(), listValues.get(11).toString(), listValues.get(12).toString(),
                            listValues.get(13).toString(), listValues.get(14).toString(), listValues.get(15).toString(),
                            listValues.get(16).toString());

                    db.createCar(car);

                    //setting all the alarms
                    ManageAlarms manageAlarms = new ManageAlarms();
                    if (!listValues.get(3).toString().isEmpty() && listValues.get(3) != null &&
                            !listValues.get(3).toString().equals(""))
                        manageAlarms.setAlarm(getApplicationContext(), listValues.get(3).toString(),
                                "insurance", listValues.get(0).toString());

                    if (!listValues.get(4).toString().isEmpty() && listValues.get(4) != null &&
                            !listValues.get(4).toString().equals(""))
                        manageAlarms.setAlarm(getApplicationContext(), listValues.get(4).toString(),
                                "inspection", listValues.get(0).toString());

                    if (!listValues.get(5).toString().isEmpty() && listValues.get(5) != null &&
                            !listValues.get(5).toString().equals(""))
                        manageAlarms.setAlarm(getApplicationContext(), listValues.get(5).toString(),
                                "road tax", listValues.get(0).toString());

                    if (!listValues.get(6).toString().isEmpty() && listValues.get(6) != null &&
                            !listValues.get(6).toString().equals(""))
                        manageAlarms.setAlarm(getApplicationContext(), listValues.get(6).toString(),
                                "fire extinguisher", listValues.get(0).toString());

                    if (!listValues.get(7).toString().isEmpty() && listValues.get(7) != null &&
                            !listValues.get(7).toString().equals(""))
                        manageAlarms.setAlarm(getApplicationContext(), listValues.get(7).toString(),
                                "medical kit", listValues.get(0).toString());

                    if (!listValues.get(8).toString().isEmpty() && listValues.get(8) != null &&
                            !listValues.get(8).toString().equals(""))
                        manageAlarms.setAlarm(getApplicationContext(), listValues.get(8).toString(),
                                "rate", listValues.get(0).toString());

                    if (!listValues.get(9).toString().isEmpty() && listValues.get(9) != null &&
                            !listValues.get(8).toString().equals(""))
                        manageAlarms.setAlarm(getApplicationContext(), listValues.get(9).toString(),
                                "oil", listValues.get(0).toString());

                    if (!listValues.get(10).toString().isEmpty() && listValues.get(10) != null &&
                            !listValues.get(10).toString().equals(""))
                        manageAlarms.setAlarm(getApplicationContext(), listValues.get(10).toString(),
                                "parking pass", listValues.get(0).toString());

                    if (!listValues.get(11).toString().isEmpty() && listValues.get(11) != null &&
                            !listValues.get(11).toString().equals(""))
                        manageAlarms.setAlarm(getApplicationContext(), listValues.get(11).toString(),
                                "engine air filter", listValues.get(0).toString());

                    if (!listValues.get(12).toString().isEmpty() && listValues.get(12) != null &&
                            !listValues.get(12).toString().equals(""))
                        manageAlarms.setAlarm(getApplicationContext(), listValues.get(12).toString(),
                                "cabin air filter", listValues.get(0).toString());

                    if (!listValues.get(13).toString().isEmpty() && listValues.get(13) != null &&
                            !listValues.get(13).toString().equals(""))
                        manageAlarms.setAlarm(getApplicationContext(), listValues.get(13).toString(),
                                "battery", listValues.get(0).toString());

                    if (!listValues.get(14).toString().isEmpty() && listValues.get(14) != null &&
                            !listValues.get(14).toString().equals(""))
                        manageAlarms.setAlarm(getApplicationContext(), listValues.get(14).toString(),
                                "antifreeze", listValues.get(0).toString());

                    if (!listValues.get(15).toString().isEmpty() && listValues.get(15) != null &&
                            !listValues.get(15).toString().equals(""))
                        manageAlarms.setAlarm(getApplicationContext(), listValues.get(15).toString(),
                                "tire", listValues.get(0).toString());

                    if (!listValues.get(16).toString().isEmpty() && listValues.get(16) != null &&
                            !listValues.get(16).toString().equals(""))
                        manageAlarms.setAlarm(getApplicationContext(), listValues.get(8).toString(),
                                "wiper", listValues.get(0).toString());

/*                    Log.d("DebugAlarm", "The rate is " +Integer.getInteger(rate));
                    Log.d("DebugAlarm", Boolean.toString(rate.isEmpty()));*/

                    Toast.makeText(getApplicationContext(), R.string.toast_car_added, Toast.LENGTH_LONG).show();
                }
            }
        });

        updateView();
    }

    private void updateView() {

        Log.d("ListView2", "Now in updateview");
        final ListView m_listview = (ListView) findViewById(R.id.list);

        final ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, R.layout.simple_list_item_3, android.R.id.text1, listFields) {
                    //overriding the method so we can use both text items
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        //firstPosition = m_listview.getFirstVisiblePosition();
                        TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                        TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                        text1.setText(listFields.get(position).toString());
                        text2.setText(listValuesDisplay.get(position).toString());
                        //m_listview.smoothScrollToPosition(position);


                        return view;
                    }
                };

        m_listview.setAdapter(adapter);
        //m_listview.setSelection(firstPosition);

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
                    case 9:
                        showStartDateDialog(view, 9);
                        break;
                    case 10:
                        showStartDateDialog(view, 10);
                        break;
                    case 11:
                        showStartDateDialog(view, 11);
                        break;
                    case 12:
                        showStartDateDialog(view, 12);
                        break;
                    case 13:
                        showStartDateDialog(view, 13);
                        break;
                    case 14:
                        showStartDateDialog(view, 14);
                        break;
                    case 15:
                        showStartDateDialog(view, 15);
                        break;
                    case 16:
                        showStartDateDialog(view, 16);
                        break;


                }
            }
        });
    }

    private void changeLicencePlate() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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
                listValuesDisplay.add(0, input.getText().toString());
                //car.setLicence(input.getText().toString());
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
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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
                listValuesDisplay.add(1, input.getText().toString());
                //car.setBrand(input.getText().toString());
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
        final Dialog dialog = new Dialog(MainActivity.this);
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
                listValuesDisplay.set(2, selectedRadioButton.getText().toString());
                //car.setUsage(selectedRadioButton.getText().toString());
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


    public class StartDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        Calendar c = Calendar.getInstance();
        int startYear = c.get(Calendar.YEAR);
        int startMonth = c.get(Calendar.MONTH);
        int startDay = c.get(Calendar.DAY_OF_MONTH);
        int position;

        public StartDatePicker(int position) {
            this.position = position;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // TODO Auto-generated method stub
            // Use the current date as the default date in the picker
            DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, this, startYear, startMonth, startDay);
            dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
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
            String finalDate = startDay + "/" + startMonth2 + "/" + startYear;
            //listValues.add(position, finalDate);
            if (position == 3) {
                listValues.add(3, finalDate);
                listValuesDisplay.add(3, finalDate);
            } else if (position == 4) {
                listValues.add(4, finalDate);
                listValuesDisplay.add(4, finalDate);
            } else if (position == 5) {
                listValues.add(5, finalDate);
                listValuesDisplay.add(5, finalDate);
            } else if (position == 6) {
                listValues.add(6, finalDate);
                listValuesDisplay.add(6, finalDate);
            } else if (position == 7) {
                listValues.add(7, finalDate);
                listValuesDisplay.add(7, finalDate);
            } else if (position == 8) {
                listValues.add(8, finalDate);
                listValuesDisplay.add(8, finalDate);
            } else if (position == 9) {
                listValues.add(9, finalDate);
                listValuesDisplay.add(9, finalDate);
            } else if (position == 10) {
                listValues.add(10, finalDate);
                listValuesDisplay.add(10, finalDate);
            } else if (position == 11) {
                listValues.add(11, finalDate);
                listValuesDisplay.add(11, finalDate);
            } else if (position == 12) {
                listValues.add(12, finalDate);
                listValuesDisplay.add(12, finalDate);
            } else if (position == 13) {
                listValues.add(13, finalDate);
                listValuesDisplay.add(13, finalDate);
            } else if (position == 14) {
                listValues.add(14, finalDate);
                listValuesDisplay.add(14, finalDate);
            } else if (position == 15) {
                listValues.add(15, finalDate);
                listValuesDisplay.add(15, finalDate);
            } else if (position == 16) {
                listValues.add(16, finalDate);
                listValuesDisplay.add(16, finalDate);
            }

            updateView();
        }
    }
}
