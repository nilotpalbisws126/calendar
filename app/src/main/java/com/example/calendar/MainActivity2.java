package com.example.calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity2 extends  AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,CalendarAdapter.OnItemListener {
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;
    private TextView cellDayText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initWidgets();
        selectedDate=LocalDate.now();
        setMonthView();




        final DrawerLayout drawer = findViewById(R.id.drawer);
        ImageView menuIcon = (ImageView) findViewById(R.id.toggle);
        menuIcon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonth(selectedDate);
        CalendarAdapter calendarAdapter=new CalendarAdapter(selectedDate,daysInMonth,this);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);

    }

    private ArrayList<String> daysInMonth(LocalDate selectedDate) {
        ArrayList<String>daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(selectedDate);
        int daysInMonth = yearMonth.lengthOfMonth();
        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);

        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();
      //  Toast.makeText(this,""+LocalDate.now(),Toast.LENGTH_LONG).show();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
        String month=selectedDate.format(formatter).toString();
        String year= String.valueOf(selectedDate.getYear());
        String day= String.valueOf(selectedDate.getDayOfMonth());
        //LocalDate.parse(""+year+"-"+month+"-"+day);
      //  Toast.makeText(this,LocalDate.parse(year+"-"+month+"-"+day).toString(),Toast.LENGTH_LONG).show();



        for(int i=1;i<=42;i++){
            if(i<dayOfWeek||(i-dayOfWeek+1) > daysInMonth){
                daysInMonthArray.add("");
            }
            else{
                daysInMonthArray.add(String.valueOf(i-dayOfWeek+1));

            }
        }
        return daysInMonthArray;
    }

    private String monthYearFromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    private void initWidgets() {
        calendarRecyclerView =findViewById(R.id.calenderRecyclerView);
        monthYearText=findViewById(R.id.monthViewtv);


    }
    public void previousMonthAction(View view) {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }
    public void nextMonthAction(View view) {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, String dayText) {
        if(!dayText.equals("")){
            String message="Selected Date"+dayText+" "+monthYearFromDate(selectedDate);
            Toast.makeText(this,message,Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        Context context = getApplicationContext();
        if(id==R.id.yearView){
            Intent i = new Intent(this, MainActivity2.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(),"home",Toast.LENGTH_LONG).show();
        }
        else if(id == R.id.monthView){
            Intent i1 = new Intent(this, YearView.class);
            startActivity(i1);
            //  Toast.makeText(getApplicationContext(),"about",Toast.LENGTH_LONG).show();
        }
        else if(id == R.id.dayView){
            Intent i2 = new Intent(this, DayView.class);
            startActivity(i2);
           // Toast.makeText(getApplicationContext(),"contact",Toast.LENGTH_LONG).show();
        }
//        else if(id == R.id.menu_acknowledgement){
//            Intent i3 = new Intent(MainActivity.this, Acknowledgement.class);
//            this.startActivity(i3);
//            // Toast.makeText(getApplicationContext(),"contact",Toast.LENGTH_LONG).show();
//        }

        DrawerLayout drawer = findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}