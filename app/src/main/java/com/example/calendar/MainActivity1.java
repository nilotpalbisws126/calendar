package com.example.calendar;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,CalendarAdapter.OnItemListener {
private TextView monthYearText;
private RecyclerView calendarRecyclerView;
private LocalDate selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


//        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
//
//
//        navigationView=(NavigationView)findViewById(R.id.nav_menu);
//        drawerLayout= (DrawerLayout)findViewById(R.id.drawer);
//
//        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
//
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId()){
//                    case R.id.menu_about:
//                        Toast.makeText(getApplicationContext(),"about",Toast.LENGTH_LONG).show();
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//                    case R.id.menu_home:
//                        Toast.makeText(getApplicationContext(),"home",Toast.LENGTH_LONG).show();
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//                    case R.id.menu_contact:
//                        Toast.makeText(getApplicationContext(),"contact",Toast.LENGTH_LONG).show();
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//                }
//
//                return true;
//            }
//        });


    }

    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String>daysInMonth = daysInMonth(selectedDate);
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
        for(int i=1;i<=42;i++){
            if(i<=dayOfWeek||i>daysInMonth - dayOfWeek){
                daysInMonthArray.add("");
            }
            else{
                daysInMonthArray.add(String.valueOf(i+dayOfWeek));

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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        Context context = getApplicationContext();
//        if(id==R.id.menu_home){
//           // Intent i = new Intent(context, MainActivity.class);
//          //  context.startActivity(i);
//          //  Toast.makeText(getApplicationContext(),"home",Toast.LENGTH_LONG).show();
//        }
//        else if(id == R.id.menu_about){
//            Intent i1 = new Intent(MainActivity.this, AboutActivity.class);
//            this.startActivity(i1);
//          //  Toast.makeText(getApplicationContext(),"about",Toast.LENGTH_LONG).show();
//        }
//        else if(id == R.id.menu_contact){
//            Intent i2 = new Intent(MainActivity.this, Contact.class);
//            this.startActivity(i2);
//           // Toast.makeText(getApplicationContext(),"contact",Toast.LENGTH_LONG).show();
//        }
//        else if(id == R.id.menu_acknowledgement){
//            Intent i3 = new Intent(MainActivity.this, Acknowledgement.class);
//            this.startActivity(i3);
//            // Toast.makeText(getApplicationContext(),"contact",Toast.LENGTH_LONG).show();
//        }

        DrawerLayout drawer = findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        return true;
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
        if(dayText.equals("")){
            String message="Selected Date"+dayText+" "+monthYearFromDate(selectedDate);
            Toast.makeText(this,message,Toast.LENGTH_LONG).show();
        }

    }
}