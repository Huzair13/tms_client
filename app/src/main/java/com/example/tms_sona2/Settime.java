package com.example.tms_sona2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Settime extends AppCompatActivity {

    DatabaseReference dbref;

    Button btn;
    private String currentDate;
    private String currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settime);

        dbref=FirebaseDatabase.getInstance().getReference();

        //databaseReference= FirebaseDatabase.getInstance().getReference();

        btn=findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveButtonClickTime();
            }
        });
    }
    private void saveButtonClickTime() {

        dbref = FirebaseDatabase.getInstance().getReference().child("SonaStars");
        final String uniqueKey = dbref.push().getKey();

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDateFormat = new SimpleDateFormat("MMM dd, yyyy");
        currentDate = currentDateFormat.format(calForDate.getTime());
        Calendar calForTime = Calendar.getInstance();
        SimpleDateFormat currentTimeFormat = new SimpleDateFormat("hh:mm a");
        currentTime = currentTimeFormat.format(calForTime.getTime());
        long timestamp= System.currentTimeMillis();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("SonaStars").child(uniqueKey);

        databaseReference.child("init_date").setValue(timestamp);

    }
}