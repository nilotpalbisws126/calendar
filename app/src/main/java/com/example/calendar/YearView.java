package com.example.calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class YearView extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    YearViewAdapter yearViewAdapter;
    int []arr={R.drawable.jan,R.drawable.jan,R.drawable.jan,R.drawable.jan,R.drawable.jan,R.drawable.jan,R.drawable.jan,R.drawable.jan,R.drawable.jan,R.drawable.jan,R.drawable.jan,R.drawable.jan};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_view);

        recyclerView=findViewById(R.id.yearRecyclerView);
        layoutManager=new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        yearViewAdapter=new YearViewAdapter(arr);


        recyclerView.setAdapter(yearViewAdapter);

        recyclerView.setHasFixedSize(true);
        Toast.makeText(this,"hello",Toast.LENGTH_LONG).show();



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