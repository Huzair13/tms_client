package com.example.map_my_sona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScannerPage extends AppCompatActivity {

    LinearLayout scanBtn;
    public static TextView scantxt;
    TextView manualltext;

//    private TextView manual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_page);

        scanBtn=findViewById(R.id.Scanner);
//        scantxt=(TextView) findViewById(R.id.scantxt);
        manualltext=findViewById(R.id.entermanuallytext);

//        manual =(TextView) findViewById(R.id.ManualEntry);

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ScannerView.class));

            }
        });

        manualltext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScannerPage.this,manualentry.class));
            }
        });
    }
}