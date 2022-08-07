package com.example.map_my_sona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RegisterPage extends AppCompatActivity {
    TextView alreadyreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        alreadyreg=findViewById(R.id.alreadyreg);

        alreadyreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterPage.this, loginpage.class));
                //Toast.makeText(AdminDashboard.this, us, Toast.LENGTH_SHORT).show();
            }
        });

    }
}