package com.example.tms_sona2.rating.rating_view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.tms_sona2.R;
import com.example.tms_sona2.complaints.Complaint_details;
import com.example.tms_sona2.rating.rating_adapter_elec;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class rating_assets extends AppCompatActivity {

    RecyclerView recyclerView_complaints_history;
    DatabaseReference reference_complaints_history;
    rating_adapter_elec adapter_complaint_history;
    ArrayList<Complaint_details> arrayList_complaints_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_assets);

        recyclerView_complaints_history=findViewById(R.id.recyclerview_rating_assets);
        reference_complaints_history= FirebaseDatabase.getInstance().getReference("complaints").child("Assets");

        recyclerView_complaints_history.setHasFixedSize(true);
        recyclerView_complaints_history.setLayoutManager(new LinearLayoutManager(this));

        arrayList_complaints_history=new ArrayList<>();
        adapter_complaint_history = new rating_adapter_elec(arrayList_complaints_history,this);
        recyclerView_complaints_history.setAdapter(adapter_complaint_history);

        reference_complaints_history.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Complaint_details user =dataSnapshot.getValue(Complaint_details.class);
                    arrayList_complaints_history.add(user);
                }
                adapter_complaint_history.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(rating_assets.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}