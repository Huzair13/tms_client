package com.example.tms_sona2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterPage extends AppCompatActivity {
    TextView alreadyreg;
    private EditText username,email,password,passwordConfirm;
    private String uname_str,email_str,pass_str,pass_confirm_str;
    private Button Signupbtn;
    FirebaseAuth mAuth ;
    ProgressDialog progressDialog;
    Boolean bool;
    String s="yes";

    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        alreadyreg=findViewById(R.id.alreadyreg);

        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();

        username=findViewById(R.id.signupusernamelInput);
        email=findViewById(R.id.signupemaillInput);
        password=findViewById(R.id.signuppassInput);
        passwordConfirm=findViewById(R.id.signuppassInputConfirm);

        Signupbtn=findViewById(R.id.signUpBtn);

        database=FirebaseDatabase.getInstance();

        Signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckValidity();
//                PerforAuth();
            }
        });

        alreadyreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterPage.this, loginpage.class));
                //Toast.makeText(AdminDashboard.this, us, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void CheckValidity() {
        uname_str=username.getText().toString();
        email_str=email.getText().toString();
        pass_str=password.getText().toString();
        pass_confirm_str=passwordConfirm.getText().toString();

        if(uname_str.isEmpty()){
            username.setError("User name can't be empty");
            username.requestFocus();
        }
        else if(email_str.isEmpty()){
            email.setError("Email can't be empty");
            email.requestFocus();
        }
        else if(pass_str.isEmpty()){
            password.setError("Please enter your password");
            password.requestFocus();
        }else if(!pass_confirm_str.equals(pass_str)){
            passwordConfirm.setError("Password not matching");
            passwordConfirm.requestFocus();
        }else{
            PerforAuth();
        }
    }

//    private Boolean getBool() {
//        Boolean b = true;
//        database.getReference().child("usersLogin").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//               b = snapshot.hasChild(username.getText().toString());
//                Toast.makeText(RegisterPage.this, String.valueOf(b[0]), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }

    private void PerforAuth() {
        database.getReference().child("usersLogin").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.hasChild(username.getText().toString())){
                    progressDialog.setMessage("Please wait while Registration");
                    progressDialog.setTitle("Registration");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    mAuth.createUserWithEmailAndPassword(email_str,pass_str).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressDialog.dismiss();

                                String id=task.getResult().getUser().getUid();

                                UserDetails userDetails=new UserDetails(id,email_str);

                                database.getReference().child("usersLogin").child(uname_str).child("email").setValue(email_str);
                                database.getReference().child("usersLogin").child(uname_str).child("userID").setValue(id);

                                database.getReference().child("userDetails").child(uname_str).child("email").setValue(email_str);
                                database.getReference().child("userDetails").child(uname_str).child("userID").setValue(id);
                                database.getReference().child("userDetails").child(uname_str).child("position").setValue("newuser");

                                database.getReference().child("users").child(id).child("position").setValue("newuser");

                                //sendUserToNextActivity();
                                Toast.makeText(RegisterPage.this, "Registration Successful Log in Now", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(RegisterPage.this, loginpage.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);

                            }else{
                                progressDialog.dismiss();
                                Toast.makeText(RegisterPage.this, "ERROR !! May your email ID has been already registered.... Check your email ID please", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(RegisterPage.this, "Username Already Exists Please change your username", Toast.LENGTH_SHORT).show();
                }
               // Toast.makeText(RegisterPage.this, String.valueOf(username.getText().toString()), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
//    private void sendUserToNextActivity(){
//
//    }
}