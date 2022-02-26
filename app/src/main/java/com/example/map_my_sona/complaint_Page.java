package com.example.map_my_sona;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class complaint_Page extends AppCompatActivity {
    public static TextView scanText;
    public static TextView text_view_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_page);

        scanText=(TextView) findViewById(R.id.textView);
        String s = getIntent().getStringExtra("SCAN_RESULT");

        scanText.setText(s);
        Calendar calForDate=Calendar.getInstance();
        SimpleDateFormat currentDate=new SimpleDateFormat("dd-MM-yy");
        String date=currentDate.format(calForDate.getTime());
        TextView textViewDate = findViewById(R.id.text_view_date);
        textViewDate.setText(date);

        Calendar calForTime = Calendar.getInstance();
        SimpleDateFormat currentTime=new SimpleDateFormat("hh:mm a");
        String time=currentTime.format(calForTime.getTime());
        TextView textViewtime = findViewById(R.id.time_current);
        textViewtime.setText(time);
    }
}