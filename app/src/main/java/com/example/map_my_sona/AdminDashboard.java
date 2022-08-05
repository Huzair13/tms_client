package com.example.map_my_sona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
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

import java.util.logging.MemoryHandler;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import meow.bottomnavigation.MeowBottomNavigation;

public class AdminDashboard extends AppCompatActivity {

    private MaterialCardView admin_dash_assign;
    private  MaterialCardView admin_dash_update;
    private MaterialCardView admin_dash_history;

    private final int home= 1;
    private final int report= 1;
    private final int rating= 1;
    private final int logout= 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard2);

        //dashboard details findviewbyid
        admin_dash_assign=findViewById(R.id.admin_dash_assign);
        admin_dash_update = findViewById(R.id.admin_dash_update);
        admin_dash_history = findViewById(R.id.admin_dash_history);

        //bottom
//        TextView textview = findViewById(R.id.page)
        MeowBottomNavigation bottomNavigation = findViewById(R.id.bottomnavi);

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

//        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.adminicon));
//        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.google));
//        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.addp));
//        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.notification));


    bottomNavigation.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //user navi
        }
    });



    }

    }