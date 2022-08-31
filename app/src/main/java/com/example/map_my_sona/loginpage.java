package com.example.map_my_sona;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.map_my_sona.admin.AdminDashboard;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
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

    private ImageView googlelogin;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    private CheckBox rememberme;
    private DatabaseReference refDash;

//    GoogleSignInClient googleSignInClient;
//    FirebaseAuth firebaseAuth;

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

        googlelogin=findViewById(R.id.gllogin);



//        GoogleSignInOptions googleSignInOptions=new GoogleSignInOptions.Builder(
//                GoogleSignInOptions.DEFAULT_SIGN_IN
//        ).requestIdToken("438431947620-ecpi41uk3dhhf4mv8g8q993k3vs49ltm.apps.googleusercontent.com")
//                .requestEmail()
//                .build();
//
//        // Initialize sign in client
//        googleSignInClient= GoogleSignIn.getClient(loginpage.this
//                ,googleSignInOptions);
//
//        googlelogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Initialize sign in intent
//                Intent intent=googleSignInClient.getSignInIntent();
//                // Start activity for result
//                startActivityForResult(intent,100);
//            }
//        });
//
//        // Initialize firebase auth
//        firebaseAuth=FirebaseAuth.getInstance();
//        // Initialize firebase user
//        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
//        // Check condition
//        if(firebaseUser!=null)
//        {
//            // When user already sign in
//            // redirect to profile activity
//            startActivity(new Intent(loginpage.this,AdminDashboard.class)
//                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
//        }


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


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginpage.this,RegisterPage.class));
            }
        });
    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        super.onActivityResult(requestCode, resultCode, data);
//        // Check condition
//        if(requestCode==100)
//        {
//            // When request code is equal to 100
//            // Initialize task
//            Task<GoogleSignInAccount> signInAccountTask=GoogleSignIn
//                    .getSignedInAccountFromIntent(data);
//
//            // check condition
//            if(signInAccountTask.isSuccessful())
//            {
//                // When google sign in successful
//                // Initialize string
//                String s="Google sign in successful";
//                // Display Toast
//                displayToast(s);
//                // Initialize sign in account
//                try {
//                    // Initialize sign in account
//                    GoogleSignInAccount googleSignInAccount=signInAccountTask
//                            .getResult(ApiException.class);
//                    // Check condition
//                    if(googleSignInAccount!=null)
//                    {
//                        // When sign in account is not equal to null
//                        // Initialize auth credential
//                        AuthCredential authCredential= GoogleAuthProvider
//                                .getCredential(googleSignInAccount.getIdToken()
//                                        ,null);
//                        // Check credential
//                        firebaseAuth.signInWithCredential(authCredential)
//                                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<AuthResult> task) {
//                                        // Check condition
//                                        if(task.isSuccessful())
//                                        {
//                                            // When task is successful
//                                            // Redirect to profile activity
//                                            startActivity(new Intent(loginpage.this
//                                                    ,AdminDashboard.class)
//                                                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
//                                            // Display Toast
//                                            displayToast("Firebase authentication successful");
//                                        }
//                                        else
//                                        {
//                                            // When task is unsuccessful
//                                            // Display Toast
//                                            displayToast("Authentication Failed :"+task.getException()
//                                                    .getMessage());
//                                        }
//                                    }
//                                });
//
//                    }
//                }
//                catch (ApiException e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    private void displayToast(String s) {
//        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
//    }

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
                    try {
                        if ((progressDialog != null) && progressDialog.isShowing()) {
                           progressDialog.dismiss();
                        }
                    } catch (final IllegalArgumentException e) {
                        // Handle or log or ignore
                    } catch (final Exception e) {
                        // Handle or log or ignore
                    } finally {
                        progressDialog = null;
                    }
                    Toast.makeText(loginpage.this, "User Logged in successfully", Toast.LENGTH_SHORT).show();
                    refDash=FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getUid());
                    refDash.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String pos=snapshot.child("position").getValue(String.class);
                            if(pos.equals("admin")){
                                startActivity(new Intent(loginpage.this, dashboard.class));
                            }
                            else{
                                startActivity(new Intent(loginpage.this, AdminDashboard.class));
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