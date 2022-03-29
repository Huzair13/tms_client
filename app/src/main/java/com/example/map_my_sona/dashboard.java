package com.example.map_my_sona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.map_my_sona.complaints.HistoryDetails.Complaints_HistoryDetails_Electricity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //
    private long backPressedTime;
    private Toast backToast;

    LinearLayout loading;

    DrawerLayout drawerLayout1;
    NavigationView navigationView1;
    LinearLayout Linear1;
    MaterialToolbar toolbar1;
    ImageView logo;

    private MaterialCardView scanner;
    private  MaterialCardView manualentry;
    private MaterialCardView history;

    FirebaseAuth mAuth;
    AlertDialog.Builder builder1;
    private DatabaseReference refDash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        drawerLayout1=findViewById(R.id.user_drawer_layout);
        builder1=new AlertDialog.Builder(this);
        navigationView1=findViewById(R.id.nav_view_admin_new);
        toolbar1=findViewById(R.id.topAppBar_user);
//        logo=findViewById(R.id.logo);

        loading=(LinearLayout)findViewById(R.id.lin_load_ani);

        //reference for visibilty restriction
        refDash=FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getUid());

        //mAuth for logout activity
        mAuth=FirebaseAuth.getInstance();

        ActionBar actionBar = getSupportActionBar();



        //dashboard details findviewbyid
        scanner=findViewById(R.id.scancode);
        manualentry = findViewById(R.id.manualentry);
        history = findViewById(R.id.histotydetails);

        //drawer_layout navigation
        navigationView1.bringToFront();
        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(this,drawerLayout1,toolbar1,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        toggle.syncState();

        navigationView1.setNavigationItemSelectedListener(dashboard.this);

        navigationView1.setCheckedItem(R.id.nav_home_admin);
        navigationView1.setCheckedItem(R.id.new_id);
        navigationView1.setCheckedItem(R.id.nav_newQR);
        navigationView1.setCheckedItem(R.id.nav_pending);
        navigationView1.setCheckedItem(R.id.nav_solved);
        navigationView1.setCheckedItem(R.id.emergency_contact);

        View headerview=navigationView1.getHeaderView(0);


        //dashboard details visibility
        history.setVisibility(View.GONE);
        manualentry.setVisibility(View.GONE);
        scanner.setVisibility(View.GONE);


        //visibility Restrictions
        refDash.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String pos=snapshot.child("position").getValue(String.class);

                if(snapshot.exists()){
                    if(pos.equals("admin")){

                        loading.setVisibility(View.GONE);

                        history.setVisibility(View.VISIBLE);
                        manualentry.setVisibility(View.VISIBLE);
                        scanner.setVisibility(View.VISIBLE);

                        YoYo.with(Techniques.SlideInRight).duration(1000).playOn(scanner);
                        YoYo.with(Techniques.SlideInLeft).duration(1000).playOn(history);
                        YoYo.with(Techniques.SlideInRight).duration(1000).playOn(manualentry);
//                        YoYo.with(Techniques.Shake).duration(1000).playOn(logo);

                        Menu menu=navigationView1.getMenu();
                        MenuItem nav_QR=menu.findItem(R.id.nav_newQR);
                        nav_QR.setVisible(true);

                        MenuItem upData=menu.findItem(R.id.update_data);
                        upData.setVisible(true);

                        MenuItem new_id=menu.findItem(R.id.new_id);
                        new_id.setVisible(true);

                        MenuItem nav_pend=menu.findItem(R.id.nav_pending);
                        nav_pend.setVisible(true);

                        MenuItem nav_sol=menu.findItem(R.id.nav_solved);
                        nav_sol.setVisible(true);

                        MenuItem nav_det=menu.findItem(R.id.nav_details);
                        nav_det.setVisible(true);

                        MenuItem nav_emergencyContect=menu.findItem(R.id.emergency_contact);
                        nav_emergencyContect.setVisible(true);


                    }else if(pos.equals("user")){

                        loading.setVisibility(View.GONE);

                        scanner.setVisibility(View.VISIBLE);
                        history.setVisibility(View.VISIBLE);

                        YoYo.with(Techniques.SlideInRight).duration(1000).playOn(scanner);
                        YoYo.with(Techniques.SlideInLeft).duration(1000).playOn(history);

                        Menu menu=navigationView1.getMenu();
                        MenuItem nav_pend=menu.findItem(R.id.nav_pending);
                        nav_pend.setVisible(true);

                        MenuItem nav_sol=menu.findItem(R.id.nav_solved);
                        nav_sol.setVisible(true);

                        MenuItem nav_det=menu.findItem(R.id.nav_details);
                        nav_det.setVisible(true);
                    }else if(pos.equals("technician")){

                        loading.setVisibility(View.GONE);

                        scanner.setVisibility(View.VISIBLE);
                        history.setVisibility(View.VISIBLE);

                        YoYo.with(Techniques.SlideInRight).duration(1000).playOn(scanner);
                        YoYo.with(Techniques.SlideInLeft).duration(1000).playOn(history);

                        Menu menu=navigationView1.getMenu();
                        MenuItem nav_pend=menu.findItem(R.id.nav_pending);
                        nav_pend.setVisible(true);

                        MenuItem nav_sol=menu.findItem(R.id.nav_solved);
                        nav_sol.setVisible(true);

                        MenuItem nav_det=menu.findItem(R.id.nav_details);
                        nav_det.setVisible(true);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //On click listeners
        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dashboard.this,ScannerPage.class));
            }
        });

        manualentry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboard.this,manualentry.class));
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboard.this, Dep_wise_history.class));
            }
        });


    }


    @Override
    public void onBackPressed() {
        if(drawerLayout1.isDrawerOpen(GravityCompat.START)){
            drawerLayout1.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
        super.onBackPressed();

    }

    //Drawe layout Navigation setting
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){


            case R.id.nav_home_admin:
                Intent intent=new Intent(dashboard.this, dashboard.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;

            case R.id.new_id:
                startActivity(new Intent(dashboard.this, Qr_id_generator.class));
                break;

            case R.id.nav_newQR:
                startActivity(new Intent(dashboard.this, Newqrcode.class));
                break;


            case R.id.nav_pending:
                startActivity(new Intent(dashboard.this, adminpendingcomplaint.class));
                break;

            case R.id.nav_solved:
                startActivity(new Intent(dashboard.this, adminregistercomplaint.class));
                break;
            case R.id.update_data:
                startActivity(new Intent(dashboard.this,update_database.class));
                break;

            case R.id.emergency_contact:
                startActivity(new Intent(dashboard.this,emergencyContact.class));
                break;
            case R.id.nav_details:
                startActivity(new Intent(dashboard.this,Dep_wise_history.class));
                break;

            case R.id.nav_logOut:
                builder1.setTitle("Alert")
                        .setMessage("Are you sure to Log out")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mAuth.signOut();
                                Intent intent=new Intent(dashboard.this, loginpage.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
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
        return true;
    }


}