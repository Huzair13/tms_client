package com.example.map_my_sona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

    //private SharedPrefManager sharedPrefManager;
    public static final String PREFS_NAME = "MyPrefsFile";

    private TextInputEditText LogEmail,username;
    private TextInputEditText LogPassword;
    private TextView ForgetPass;
    private  TextView Register;
    private Button btnLogin;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    public static String us_name;

    private CheckBox rememberme;
    private DatabaseReference refDash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        //progressDialouge
        progressDialog=new ProgressDialog(this);

        //sharedPrefManager=new SharedPrefManager(getApplicationContext());

        //LogEmail=findViewById(R.id.loginemailInput);
        username=findViewById(R.id.loginemailInput);
        us_name=username.getText().toString();
        LogPassword=findViewById(R.id.loginpasswordInput);
        btnLogin=findViewById(R.id.loginbutton);
        ForgetPass=findViewById(R.id.forgetpassword);
        Register=findViewById(R.id.Register);

//        rememberme=findViewById(R.id.rememberme);
//        Changepass=findViewById(R.id.changepassword);

        mAuth=FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(view -> {

            LoginUserCheck();

//            SharedPreferences sharedPreferences =getSharedPreferences(loginpage.PREFS_NAME,0);
//            SharedPreferences.Editor editor=sharedPreferences.edit();
//            editor.putBoolean("hasLoggedIn",true);
//            editor.commit();
//
//            startActivity(new Intent(loginpage.this,dashboard.class));
//            finish();

        });

        ForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginpage.this,forgetpassword.class));
            }
        });


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginpage.this,RegisterPage.class));
            }
        });
//        Changepass.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(loginpage.this,changepassword.class));
//            }
//        });
//        SharedPreferences preferences = getSharedPreferences("checked", MODE_PRIVATE);
//        String checkbox =preferences.getString("remember","");
//        if(checkbox.equals("true")){
//            Intent intent = new Intent(loginpage.this,dashboard.class);
//            startActivity(intent);
//        }else if(checkbox.equals("false")){
//            Toast.makeText(this,"Please Sign In",Toast.LENGTH_SHORT).show();
//        }

//        rememberme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                if(compoundButton.isChecked()){
//                    SharedPreferences preferences =getSharedPreferences("checkbox",MODE_PRIVATE);
//                    SharedPreferences.Editor editor =preferences.edit();
//                    editor.putString("remember","true");
//                    editor.apply();
//                    Toast.makeText(loginpage.this,"Saved",Toast.LENGTH_SHORT).show();
//                }
//                else if(!compoundButton.isChecked()){
//                    SharedPreferences preferences =getSharedPreferences("checkbox",MODE_PRIVATE);
//                    SharedPreferences.Editor editor =preferences.edit();
//                    editor.putString("remember","false");
//                    editor.apply();
//                    Toast.makeText(loginpage.this,"Not Saved",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
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
                uname.endsWith("@yahoo.com") || uname.endsWith("@YAHOO.COM") ||
                uname.endsWith("@sonatech.ac.in") || uname.endsWith("@SONATECH.AC.IN")){
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
//        if(email.equals(getResources().getString(R.string.username)) && password.equals(getResources().getString(R.string.password))){
//
//        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(loginpage.this, "User Logged in successfully", Toast.LENGTH_SHORT).show();
                    refDash=FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getUid());
                    refDash.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String pos=snapshot.child("position").getValue(String.class);
                            if(pos.equals("admin")){
                                startActivity(new Intent(loginpage.this,AdminDashboard.class));
                            }
                            else{
                                startActivity(new Intent(loginpage.this, dashboard.class));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(loginpage.this, "Login Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}