package com.example.map_my_sona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity {
    private static int sleeptimer=2700;
    TextView text1;
//    TextView text2;
    LottieAnimationView lottieAnimationView;
    private static int SPLASH_TIME_OUT=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.text1);
//        text2 = findViewById(R.id.text2);
        lottieAnimationView= findViewById(R.id.lottie);

        YoYo.with(Techniques.FadeIn).duration(2000).playOn(text1);
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

//             SharedPreferences sharedPreferences = getSharedPreferences(loginpage.PREFS_NAME,0);
//             boolean hasLoggedIn = sharedPreferences.getBoolean("hasLoggedIn",false);
//
//             if(hasLoggedIn){
//                 Intent i=new Intent(MainActivity.this,dashboard.class);
//                 startActivity(i);
//                 finish();
//             } else  {
//                 Intent a=new Intent(MainActivity.this,loginpage.class);
//                 startActivity(a);
//             }
              finish();
              }
             },SPLASH_TIME_OUT
        );
    }
    }
