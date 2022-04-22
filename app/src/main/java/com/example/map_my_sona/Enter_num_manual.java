package com.example.map_my_sona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.map_my_sona.complaints.complaint_Page;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Enter_num_manual extends AppCompatActivity {

    TextView tv;
    Button bt;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_num_manual);

        tv=findViewById(R.id.editTextTextPersonName);
        bt=findViewById(R.id.button);
        databaseReference = FirebaseDatabase.getInstance().getReference("Datas");

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tv_str=tv.getText().toString();
                if(tv_str.isEmpty()){
                    Toast.makeText(Enter_num_manual.this, "Cant be empty", Toast.LENGTH_SHORT).show();
                }
                else if(!tv_str.isEmpty()){
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(tv_str)){
                                Intent intent = new Intent(getBaseContext(), complaint_Page.class);
                                intent.putExtra("SCAN_RESULT", tv_str);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(Enter_num_manual.this, "There is no product details available for the ID given", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

    }
}