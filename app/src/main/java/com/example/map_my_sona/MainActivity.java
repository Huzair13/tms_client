package com.example.map_my_sona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static int sleeptimer=3000;
    TextView studio_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studio_text = findViewById(R.id.studio_text);
        Animation move_anim = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move_anim);
        studio_text.startAnimation(move_anim);


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
