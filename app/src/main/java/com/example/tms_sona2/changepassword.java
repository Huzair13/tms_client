package com.example.tms_sona2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class changepassword extends AppCompatActivity {

    private TextInputEditText cpusername,cpoldpass,cpnewpass;
    private MaterialButton changepasswbtn;
    FirebaseAuth  fAuth;
    String userId;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);

//
//        userId = fAuth.getCurrentUser().getUid();
//        user =  fAuth.getCurrentUser();


        cpusername=findViewById(R.id.cpusername);
        cpoldpass=findViewById(R.id.cpoldpassword);
        cpnewpass=findViewById(R.id.cpnewpassword);

        changepasswbtn=findViewById(R.id.changepassbtn);


        changepasswbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final TextInputEditText cpusername = new TextInputEditText(v.getContext());
                final AlertDialog.Builder passwordResetDialog =  new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password");
                passwordResetDialog.setMessage("Enter New Password > 6 Characters long. ");
                passwordResetDialog.setView(cpusername);

                passwordResetDialog.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String newPassword = cpusername.getText().toString();
                        user.updatePassword(newPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(changepassword.this,"Password reset successfully",Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(changepassword.this,"Password reset failed",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                passwordResetDialog.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                });
                passwordResetDialog.create().show();

            }
        });



    }
}