package com.example.tms_sona2.complaints.HistoryDetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tms_sona2.R;
import com.example.tms_sona2.complaints.Complaint_details;
import com.example.tms_sona2.complaints.Dep_wise_history;
import com.example.tms_sona2.complaints.complaints_history_Adapter;
import com.example.tms_sona2.loginpage;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Complaints_HistoryDetails_Plumber extends AppCompatActivity {

    RecyclerView recyclerView_complaints_history_plumber;
    DatabaseReference reference_complaints_history_plumber;
    complaints_history_Adapter adapter_complaint_history_plumber;
    ArrayList<Complaint_details> arrayList_complaints_history_plumber;

    private TextView Solved,Pending;
    private DatabaseReference mDatabase;

    TextInputLayout hisfliter_plumber;
    AutoCompleteTextView hisflitertext_plumber;
    Spinner spin_plumber;
    String posofuser;
    MaterialToolbar toolbar;
    AlertDialog.Builder builder11;
    FirebaseAuth mAuth;
    private DatabaseReference refDash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_history_details_plumber);

        Solved = findViewById(R.id.solved_plum);
        Pending=findViewById(R.id.pending_plum);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("complaints");

        mDatabase.child("Pluming").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int countc=0;
                int countp=0;
                for(DataSnapshot ds: snapshot.getChildren()){
                    Map<String,Object> map=(Map<String, Object>) ds.getValue();
                    Object avg=map.get("status");
                    String str=String.valueOf(avg);
                    //Float gvalue=Float.parseFloat(String.valueOf(avg));
                    if(str.equals("Completed")){
                        countc+=1;
                    }
                    else {
                        countp += 1;
                    }
                }
                Solved.setText(String.valueOf(countc));
                Pending.setText(String.valueOf(countp));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recyclerView_complaints_history_plumber=findViewById(R.id.recyclerview_complaints_history_plumber);
        reference_complaints_history_plumber= FirebaseDatabase.getInstance().getReference("complaints").child("Pluming");

        recyclerView_complaints_history_plumber.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        recyclerView_complaints_history_plumber.setLayoutManager(linearLayoutManager);

        refDash= FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getUid());


        //recyclerView_complaints_history_plumber.setLayoutManager(new LinearLayoutManager(this));

        arrayList_complaints_history_plumber=new ArrayList<>();
        adapter_complaint_history_plumber = new complaints_history_Adapter(arrayList_complaints_history_plumber,this);
        recyclerView_complaints_history_plumber.setAdapter(adapter_complaint_history_plumber);

        builder11=new AlertDialog.Builder(this);
        mAuth=FirebaseAuth.getInstance();

        //ORDER BY
        reference_complaints_history_plumber.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Complaint_details user =dataSnapshot.getValue(Complaint_details.class);
                    arrayList_complaints_history_plumber.add(user);
                }
                adapter_complaint_history_plumber.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Complaints_HistoryDetails_Plumber.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        toolbar= findViewById(R.id.topAppBar_plumber);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"your icon was clicked",Toast.LENGTH_SHORT).show();
                refDash.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String pos=snapshot.child("position").getValue(String.class);
                        posofuser=snapshot.child("position").getValue(String.class);
                        if(pos.equals("admin")||pos.equals("supervisor")){
                            startActivity(new Intent(Complaints_HistoryDetails_Plumber.this, Dep_wise_history.class));
                        }
                        else{
                            builder11.setTitle("Alert")
                                    .setMessage("Are you sure to Log out ?")
                                    .setCancelable(true)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            mAuth.signOut();
                                            Intent intent=new Intent(Complaints_HistoryDetails_Plumber.this, loginpage.class);
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
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

//        if(posofuser.equals("plumber")){
//            onBackPressed();
//        }
    }
//
//    @Override
//    public void onBackPressed() {
//        Toast.makeText(this, "You are already in the home page", Toast.LENGTH_SHORT).show();
//    }
}