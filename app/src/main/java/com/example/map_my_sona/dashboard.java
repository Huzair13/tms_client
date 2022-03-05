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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.map_my_sona.complaints.Complaints_HistoryDetails;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout1;
    NavigationView navigationView1;
    LinearLayout Linear1;
    MaterialToolbar toolbar1;

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
        refDash=FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getUid());

        mAuth=FirebaseAuth.getInstance();
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
//        getSupportActionBar().setLogo(R.drawable.logout);

        // Customize the back button
//        actionBar.setHomeAsUpIndicator(R.drawable.logout);

        // showing the back button in action bar
//        actionBar.setDisplayHomeAsUpEnabled(true);

        scanner=findViewById(R.id.scancode);
        manualentry = findViewById(R.id.manualentry);
        history = findViewById(R.id.histotydetails);

        navigationView1.bringToFront();
        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(this,drawerLayout1,toolbar1,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        toggle.syncState();

        navigationView1.setNavigationItemSelectedListener(dashboard.this);

        navigationView1.setCheckedItem(R.id.nav_home_admin);
        navigationView1.setCheckedItem(R.id.new_id);
        navigationView1.setCheckedItem(R.id.nav_newQR);
        navigationView1.setCheckedItem(R.id.nav_pending);
        navigationView1.setCheckedItem(R.id.nav_solved);


        View headerview=navigationView1.getHeaderView(0);

        history.setVisibility(View.GONE);
        manualentry.setVisibility(View.GONE);

        refDash.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String pos=snapshot.child("position").getValue(String.class);

               if(snapshot.exists()){
                    if(pos.equals("admin")){
                        history.setVisibility(View.VISIBLE);
                        manualentry.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
                startActivity(new Intent(dashboard.this, Complaints_HistoryDetails.class));
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


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
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