package com.example.map_my_sona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.map_my_sona.complaints.Dep_wise_history;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
                startActivity(new Intent(AdminDashboard.this, Assign_position_admin.class));
                //Toast.makeText(AdminDashboard.this, us, Toast.LENGTH_SHORT).show();
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