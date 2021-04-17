package com.example.calendar;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {

    private final ArrayList<String> dayOfMonth;
    private final OnItemListener onItemListener;
    private final LocalDate month_year;


    public CalendarAdapter(LocalDate month_year,ArrayList<String> dayOfMonth, OnItemListener onItemListener) {
        this.dayOfMonth = dayOfMonth;
        this.onItemListener = onItemListener;
        this.month_year=month_year;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell,parent,false);
        ViewGroup.LayoutParams layoutParams=view.getLayoutParams();
        layoutParams.height=(int)(parent.getHeight()*0.16666666666);

        return new CalendarViewHolder((view), onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        holder.dayOfMonth.setText(dayOfMonth.get(position));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
        DecimalFormat formatter_day = new DecimalFormat("00");


        String month=month_year.format(formatter).toString();
        String year= String.valueOf(month_year.getYear());
        if(holder.dayOfMonth.getText().toString()!=null && !holder.dayOfMonth.getText().toString().isEmpty()){
            int day=Integer.parseInt(holder.dayOfMonth.getText().toString());
            String day_s=formatter_day.format(day);


            if(LocalDate.parse(year+"-"+month+"-"+day_s).getDayOfWeek().toString().equals("SUNDAY")){
                holder.dayOfMonth.setTextColor(Color.RED);
            }
        }

//        LocalDate.parse(""+year+"-"+month+"-"+day);
        //  Toast.makeText(this,LocalDate.parse(year+"-"+month+"-"+day).toString(),Toast.LENGTH_LONG).show();

        if(holder.dayOfMonth.getText().toString().equals(""+LocalDate.now().getDayOfMonth()) && month_year.equals(LocalDate.now())){

           holder.dayOfMonth.setTextColor(Color.BLACK);
            holder.dayOfMonth.setBackgroundColor(Color.GREEN);

        }





    }

    @Override
    public int getItemCount() {
        return dayOfMonth.size();

    }
    public interface OnItemListener
    {
        void onItemClick(int position, String dayText);
    }
}
