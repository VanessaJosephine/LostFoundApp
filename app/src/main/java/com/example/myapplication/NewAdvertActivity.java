package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.sqlitehelper.Advert;
import com.example.myapplication.sqlitehelper.DatabaseHelper;

import java.util.Calendar;

public class NewAdvertActivity extends AppCompatActivity {

    EditText name, phone, desc, date, loc;
    Button save;
    RadioGroup radioGroup;
    RadioButton radioButton;
    String advertType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_advert);

        // findViewById
        name = findViewById(R.id.editTextText);
        phone = findViewById(R.id.editTextText2);
        desc = findViewById(R.id.editTextText3);
        date = findViewById(R.id.editTextText4);
        loc = findViewById(R.id.editTextText5);
        save = findViewById(R.id.button3);
        radioGroup = findViewById(R.id.radioGroup);

        // Radio Group
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton = findViewById(i);
                Toast.makeText(getApplicationContext(), radioButton.getText().toString() + " selected", Toast.LENGTH_LONG).show();
                advertType = radioButton.getText().toString();
            }
        });

        // Date Picker
        date.setShowSoftInputOnFocus(false);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Calender class's instance and get current date, month and year from calendar
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // Date picker dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        // Save Button
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // DatabaseHelper
                DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
                // Makes sure EditText is not empty
                if (advertType != null || name.getText().toString().trim().length() != 0 || phone.getText().toString().trim().length() != 0 || desc.getText().toString().trim().length() != 0 || date.getText().toString().trim().length() != 0 || loc.getText().toString().trim().length() != 0)
                {
                    long result = databaseHelper.insertAdvert(new Advert(
                            name.getText().toString(),
                            advertType,
                            phone.getText().toString(),
                            desc.getText().toString(),
                            date.getText().toString(),
                            loc.getText().toString()
                    ));
                    if(result > 0)
                    {
                        Toast.makeText(view.getContext(), "Advert added successfully!", Toast.LENGTH_LONG).show();
                    }
                    else Toast.makeText(view.getContext(), "Unsuccessful!", Toast.LENGTH_LONG).show();
                }
                else Toast.makeText(view.getContext(), "Please enter all fields!", Toast.LENGTH_LONG).show();
            }
        });
    }
}