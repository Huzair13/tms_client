package com.example.tms_sona2.assets.tablets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tms_sona2.R;
import com.example.tms_sona2.assets.AssetDeatils1;
import com.example.tms_sona2.assets.Assets;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class asset_tablet_recycle extends AppCompatActivity {
    MaterialToolbar toolbar;

    private RecyclerView recyclerView;
    DatabaseReference reference;
    AssetTabletAdapter adapter;
    ArrayList<AssetDeatils1> asset_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_tablet_recycle);

        recyclerView=findViewById(R.id.assets_tablet);
        reference= FirebaseDatabase.getInstance().getReference("Assets").child("Tablets");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        asset_details=new ArrayList<AssetDeatils1>();
        adapter = new AssetTabletAdapter(asset_details,this);
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
                Toast.makeText(asset_tablet_recycle.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        toolbar= findViewById(R.id.topAppBar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(asset_tablet_recycle.this, Assets.class));
            }
        });
    }
}