package com.example.map_my_sona;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.map_my_sona.complaints.Dep_wise_history;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;

public class AdminDashboard extends AppCompatActivity {

    private MaterialCardView admin_dash_assign;
    private  MaterialCardView admin_dash_update;
    private MaterialCardView admin_dash_history;

    //
    BottomNavigationView bottomNavigation;

        private final int home=1;
        private final int report =2;
        private final int rating=3;
       private final int logout =4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard2);

        //dashboard details findviewbyid
        admin_dash_assign=findViewById(R.id.admin_dash_assign);
        admin_dash_update = findViewById(R.id.admin_dash_update);
        admin_dash_history = findViewById(R.id.admin_dash_history);

        //bottom
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomnavi);

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

//        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.google));
//        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.addp));
//        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.adminicon));
//        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.notification));


//        bottomNavigation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//            }
//        });
//
//      bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
//          @Override
//          public void onShowItem(MeowBottomNavigation.Model item) {
//              String name;
//              switch (item.getId()){
//                  case home:
//                      break;
//
//                  case report:
//                      startActivity(new Intent(AdminDashboard.this ,Qr_id_generator.class));
//                      break;
//                  case rating:
//                      startActivity(new Intent(AdminDashboard.this ,Qr_id_generator.class));
//                      break;
//
//                  case logout:
//                      startActivity(new Intent(AdminDashboard.this ,Qr_id_generator.class));
//                      break;
//              }
//
//          }
//      });
//      bottomNavigation.show(home,true);



//
//        bottomNavigation.setOnNavigationItemReselectedListener { item ->
//                when(item.itemId) {
//            R.id.item1 -> {
//                // Respond to navigation item 1 reselection
//            }
//            R.id.item2 -> {
//                // Respond to navigation item 2 reselection
//            }
//        }
//        }
    }
    }