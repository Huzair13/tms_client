package com.example.map_my_sona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationView;

public class admin_dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    LinearLayout Linear;
    MaterialToolbar toolbar;
    AlertDialog.Builder builder;
    MaterialCardView admincom;
    MaterialCardView registerdetail;
    MaterialCardView pendingcomplaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        drawerLayout=findViewById(R.id.admin_Drawer_Layout);

        toolbar=findViewById(R.id.topAppBar_admin);
        admincom = findViewById(R.id.admincomplaint_admin);
        registerdetail=findViewById(R.id.registerdetail_admin);
        pendingcomplaint=findViewById(R.id.pending_admin);

        Linear=(LinearLayout)findViewById(R.id.linear_layout_admin);

        builder=new AlertDialog.Builder(this);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(admin_dashboard.this);

        navigationView.setCheckedItem(R.id.nav_home_admin);
        navigationView.setCheckedItem(R.id.new_id);
        navigationView.setCheckedItem(R.id.nav_newQR);
        navigationView.setCheckedItem(R.id.nav_pending);
        navigationView.setCheckedItem(R.id.nav_solved);


        View headerview=navigationView.getHeaderView(0);

        //
//        admincom.animate().translation(2200).setDuration(1000).setStartDelay(000);
//        registerdetail.animate().translation(2200).setDuration(1000).setStartDelay(000);
//        pendingcomplaint.animate().translation(2200).setDuration(1000).setStartDelay(000);

        admincom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(admin_dashboard.this,ScannerPage.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);

            }
        });

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.nav_home_admin:
                startActivity(new Intent(admin_dashboard.this, admin_dashboard.class));
                break;

            case R.id.new_id:
                startActivity(new Intent(admin_dashboard.this, Qr_id_generator.class));
                break;

            case R.id.nav_newQR:
                startActivity(new Intent(admin_dashboard.this, Newqrcode.class));
                break;


            case R.id.nav_pending:
                startActivity(new Intent(admin_dashboard.this, adminpendingcomplaint.class));
                break;

            case R.id.nav_solved:
                startActivity(new Intent(admin_dashboard.this, adminregistercomplaint.class));
                break;
            case R.id.update_data:
                startActivity(new Intent(admin_dashboard.this,update_database.class));
                break;

            case R.id.nav_logOut:
                builder.setTitle("Alert")
                        .setMessage("Are you sure to Log out")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent=new Intent(admin_dashboard.this, AdminLoginPage.class);
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