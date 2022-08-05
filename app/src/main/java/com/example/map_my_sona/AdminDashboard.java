package com.example.map_my_sona;

import static com.example.map_my_sona.R.id.emergency_contact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.map_my_sona.BottomFragments.EmergencyContactFragment;
import com.example.map_my_sona.BottomFragments.ReportFragment;
import com.example.map_my_sona.complaints.Dep_wise_history;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.function.Function;

public class AdminDashboard extends AppCompatActivity {

    private MaterialCardView admin_dash_assign;
    private  MaterialCardView admin_dash_update;
    private MaterialCardView admin_dash_history;

    private final int ID_HOME  =1;
    private final int ID_Emergency =2;
    private final int ID_REPORT =3;
    private final int ID_LOGOUT =4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard2);

        //dashboard details findviewbyid
        admin_dash_assign=findViewById(R.id.admin_dash_assign);
        admin_dash_update = findViewById(R.id.admin_dash_update);
        admin_dash_history = findViewById(R.id.admin_dash_history);


//        BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottomnavi);
//        fragment_1 frf1=new fragment_1();
//        bottomNavigationView.setOnItemSelectedListener(item -> {
//            switch (item.getItemId()){
//                case R.id.emergency_contact:
//                    getSupportFragmentManager().beginTransaction().replace(R.id.container, frf1).commit();
//                    return true;
//            }
//            return false;
//        });
//        bottomNavigationView.setOnNavigationItemSelectedListener(this);
//        bottomNavigationView.setSelectedItemId(R.id.bottom_report);

        MeowBottomNavigation bottomNavigation=findViewById(R.id.bottom_nav_dashboard);

        bottomNavigation.add(new MeowBottomNavigation.Model(ID_HOME,R.drawable.ic_baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_Emergency,R.drawable.ephone));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_REPORT,R.drawable.report));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_LOGOUT,R.drawable.logout));


        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case ID_HOME:
                        startActivity(new Intent(AdminDashboard.this,AdminDashboard.class));
                        break;
                    case ID_Emergency:
                        replace(new EmergencyContactFragment());
                        break;
                    case ID_REPORT:
                        replace(new ReportFragment());
                        break;
                }
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Toast.makeText(AdminDashboard.this, "You are Already here!!", Toast.LENGTH_SHORT).show();
            }
        });

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                String name;
                switch (item.getId()){
                    case ID_HOME:
                        name="Home";
                        break;
                    case ID_Emergency:
                        name ="Emergency contact";
                        break;
                    case ID_REPORT:
                        name="Repor";
                        break;
                    case ID_LOGOUT:
                        name="Logout";
                        break;
                    default:
                        name="";
                }

            }
        });

        bottomNavigation.show(ID_HOME,true);

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

    private void replace(Fragment fragment) {

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container,fragment);
        fragmentTransaction.commit();
    }
}