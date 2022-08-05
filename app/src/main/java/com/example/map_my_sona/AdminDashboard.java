package com.example.map_my_sona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.map_my_sona.complaints.Dep_wise_history;
import com.google.android.material.card.MaterialCardView;

public class AdminDashboard extends AppCompatActivity {

    private MaterialCardView admin_dash_assign;
    private  MaterialCardView admin_dash_update;
    private MaterialCardView admin_dash_history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard2);


        //dashboard details findviewbyid
        admin_dash_assign=findViewById(R.id.admin_dash_assign);
        admin_dash_update = findViewById(R.id.admin_dash_update);
        admin_dash_history = findViewById(R.id.admin_dash_history);




        admin_dash_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboard.this, Dep_wise_history.class));
            }
        });

        admin_dash_assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboard.this, Admin_Assign.class));
            }
        });

        admin_dash_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboard.this, Admin_Update.class));
            }
        });

    }

    }