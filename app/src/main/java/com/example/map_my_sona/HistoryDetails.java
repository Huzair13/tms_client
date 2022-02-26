package com.example.map_my_sona;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class HistoryDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_details);
        // calling the action bar
   //     ActionBar actionBar = getSupportActionBar();

        // Customize the back button
//        actionBar.setHomeAsUpIndicator(R.drawable.mybutton);

        // showing the back button in action bar
//        actionBar.setDisplayHomeAsUpEnabled(true);
  //      getActionBar().setHomeButtonEnabled(true);

    //    getSupportActionBar().setTitle("HISTORY DETAILS");  // provide compatibility to all the versions
    }
}