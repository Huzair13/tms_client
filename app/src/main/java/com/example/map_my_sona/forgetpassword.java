package com.example.map_my_sona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class forgetpassword extends AppCompatActivity {
    private TextInputEditText emailAddressForgePass;
    private Button resetPassBtn;
    String email;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_forgetpassword);
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // Customize the back button
//        actionBar.setHomeAsUpIndicator(R.drawable.mybutton);

        // showing the back button in action bar
//        actionBar.setDisplayHomeAsUpEnabled(true);

        emailAddressForgePass=(TextInputEditText) findViewById(R.id.ForgetPassEmail);
        resetPassBtn=(Button) findViewById(R.id.forgetpassbtn);

        auth=FirebaseAuth.getInstance();

        resetPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });

    }

    private void validateData() {
        email=emailAddressForgePass.getText().toString();

        if(email.isEmpty()){
            emailAddressForgePass.setError("Email is required !!!");
            emailAddressForgePass.requestFocus();
            return;
        }
        else{
            resetPassword();
        }
    }

    private void resetPassword() {
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(forgetpassword.this, "Check your email", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(forgetpassword.this,loginpage.class));
                            finish();
                        }else{
                            Toast.makeText(forgetpassword.this, "Error :"+task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}