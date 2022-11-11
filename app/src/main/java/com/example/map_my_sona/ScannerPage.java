package com.example.map_my_sona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.map_my_sona.admin.AdminDashboard;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ScannerPage extends AppCompatActivity {

    LinearLayout scanBtn;
    public static TextView scantxt;
    Button num_enter_man;
    //TextView manualltext;
    MaterialToolbar toolbar;
    private DatabaseReference refDash;

    AlertDialog.Builder builder1;
    FirebaseAuth mAuth;


//    private TextView manual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_page);

        scanBtn=findViewById(R.id.Scanner);
//        scantxt=(TextView) findViewById(R.id.scantxt);
        //manualltext=findViewById(R.id.entermanuallytext);

        builder1=new AlertDialog.Builder(this);

        refDash= FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getUid());

        num_enter_man=findViewById(R.id.num_enter_man);
        mAuth=FirebaseAuth.getInstance();

        num_enter_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ScannerPage.this,Enter_num_manual.class));
            }
        });

//        manual =(TextView) findViewById(R.id.ManualEntry);

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ScannerView.class));

            }
        });
        toolbar= findViewById(R.id.topAppBar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refDash.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String pos=snapshot.child("position").getValue(String.class);
                        if(pos.equals("admin") || pos.equals("superadmin")){
                            startActivity(new Intent(ScannerPage.this, dashboard.class));
                        }
                        else{
                            builder1.setTitle("Alert")
                                    .setMessage("Are you sure to Log out ?")
                                    .setCancelable(true)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            mAuth.signOut();
                                            Intent intent=new Intent(ScannerPage.this, loginpage.class);
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
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}