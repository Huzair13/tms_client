package com.example.map_my_sona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.map_my_sona.complaints.viewDetails.historyviewdetails_carpenter;
import com.google.android.material.appbar.MaterialToolbar;

public class emergencyContact extends AppCompatActivity {

    ImageView hodcall;
    ImageView hod1call;
    ImageView hod2call;
    ImageView hod3call;
    ImageView hod4call;
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contact);

        hodcall=findViewById(R.id.hodcall);
        hod1call=findViewById(R.id.hod1call);
        hod2call=findViewById(R.id.hod2call);
        hod3call=findViewById(R.id.hod3call);
        hod4call=findViewById(R.id.hod4call);

        hodcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                //pannerselvam sir
                intent.setData(Uri.parse("tel:7418009997"));
                startActivity(intent);
            }
        });

        hod1call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                //adiyaman sir
                intent1.setData(Uri.parse("tel:9894341589"));
                startActivity(intent1);
            }
        });
        hod2call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                //sakthivel sir
                intent1.setData(Uri.parse("tel:9442531522"));
                startActivity(intent1);
            }
        });
        hod3call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                //adiyaman sir
                intent1.setData(Uri.parse("tel:9894341589"));
                startActivity(intent1);
            }
        });
        hod4call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                //adiyaman sir
                intent1.setData(Uri.parse("tel:9894341589"));
                startActivity(intent1);
            }
        });
        toolbar= findViewById(R.id.topAppBar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"your icon was clicked",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(emergencyContact.this, dashboard.class));
            }
        });
    }
}