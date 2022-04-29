package com.example.map_my_sona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.map_my_sona.manualComplaints.ManualComplaint_page;
import com.example.map_my_sona.rating.Rating_and_Feedback;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    //bottom navi
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.bottom_history:
                    startActivity(new Intent(emergencyContact.this, ManualComplaint_page.class));
                    break;
                case R.id.bottom_feedback:

                    startActivity(new Intent(emergencyContact.this, Rating_and_Feedback.class));
                    break;
                case R.id.bottom_home:
                    startActivity(new Intent(emergencyContact.this,dashboard.class));
                    break;
                case R.id.bottom_report:
                    startActivity(new Intent(emergencyContact.this,Rating_and_Feedback.class));
                    break;

                case R.id.bottom_emer:
                    startActivity(new Intent(emergencyContact.this,emergencyContact.class));
                    break;

            }
            return false;
        }
    };

}