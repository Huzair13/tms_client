package com.example.map_my_sona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.map_my_sona.complaints.Complaint_details;
import com.example.map_my_sona.complaints.complaint_Page;
import com.example.map_my_sona.manualComplaints.ManualComplaint_page;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import papaya.in.sendmail.SendMail;

public class report_page extends AppCompatActivity {

    EditText report;
    Button report_btn;
    DatabaseReference dbRef;
    String report_str;
    String userEmail;
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_page);

        report=(EditText) findViewById(R.id.report_text);

        report_btn=(Button) findViewById(R.id.report_submit_btn);

        toolbar= findViewById(R.id.reporttopAppBar);

        report_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidation();
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"your icon was clicked",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(report_page.this, dashboard.class));
            }
        });
    }

    private void checkValidation() {
        report_str=report.getText().toString();
        if(report_str.isEmpty()){
            report.setError("Please fill something in the report column to submit");
            report.requestFocus();
        }
        else{
            submitReport();
        }
    }

    private void submitReport() {
        dbRef = FirebaseDatabase.getInstance().getReference().child("Reports");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            userEmail = user.getEmail();
        }
        final String uniqueKey = dbRef.push().getKey();

        Report_Details report_details = new Report_Details(report_str,userEmail);
        dbRef.child(uniqueKey).setValue(report_details).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(report_page.this, "Report Submited Successfully", Toast.LENGTH_SHORT).show();

                SendMail mail1=new SendMail("mapmysona@gmail.com",
                        "mms@2022",
                        "ahamedhuzair13@gmail.com",
                        "A Report has been submitted",
                        "A Report has been submitted by the user "+userEmail+" . Please check report : "+report_str
                );
                mail1.execute();

                SendMail mail2=new SendMail("mapmysona@gmail.com",
                        "mms@2022",
                        "srisanjanaarunkumar@gmail.com",
                        "A Report has been submitted",
                        "A Report has been submitted by the user "+userEmail+". Please check report : "+report_str
                );
                mail2.execute();

                SendMail mail3=new SendMail("mapmysona@gmail.com",
                        "mms@2022",
                        "bs.vigneshwaran@gmail.com",
                        "A Report has been submitted",
                        "A Report has been submitted by the user "+userEmail+" . Please check report : "+report_str
                );
                mail3.execute();

                SendMail mail4=new SendMail("mapmysona@gmail.com",
                        "mms@2022",
                        "shreedev2k3@gmail.com",
                        "A Report has been submitted",
                        "A Report has been submitted by the user "+userEmail+" . Please check report : "+report_str
                );
                mail4.execute();

                Intent intent = new Intent(report_page.this, dashboard.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(report_page.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

}