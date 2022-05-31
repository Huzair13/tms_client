package com.example.map_my_sona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.map_my_sona.complaints.HistoryDetails.Complaints_HistoryDetails_Electricity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class asset_laptop_recycle extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference reference;
    AssetLaptopAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<AssetDeatils1> asset_details;

    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_laptop_recycle);

        recyclerView=findViewById(R.id.assets_laptop);
        reference= FirebaseDatabase.getInstance().getReference("Assets").child("Laptops");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        asset_details=new ArrayList<AssetDeatils1>();
        adapter = new AssetLaptopAdapter(asset_details,this);
        recyclerView.setAdapter(adapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    AssetDeatils1 user =dataSnapshot.getValue(AssetDeatils1.class);
                    asset_details.add(user);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(asset_laptop_recycle.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        toolbar = findViewById(R.id.topAppBar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(asset_laptop_recycle.this, Assets.class));
            }
        });
    }
}