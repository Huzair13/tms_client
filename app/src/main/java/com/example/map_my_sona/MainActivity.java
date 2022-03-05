package com.example.map_my_sona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {
    private static int sleeptimer=5000;
    TextView text1;
//    TextView text2;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.text1);
//        text2 = findViewById(R.id.text2);
        lottieAnimationView= findViewById(R.id.lottie);


        text1.animate().translationY(2200).setDuration(1000).setStartDelay(4000);
//        text2.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(2200).setDuration(1000).setStartDelay(4000);

//
//        Animation move_anim = AnimationUtils.loadAnimation(getApplicationContext(),
//                R.anim.move_anim);
//        text1.startAnimation(move_anim);


        new Handler().postDelayed(new Runnable() {
         @Override
           public void run() {
              Intent i=new Intent(MainActivity.this,loginpage.class);
              startActivity(i);
              }
             },sleeptimer
        );
    }
    }
