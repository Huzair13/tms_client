package com.example.map_my_sona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    private static int sleeptimer=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Handler().postDelayed(new Runnable() {
         @Override
           public void run() {
              Intent i=new Intent(MainActivity.this,Home.class);
              startActivity(i);
              }
             },sleeptimer
        );
    }
    }
