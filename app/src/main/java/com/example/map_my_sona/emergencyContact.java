package com.example.map_my_sona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class emergencyContact extends AppCompatActivity {

    ImageView hodcall;
    ImageView hod1call;
    ImageView hod2call;
    ImageView hod3call;
    ImageView hod4call;
    MaterialToolbar toolbar;
    private DatabaseReference dbref;
    private String mob_elec,mob_plum,mob_paint,mob_net,mob_carp,mob_asset,mob_other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contact);

        hodcall=findViewById(R.id.hodcall);
        hod1call=findViewById(R.id.hod1call);
        hod2call=findViewById(R.id.hod2call);
        hod3call=findViewById(R.id.hod3call);
        hod4call=findViewById(R.id.hod4call);

        dbref= FirebaseDatabase.getInstance().getReference().child("Emails");

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mob_elec=snapshot.child("Electricity").child("mobile").getValue(Long.class).toString();
                mob_carp=snapshot.child("Carpenter").child("mobile").getValue(Long.class).toString();
                mob_net=snapshot.child("Network").child("mobile").getValue(Long.class).toString();
                mob_plum=snapshot.child("Plumber").child("mobile").getValue(Long.class).toString();
                mob_paint=snapshot.child("Painting").child("mobile").getValue(Long.class).toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        hodcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                //pannerselvam sir
                intent.setData(Uri.parse("tel:"+mob_elec));
                startActivity(intent);
            }
        });

        hod1call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                //adiyaman sir
                intent1.setData(Uri.parse("tel:"+mob_carp));
                startActivity(intent1);
            }
        });
        hod2call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                //sakthivel sir
                intent1.setData(Uri.parse("tel:"+mob_net));
                startActivity(intent1);
            }
        });
        hod3call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                //adiyaman sir
                intent1.setData(Uri.parse("tel:"+mob_plum));
                startActivity(intent1);
            }
        });
        hod4call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                //adiyaman sir
                intent1.setData(Uri.parse("tel:"+mob_paint));
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

//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//    }


    //bottom navi
//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {

//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            Fragment fragment;
//            switch (item.getItemId()) {
//                case R.id.bottom_history:
//                    startActivity(new Intent(emergencyContact.this, ManualComplaint_page.class));
//                    break;
//                case R.id.bottom_feedback:
//
//                    startActivity(new Intent(emergencyContact.this, Rating_and_Feedback.class));
//                    break;
//                case R.id.bottom_home:
//                    startActivity(new Intent(emergencyContact.this,dashboard.class));
//                    break;
//                case R.id.bottom_report:
//                    startActivity(new Intent(emergencyContact.this,Rating_and_Feedback.class));
//                    break;
//
//                case R.id.bottom_emer:
//                    startActivity(new Intent(emergencyContact.this,emergencyContact.class));
//                    break;
//
//            }
//            return false;
//        }
    };

}