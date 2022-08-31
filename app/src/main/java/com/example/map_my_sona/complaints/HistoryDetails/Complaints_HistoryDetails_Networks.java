package com.example.map_my_sona.complaints.HistoryDetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.map_my_sona.R;
import com.example.map_my_sona.admin.AdminDashboard;
import com.example.map_my_sona.complaints.Complaint_details;
import com.example.map_my_sona.complaints.complaints_history_Adapter;
import com.example.map_my_sona.dashboard;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Complaints_HistoryDetails_Networks extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    RecyclerView recyclerView_complaints_history_networks;
    DatabaseReference reference_complaints_history_networks;
    complaints_history_Adapter adapter_complaint_history_networks;
    ArrayList<Complaint_details> arrayList_complaints_history_networks;

    //fliter
    TextInputLayout hisfliter_networks;
    AutoCompleteTextView hisflitertext_networks;
    Spinner spin_networks;
    MaterialToolbar toolbar;
    private DatabaseReference refDash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_history_details_networks);

        recyclerView_complaints_history_networks=findViewById(R.id.recyclerview_complaints_history_networks);
        reference_complaints_history_networks= FirebaseDatabase.getInstance().getReference("complaints").child("Network");

        recyclerView_complaints_history_networks.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        recyclerView_complaints_history_networks.setLayoutManager(linearLayoutManager);

        //recyclerView_complaints_history_networks.setLayoutManager(new LinearLayoutManager(this));

        refDash= FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getUid());

        arrayList_complaints_history_networks=new ArrayList<>();
        adapter_complaint_history_networks = new complaints_history_Adapter(arrayList_complaints_history_networks,this);
        recyclerView_complaints_history_networks.setAdapter(adapter_complaint_history_networks);

        reference_complaints_history_networks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Complaint_details user =dataSnapshot.getValue(Complaint_details.class);
                    arrayList_complaints_history_networks.add(user);
                }
                adapter_complaint_history_networks.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Complaints_HistoryDetails_Networks.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        toolbar= findViewById(R.id.topAppBar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"your icon was clicked",Toast.LENGTH_SHORT).show();
                refDash.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String pos=snapshot.child("position").getValue(String.class);
                        if(pos.equals("admin")){
                            startActivity(new Intent(Complaints_HistoryDetails_Networks.this, dashboard.class));
                        }
                        else{
                            startActivity(new Intent(Complaints_HistoryDetails_Networks.this, AdminDashboard.class));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
        ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
        ((TextView) adapterView.getChildAt(0)).setTextSize(20);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}