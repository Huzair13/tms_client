package com.example.tms_sona2.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tms_sona2.R;
import com.example.tms_sona2.dashboard;
import com.google.android.material.appbar.MaterialToolbar;

public class DetailsAssignAdmin extends AppCompatActivity {

    private TextView com_email_assign,pos_assign,mob_change;
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_assign_admin);

        com_email_assign=findViewById(R.id.Complaint_email);
        pos_assign=findViewById(R.id.pos_assign);
        mob_change=findViewById(R.id.mob_change);

        toolbar= findViewById(R.id.topAppBar_assign);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"your icon was clicked",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DetailsAssignAdmin.this, dashboard.class));
            }
        });

        com_email_assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailsAssignAdmin.this, Complaint_email_assign.class));
            }
        });

        pos_assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailsAssignAdmin.this, Assign_position_admin.class));
            }
        });

        mob_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailsAssignAdmin.this, Incharge_mobile_change.class));
            }
        });

    }
}