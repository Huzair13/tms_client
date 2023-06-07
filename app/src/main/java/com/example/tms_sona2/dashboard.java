package com.example.tms_sona2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.tms_sona2.admin.DetailsAssignAdmin;
import com.example.tms_sona2.complaints.Dep_wise_history;
import com.example.tms_sona2.rating.Rating_and_Feedback;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class dashboard<FirstFragment, SecondFragment, ThirdFragment> extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //
    private long backPressedTime;
    private Toast backToast;

    LinearLayout loading;

    DrawerLayout drawerLayout1;
    NavigationView navigationView1;
    LinearLayout Linear1;
    MaterialToolbar toolbar_user;
    ImageView logo;
    BottomNavigationView bottomNavigationView;

    private MaterialCardView scanner;
    private  MaterialCardView manualentry;
    private MaterialCardView history;

    SharedPreferences sharedPreferences;

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



        toolbar_user=findViewById(R.id.topAppBar_user);

//
//        logo=findViewById(R.id.logo);
        loading=(LinearLayout)findViewById(R.id.lin_load_ani);

        //reference for visibilty restriction
        refDash=FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getUid());
        refDash.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(((snapshot.child("position").getValue(String.class)).equals("admin")) || ((snapshot.child("position").getValue(String.class)).equals("superadmin"))){
                    toolbar_user.setTitle("Admin-Dashboard");
                }else{
                    toolbar_user.setTitle("Dashboard");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //mAuth for logout activity
        mAuth=FirebaseAuth.getInstance();

        ActionBar actionBar = getSupportActionBar();



        //dashboard details findviewbyid
        scanner=findViewById(R.id.scancode);
        manualentry = findViewById(R.id.manualentry);
        history = findViewById(R.id.histotydetails);

        //drawer_layout navigation
        navigationView1.bringToFront();
        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(this,drawerLayout1,toolbar_user,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        toggle.syncState();

        navigationView1.setNavigationItemSelectedListener(dashboard.this);

        navigationView1.setCheckedItem(R.id.nav_home_admin);
        //navigationView1.setCheckedItem(R.id.new_id);
        navigationView1.setCheckedItem(R.id.nav_newQR);
//        navigationView1.setCheckedItem(R.id.nav_pending);
//        navigationView1.setCheckedItem(R.id.nav_solved);
        navigationView1.setCheckedItem(R.id.emergency_contact);
        navigationView1.setCheckedItem(R.id.upload_data);
        //navigationView1.setCheckedItem(R.id.nav_assets);
//        navigationView1.setCheckedItem(R.id.nav_NewAssets);


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

                        navigationView1.getMenu().getItem(0).setChecked(true);

                        MenuItem upData=menu.findItem(R.id.update_data);
                        upData.setVisible(true);

//                        MenuItem new_id=menu.findItem(R.id.new_id);
//                        new_id.setVisible(true);

//                        MenuItem nav_pend=menu.findItem(R.id.nav_pending);
//                        nav_pend.setVisible(true);
//
//                        MenuItem nav_sol=menu.findItem(R.id.nav_solved);
//                        nav_sol.setVisible(true);

//                        MenuItem nav_asset=menu.findItem(R.id.nav_assets);
//                        nav_asset.setVisible(true);

                        MenuItem nav_emergencyContect=menu.findItem(R.id.emergency_contact);
                        nav_emergencyContect.setVisible(true);

                        MenuItem navi_report=menu.findItem(R.id.upload_data);
                        navi_report.setVisible(true);

//                        MenuItem nav_NewAss=menu.findItem(R.id.nav_NewAssets);
//                        nav_NewAss.setVisible(true);

                    }else if(pos.equals("user")){

                        loading.setVisibility(View.GONE);

                        scanner.setVisibility(View.VISIBLE);
                        history.setVisibility(View.VISIBLE);
                        manualentry.setVisibility(View.VISIBLE);



                        YoYo.with(Techniques.SlideInRight).duration(1000).playOn(scanner);
                        YoYo.with(Techniques.SlideInLeft).duration(1000).playOn(history);
                        YoYo.with(Techniques.SlideInRight).duration(1000).playOn(manualentry);


                        Menu menu=navigationView1.getMenu();
//                        MenuItem nav_pend=menu.findItem(R.id.nav_pending);
//                        nav_pend.setVisible(true);
//
//                        MenuItem nav_sol=menu.findItem(R.id.nav_solved);
//                        nav_sol.setVisible(true);

                        //
                        MenuItem nav_QR=menu.findItem(R.id.nav_newQR);
                        nav_QR.setVisible(true);

                        MenuItem upData=menu.findItem(R.id.update_data);
                        upData.setVisible(true);
//
//                        MenuItem new_id=menu.findItem(R.id.new_id);
//                        new_id.setVisible(true);


                        MenuItem nav_emergencyContect=menu.findItem(R.id.emergency_contact);
                        nav_emergencyContect.setVisible(true);

                        MenuItem navi_report=menu.findItem(R.id.upload_data);
                        navi_report.setVisible(true);


                    }else if(pos.equals("technician")){

                        loading.setVisibility(View.GONE);

                        scanner.setVisibility(View.VISIBLE);
                        history.setVisibility(View.VISIBLE);
                        manualentry.setVisibility(View.VISIBLE);

                        YoYo.with(Techniques.SlideInRight).duration(1000).playOn(scanner);
                        YoYo.with(Techniques.SlideInLeft).duration(1000).playOn(history);
                        YoYo.with(Techniques.SlideInRight).duration(1000).playOn(manualentry);

                        Menu menu=navigationView1.getMenu();
//                        MenuItem nav_pend=menu.findItem(R.id.nav_pending);
//                        nav_pend.setVisible(true);

//                        MenuItem nav_sol=menu.findItem(R.id.nav_solved);
//                        nav_sol.setVisible(true);



                        MenuItem navi_report=menu.findItem(R.id.upload_data);
                        navi_report.setVisible(true);

                    }
                    else if(pos.equals("superadmin")){
                        loading.setVisibility(View.GONE);

                        history.setVisibility(View.VISIBLE);
                        scanner.setVisibility(View.VISIBLE);
                        manualentry.setVisibility(View.GONE);

                        YoYo.with(Techniques.SlideInRight).duration(1000).playOn(scanner);
                        YoYo.with(Techniques.SlideInLeft).duration(1000).playOn(history);

                        Menu menu=navigationView1.getMenu();
                        MenuItem nav_QR=menu.findItem(R.id.nav_newQR);
                        nav_QR.setVisible(false);

                        navigationView1.getMenu().getItem(0).setChecked(true);

                        MenuItem upData=menu.findItem(R.id.update_data);
                        upData.setVisible(false);

                        MenuItem upload_data=menu.findItem(R.id.upload_data);
                        upload_data.setVisible(false);

                        MenuItem userlist=menu.findItem(R.id.userList);
                        userlist.setVisible(false);
//
                        MenuItem rat=menu.findItem(R.id.nav_ratings);
                        rat.setVisible(false);

//                        MenuItem nav_asset=menu.findItem(R.id.nav_assets);
//                        nav_asset.setVisible(true);

                        MenuItem nav_emergencyContect=menu.findItem(R.id.emergency_contact);
                        nav_emergencyContect.setVisible(true);


//                        MenuItem nav_NewAss=menu.findItem(R.id.nav_NewAssets);
//                        nav_NewAss.setVisible(true);
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


        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboard.this, Dep_wise_history.class));
            }
        });


        manualentry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboard.this, DetailsAssignAdmin.class));
            }
        });


    };




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

    //bottom navigation

    //Drawe layout Navigation setting
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){


            case R.id.nav_home_admin:
                Intent intent=new Intent(dashboard.this, dashboard.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;

//            case R.id.new_id:
//                startActivity(new Intent(dashboard.this, Qr_id_generator.class));
//                break;

            case R.id.nav_newQR:
                startActivity(new Intent(dashboard.this, Newqrcode.class));
                break;

//            case R.id.nav_NewAssets:
//                startActivity(new Intent(dashboard.this,add_assets.class));
//                break;


//            case R.id.nav_pending:
//                startActivity(new Intent(dashboard.this, adminpendingcomplaint.class));
//                break;
//
//            case R.id.nav_solved:
//                startActivity(new Intent(dashboard.this, adminregistercomplaint.class));
//                break;
            case R.id.update_data:
                startActivity(new Intent(dashboard.this,update_database.class));
                break;

//            case R.id.nav_assign_admin:
//                startActivity(new Intent(dashboard.this, DetailsAssignAdmin.class));
//                break;
                                        //need to change
            case R.id.emergency_contact:
                startActivity(new Intent(dashboard.this, emergencyContact.class));
                break;

            case R.id.bottom_emer:
                startActivity(new Intent(dashboard.this,emergencyContact.class));
                break;

//            case R.id.nav_assets:
//                startActivity(new Intent(dashboard.this, Assets.class));
//                break;

            case R.id.nav_ratings:
                startActivity(new Intent(dashboard.this,Rating_and_Feedback.class));
                break;

            case R.id.userList:
                startActivity(new Intent(dashboard.this,Ulist_Activity.class));
                break;

            case R.id.upload_data:
                startActivity(new Intent(dashboard.this, BulkuploadActivity.class));
                break;

//            case R.id.testing_menu:
//                startActivity(new Intent(dashboard.this,AdminDashboard.class));
//                break;

            case R.id.nav_ratings_user:
                startActivity(new Intent(dashboard.this,Rating_and_Feedback_user.class));
                break;

            case R.id.nav_logOut:
                builder1.setTitle("Alert")
                        .setMessage("Are you sure to Log out ?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mAuth.signOut();
                                Intent intent=new Intent(dashboard.this, loginpage.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);

//                                SharedPreferences sharedPreferences =getSharedPreferences(loginpage.PREFS_NAME,0);
//                                SharedPreferences.Editor editor=sharedPreferences.edit();
//                                editor.putBoolean("hasLoggedIn",false);
//                                editor.commit();

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


//                SharedPreferences preferences = getSharedPreferences("checked",MODE_PRIVATE);
//                SharedPreferences.Editor editor =preferences.edit();
//                editor.putString("remember","false");
//                editor.apply();
//                finish();
                break;
        }
        return true;
    }


}