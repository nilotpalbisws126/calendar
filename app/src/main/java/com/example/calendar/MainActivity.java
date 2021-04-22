package com.example.calendar;

//import android.support.v7.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import static android.app.TimePickerDialog.*;

public class MainActivity extends AppCompatActivity   {
    private EditText startDate;
    private EditText endDate;
    private EditText deadline;
    private EditText meetingDate;
    private EditText description;
    private Button button;
    private Spinner event_type;
    private TextView title;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_add);

        title=findViewById(R.id.title);
        event_type=(Spinner)findViewById(R.id.event_type);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this,R.array.event_type,R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        event_type.setAdapter((adapter));
        button = (Button) findViewById(R.id.button);
        startDate=findViewById(R.id.start_date);
        endDate=findViewById(R.id.end_date);
        deadline=findViewById(R.id.deadline);
        meetingDate=findViewById(R.id.meeting_date);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!title.getText().toString().equals("") && (!startDate.getText().toString().equals("") || !deadline.getText().toString().equals("") || !meetingDate.getText().toString().equals(""))){
                    Intent i = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(i);
                    Toast.makeText(MainActivity.this,"Event Saved",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Enter more detail",Toast.LENGTH_LONG).show();
                }
            }
        });

        event_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               if(position==0){
                    startDate.setVisibility(View.GONE);
                    endDate.setVisibility(View.GONE);
                    deadline.setVisibility(View.GONE);
                    meetingDate.setVisibility(View.VISIBLE);
                    meetingDate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final Calendar c = Calendar.getInstance();
                            int mYear=c.get(Calendar.YEAR);
                            int mMonth= c.get(Calendar.MONTH);
                            int mDay=c.get(Calendar.DAY_OF_MONTH);
                            datePickerDialog=new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                    meetingDate.setText(dayOfMonth+"-"+(month+1)+"-"+year);
                                    Calendar c= Calendar.getInstance();
                                    int hour=c.get(Calendar.HOUR);
                                    int minute=c.get(Calendar.MINUTE);
                                    TimePickerDialog timePickerDialog=new TimePickerDialog(MainActivity.this,new TimePickerDialog.OnTimeSetListener(){

                                        @Override
                                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                            meetingDate.setText(""+meetingDate.getText().toString()+"  "+hourOfDay+":"+minute);
                                        }
                                    },hour,minute, DateFormat.is24HourFormat(MainActivity.this));
                                    timePickerDialog.show();
                                }
                            },mYear,mMonth,mDay);
                            datePickerDialog.show();
                        }
                    });

               }
               else if(position==1){
                   startDate.setVisibility(View.GONE);
                   endDate.setVisibility(View.GONE);
                   deadline.setVisibility(View.VISIBLE);
                   meetingDate.setVisibility(View.GONE);
                   deadline.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           final Calendar c = Calendar.getInstance();
                           int mYear=c.get(Calendar.YEAR);
                           int mMonth= c.get(Calendar.MONTH);
                           int mDay=c.get(Calendar.DAY_OF_MONTH);
                           datePickerDialog=new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                               @Override
                               public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                   deadline.setText(dayOfMonth+"-"+(month+1)+"-"+year);
                                   Calendar c= Calendar.getInstance();
                                   int hour=c.get(Calendar.HOUR);
                                   int minute=c.get(Calendar.MINUTE);
                                   TimePickerDialog timePickerDialog=new TimePickerDialog(MainActivity.this,new TimePickerDialog.OnTimeSetListener(){

                                       @Override
                                       public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                           deadline.setText(""+deadline.getText().toString()+"  "+hourOfDay+":"+minute);
                                       }
                                   },hour,minute, DateFormat.is24HourFormat(MainActivity.this));
                                   timePickerDialog.show();
                               }
                           },mYear,mMonth,mDay);
                           datePickerDialog.show();
                       }
                   });
               }
               else{
                   startDate.setVisibility(View.VISIBLE);
                   endDate.setVisibility(View.VISIBLE);
                   deadline.setVisibility(View.GONE);
                   meetingDate.setVisibility(View.GONE);

                   startDate.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           final Calendar c = Calendar.getInstance();
                           int mYear=c.get(Calendar.YEAR);
                           int mMonth= c.get(Calendar.MONTH);
                           int mDay=c.get(Calendar.DAY_OF_MONTH);
                           datePickerDialog=new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                               @Override
                               public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                   startDate.setText(dayOfMonth+"-"+(month+1)+"-"+year);
                                   Calendar c= Calendar.getInstance();
                                   int hour=c.get(Calendar.HOUR);
                                   int minute=c.get(Calendar.MINUTE);
                                   TimePickerDialog timePickerDialog=new TimePickerDialog(MainActivity.this,new TimePickerDialog.OnTimeSetListener(){

                                       @Override
                                       public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                           startDate.setText(""+startDate.getText().toString()+"  "+hourOfDay+":"+minute);
                                       }
                                   },hour,minute, DateFormat.is24HourFormat(MainActivity.this));
                                   timePickerDialog.show();
                               }
                           },mYear,mMonth,mDay);
                           datePickerDialog.show();
                       }
                   });

                   endDate.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           final Calendar c = Calendar.getInstance();
                           int mYear=c.get(Calendar.YEAR);
                           int mMonth= c.get(Calendar.MONTH);
                           int mDay=c.get(Calendar.DAY_OF_MONTH);
                           datePickerDialog=new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                               @Override
                               public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                   endDate.setText(dayOfMonth+"-"+(month+1)+"-"+year);
                                   Calendar c= Calendar.getInstance();
                                   int hour=c.get(Calendar.HOUR);
                                   int minute=c.get(Calendar.MINUTE);
                                   TimePickerDialog timePickerDialog=new TimePickerDialog(MainActivity.this,new TimePickerDialog.OnTimeSetListener(){

                                       @Override
                                       public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                           endDate.setText(""+endDate.getText().toString()+"  "+hourOfDay+":"+minute);
                                       }
                                   },hour,minute, DateFormat.is24HourFormat(MainActivity.this));
                                   timePickerDialog.show();
                               }
                           },mYear,mMonth,mDay);
                           datePickerDialog.show();
                       }
                   });

               }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public static boolean isValidDate(String inDate)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
            return true;
        } catch (ParseException pe) {
            return false;
        }

    }

//
//    public void openDialog()
//    {
//        String valid_date=startDate.getText().toString();
//
//        if(isValidDate(valid_date))
//        {
//            String type=typeOfEvent.getText().toString();
//            String data=typeOfEvent.getText().toString();
//
//            Database db=new Database(this);
//            db.insertData(date.getText().toString(),type,data);
//
//
//            Toast.makeText(this,"Event Saved",Toast.LENGTH_LONG).show();
//
//            Toast.makeText(this,"",Toast.LENGTH_LONG).show();
//            Intent i1 = new Intent(this, MainActivity2.class);
//            startActivity(i1);
//
//
//
//        }
//        else
//        {
//            Toast.makeText(this,"Date not valid",Toast.LENGTH_LONG).show();
//        }
//
//    }


}