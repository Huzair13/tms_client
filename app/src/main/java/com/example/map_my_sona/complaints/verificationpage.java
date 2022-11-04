package com.example.map_my_sona.complaints;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.map_my_sona.R;
import com.google.firebase.auth.PhoneAuthCredential;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class verificationpage extends AppCompatActivity {

    private EditText inputcode1, inputcode2, inputcode3, inputcode4, inputcode5, inputcode6;
    private String verificationid, s, str, mob;
    private boolean verifybool;
    PhoneAuthCredential phoneAuthCredential;
    TextView resend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificationpage);

        TextView textView = findViewById(R.id.textmobileshownumber);
        str = getIntent().getStringExtra("otpcode");
        mob = getIntent().getStringExtra("mobileNum");
        textView.setText(String.format(
                "+91-%s", mob
        ));

        resend = findViewById(R.id.textresendotp);
        inputcode1 = findViewById(R.id.inputotp1);
        inputcode2 = findViewById(R.id.inputotp2);
        inputcode3 = findViewById(R.id.inputotp3);
        inputcode4 = findViewById(R.id.inputotp4);
        inputcode5 = findViewById(R.id.inputotp5);
        inputcode6 = findViewById(R.id.inputotp6);

        //final ProgressBar progressBar = findViewById(R.id.progressbar_verify_otp);

        final Button buttonverify = findViewById(R.id.buttongetotp);

//        verificationid = getIntent().getStringExtra("verfication");
        verifybool = Boolean.parseBoolean(getIntent().getStringExtra("boolean123"));
        s = getIntent().getStringExtra("scanresult");

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

        buttonverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!inputcode1.getText().toString().trim().isEmpty()
                        && !inputcode2.getText().toString().trim().isEmpty()
                        && !inputcode3.getText().toString().trim().isEmpty()
                        && !inputcode4.getText().toString().trim().isEmpty()
                        && !inputcode5.getText().toString().trim().isEmpty()
                        && !inputcode6.getText().toString().trim().isEmpty()) {
                    String code = inputcode1.getText().toString() +
                            inputcode2.getText().toString() +
                            inputcode3.getText().toString() +
                            inputcode4.getText().toString() +
                            inputcode5.getText().toString() +
                            inputcode6.getText().toString();

                    if (str.equals(code)) {
                        Intent intent = new Intent(getApplicationContext(), complaint_Page.class);
                        verifybool = true;
                        //intent.putExtra("boolean123",verifybool);
                        intent.putExtra("otpcodev",str);
                        intent.putExtra("mobile", mob);
                        intent.putExtra("SCAN_RESULT", s);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else {
                        Toast.makeText(verificationpage.this, "Enter the Correct otp", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(verificationpage.this, "Please enter the otp", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void sendMessage() {

        int upper = 100000;
        int lower = 900000;
        int r = (int) (Math.random() * (upper - lower)) + lower;
        str = String.valueOf(r);

        // Replace with your username
        String user = "Sonatech";

        // Replace with your API KEY (We have sent API KEY on activation email, also available on panel)
        String apikey = "Y7VVzSPtX3vfsq5AKYCG";

        // Replace with the destination mobile Number to which you want to send sms
        String mobile = mob;

        // Replace if you have your own Sender ID, else donot change
        String senderid = "SONACT";

        String str = "Your complaint has been closed and solved";

        // Replace with your Message content
        String message = "For request initiated through Sonasoft the one time password is :" + r + ". Do not share it with anyone for security reason";

        // For Plain Text, use "txt" ; for Unicode symbols or regional Languages like hindi/tamil/kannada use "uni"
        String type = "txt";

        //Prepare Url
        URLConnection myURLConnection = null;
        URL myURL = null;
        BufferedReader reader = null;

        //encoding message
        String encoded_message = URLEncoder.encode(message);

        //Send SMS API
        String mainUrl = "https://smshorizon.co.in/api/sendsms.php?user=Sonatech&apikey=Y7VVzSPtX3vfsq5AKYCG&mobile=" + mobile + "&senderid=SONACT&type=txt&tid=1507159884977405639&message=" + message;

        StrictMode.ThreadPolicy gfgPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(gfgPolicy);

        try {
            //prepare connection
            myURL = new URL(mainUrl);
            myURLConnection = myURL.openConnection();
            myURLConnection.connect();
            reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
            //reading response
            String response;
            while ((response = reader.readLine()) != null) {
                //print response
                System.out.println(response);
            }

            //finally close connection
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}