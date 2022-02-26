package com.example.map_my_sona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class AdminLoginPage extends AppCompatActivity {
    private TextInputEditText loginEmailAdmin, LoginPasswordAdmin ;
    Button LoginBtnAdmin;
    private TextView Changepassword;
    private TextView Forgetpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login_page);

        loginEmailAdmin=findViewById(R.id.loginemailInputAdmin);
        LoginPasswordAdmin=findViewById(R.id.loginpasswordInputAdmin);
        LoginBtnAdmin=findViewById(R.id.loginbuttonAdmin);
         Changepassword = findViewById(R.id.changepassword);
         Forgetpassword =findViewById(R.id.forgetpassword);
        //


        LoginBtnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String AdminEmail=loginEmailAdmin.getText().toString();
                String AdminPassword=LoginPasswordAdmin.getText().toString();
                
                checkAdmin(AdminEmail,AdminPassword);
            }
        });

        //
        Changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminLoginPage.this,changepassword.class));
            }
        });

        Forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminLoginPage.this,forgetpassword.class));
            }
        });
    }

    private void checkAdmin(String adminEmail, String adminPassword) {
        if(adminEmail.equals("MapMySona") & adminPassword.equals("MapMySona")){
            openAdminPanel();
        }
        else if(TextUtils.isEmpty(adminEmail)){
            loginEmailAdmin.setError("UserName can't be empty !!!");
            loginEmailAdmin.requestFocus();
        }
        else if(TextUtils.isEmpty(adminPassword)){
            LoginPasswordAdmin.setError("Password can't be empty !!!");
            LoginPasswordAdmin.requestFocus();
        }
        else {
            Toast.makeText(AdminLoginPage.this, "Sorry !!! Your details are not Correct !!!", Toast.LENGTH_LONG).show();
        }
    }
    private void openAdminPanel() {
        Intent intent=new Intent(AdminLoginPage.this, dashboard.class);
        startActivity(intent);
    }


}