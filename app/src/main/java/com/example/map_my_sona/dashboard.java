package com.example.map_my_sona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.map_my_sona.complaints.Complaints_HistoryDetails;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;

public class dashboard extends AppCompatActivity {

    private MaterialCardView scanner;
    private  MaterialCardView manualentry;
    private MaterialCardView history;

    FirebaseAuth mAuth;

    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

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

        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dashboard.this,ScannerPage.class));
            }
        });

        manualentry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboard.this,ScannerPage.class));
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.toolappbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.logout_menu:{
                mAuth.signOut();
                Toast.makeText(dashboard.this, "THIS IS LOGOUT BUTTON", Toast.LENGTH_SHORT).show();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }


}