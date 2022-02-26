package com.example.map_my_sona;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class complaint_Page<FirebaseDatabase, Databa, FirebaseStorage> extends AppCompatActivity{
    Databa databaseReference;
    FirebaseStorage storage;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_page);

        TextView scanText = (TextView) findViewById(R.id.textView);
        String s = getIntent().getStringExtra("SCAN_RESULT");


        //scanText.setText(s);
    }
}