package com.example.map_my_sona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.map_my_sona.complaints.viewDetails.historyviewdetails_carpenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginpage extends AppCompatActivity {

    private TextInputEditText LogEmail,username;
    private TextInputEditText LogPassword;
    private TextView ForgetPass;
    private  TextView Changepass;
    private Button btnLogin;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    public static String us_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        //progressDialouge
        progressDialog=new ProgressDialog(this);

        //LogEmail=findViewById(R.id.loginemailInput);
        username=findViewById(R.id.loginemailInput);
        us_name=username.getText().toString();
        LogPassword=findViewById(R.id.loginpasswordInput);
        btnLogin=findViewById(R.id.loginbutton);
        ForgetPass=findViewById(R.id.forgetpassword);
        Changepass=findViewById(R.id.changepassword);

        mAuth=FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(view -> {
            LoginUserCheck();
        });

        ForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginpage.this,forgetpassword.class));
            }
        });

        Changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginpage.this,changepassword.class));
            }
        });
    }

    private void LoginUserCheck() {

        String uname=username.getText().toString();
        String password =LogPassword.getText().toString();
        if(TextUtils.isEmpty(uname)){
            username.setError("UserName can't be empty");
            username.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            LogPassword.setError("Password Cannot be empty");
            LogPassword.requestFocus();
        }else if(uname.endsWith("@gmail.com") || uname.endsWith("@GMAIL.COM") ||
                uname.endsWith("@yahoo.com") || uname.endsWith("@YAHOO.COM")){
            String email=uname;

            progressDialog.setMessage("Logging in please wait.....");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            logInwithUserName(email,password);
        }else{
            DatabaseReference reference=FirebaseDatabase.getInstance().getReference("usersLogin");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChild(uname)){
                        String email=snapshot.child(uname).child("email").getValue(String.class);

                        progressDialog.setMessage("Logging in please wait.....");
                        progressDialog.setTitle("Login");
                        progressDialog.setCanceledOnTouchOutside(false);
                        progressDialog.show();

                        logInwithUserName(email,password);
                    }else{
                        Toast.makeText(loginpage.this, "Username is not correct", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }

    private void logInwithUserName(String email, String password) {

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(loginpage.this, "User Logged in successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(loginpage.this, dashboard.class));
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(loginpage.this, "Login Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}