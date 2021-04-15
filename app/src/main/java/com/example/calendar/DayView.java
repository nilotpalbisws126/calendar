package com.example.calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DayView extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private TextView date;
    LocalDate today = LocalDate.now();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);

        date=findViewById(R.id.dateView);
        String message=""+monthYearFromDate(today);
        date.setText(message);

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
    private String monthYearFromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return date.format(formatter);
    }

    public void previousDayAction(View view) {
        today = today.minusDays(1);
        setDayView();
    }

    private void setDayView() {
        String message=""+monthYearFromDate(today);
        date.setText(message);
    }

    public void nextDayAction(View view) {
        today = today.plusDays(1);
        setDayView();
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
        DrawerLayout drawer = findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}