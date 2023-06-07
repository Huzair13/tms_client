package com.example.tms_sona2.admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.tms_sona2.BottomFragments.AdminDashboardFragment;
import com.example.tms_sona2.BottomFragments.EmergencyContactFragment;
import com.example.tms_sona2.BottomFragments.ReportFragment;
import com.example.tms_sona2.R;
import com.example.tms_sona2.loginpage;
import com.google.firebase.auth.FirebaseAuth;

public class AdminDashboard extends AppCompatActivity {

//    private MaterialCardView admin_dash_assign;
//    private  MaterialCardView admin_dash_update;
//    private MaterialCardView admin_dash_history;

    private final int ID_HOME  =1;
    private final int ID_Emergency =2;
    private final int ID_REPORT =3;
    private final int ID_LOGOUT =4;

    AlertDialog.Builder builder11;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard2);

        //dashboard details findviewbyid
//        admin_dash_assign=findViewById(R.id.admin_dash_assign);
//        admin_dash_update = findViewById(R.id.admin_dash_update);
//        admin_dash_history = findViewById(R.id.admin_dash_history);

        builder11=new AlertDialog.Builder(this);

        mAuth=FirebaseAuth.getInstance();


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
                        replace(new AdminDashboardFragment());
                        break;
                    case ID_Emergency:
                        replace(new EmergencyContactFragment());
                        break;
                    case ID_REPORT:
                        replace(new ReportFragment());
                        break;
                    case ID_LOGOUT:
                        builder11.setTitle("Alert")
                                .setMessage("Are you sure to Log out ?")
                                .setCancelable(true)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        mAuth.signOut();
                                        Intent intent=new Intent(AdminDashboard.this, loginpage.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                        finish();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                })
                                .show();
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

        replace(new AdminDashboardFragment());
        bottomNavigation.show(ID_HOME,true);

//        admin_dash_history.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(AdminDashboard.this, Dep_wise_history.class));
//            }
//        });
//
//        admin_dash_assign.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(AdminDashboard.this, Assign_position_admin.class));
//                //Toast.makeText(AdminDashboard.this, us, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        admin_dash_update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(AdminDashboard.this, Admin_Update.class));
//            }
//        });

    }

    private void replace(Fragment fragment) {

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container,fragment);
        fragmentTransaction.commit();
    }
}