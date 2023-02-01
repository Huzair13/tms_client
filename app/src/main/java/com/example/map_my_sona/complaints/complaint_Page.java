package com.example.map_my_sona.complaints;

import static android.R.layout.simple_spinner_dropdown_item;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.audiofx.DynamicsProcessing;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.INotificationSideChannel;
import android.util.Config;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.airbnb.lottie.animation.content.Content;
import com.example.map_my_sona.ExampleService;
import com.example.map_my_sona.R;
import com.example.map_my_sona.ScannerPage;
import com.example.map_my_sona.admin.AdminDashboard;
import com.example.map_my_sona.dashboard;
import com.example.map_my_sona.manualentry;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import papaya.in.sendmail.SendMail;

public class complaint_Page extends AppCompatActivity {

    private TextView sn, make, model, procurement, powerRating, wexpiry, wperiod, ins_by, ins_date,dep_of_pro,config;
    private TextView sn_snNum,sn_make,sn_model,sn_proc,sn_powerRat,sn_wperiod,sn_wexpiry,sn_ins_by,sn_ins_date,sn_dep_of_pro,sn_config,sn_loc;
    private TableRow snRow,makeRow,modelRow,procRow,powerRatRow,wperiodrow,wexpiryrow,ins_byRow,ins_dateRow,dep_of_proRow,configRow,locationRow;
    private TextView location;
    private TextView cost1,cost2,config1,config2;
    private String location_str;
    private TableLayout tblayout_com_page;
    private EditText  complainted_by_name, complainted_by_mob ;
    private EditText other_com;
    //private Spinner complainted_by_dep;
    private Spinner complaint_qrcode;
    private Button complaint_subBtn;
    private int snNumber=1;
    private DatabaseReference refDash;
    Float rating;
    String otpcode;
    String rating_str;
    String mobile,scanresult;
    CheckBox vhigh ,high ,low;
    private TextView uid_tv;

    private String Senderemail, ReceiverEmail,Sendpass , StringHost;

    String uref;

    private String  complainted_by_name_str, complainted_by_mob_str, sn_str, make_str, model_str,
            procurement_str, powerRating_str, wexpiry_str, wperiod_str, ins_by_str, ins_date_str,
            config_str,dep_of_pro_str;
    private String complaint_txt,others_com_str;
    String status = "Pending";
    DatabaseReference databaseReference;
    String s,manual_name,manual_mob;
    String FeedBack_str;
    DatabaseReference dbRef;

    DatabaseReference refemail;
    DatabaseReference refDash1,refDash2;

    TextInputLayout complaint_content;
    AutoCompleteTextView complaint_content_text;

    String uniqueIDGen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_page);

        uref= FirebaseAuth.getInstance().getUid();

        Senderemail="mapmysona@gmail.com";
        Sendpass="mms@2022";

        refDash1 = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getUid());
        refDash2 =FirebaseDatabase.getInstance().getReference("Datas");

        //StringHost="smtp.outlook.com";

        //
//        complaint_content=findViewById(R.id.complaint_content);
//        complaint_content_text=findViewById(R.id.complaint_Qrcode);

        sn = (TextView) findViewById(R.id.sn_unit);
        make = (TextView) findViewById(R.id.make_unit);
        model = (TextView) findViewById(R.id.model_unit);
        powerRating = (TextView) findViewById(R.id.powerRating_unit);
        procurement = (TextView) findViewById(R.id.procurement_unit);
        wperiod = (TextView) findViewById(R.id.warranty_unit);
        wexpiry = (TextView) findViewById(R.id.warranty_exp_unit);
        ins_by = (TextView) findViewById(R.id.ins_by_unit);
        ins_date = (TextView) findViewById(R.id.ins_date_unit);
        //mob = (TextView) findViewById(R.id.mob_unit);
        dep_of_pro=(TextView)findViewById(R.id.dep_of_pro_unit);
        location=(TextView)findViewById(R.id.scanned_location);
        uid_tv=(TextView)findViewById(R.id.com_page_uniqueID);
        tblayout_com_page=(TableLayout)findViewById(R.id.table_complaint);

        snRow=(TableRow) findViewById(R.id.serialNumRow);
        makeRow=(TableRow) findViewById(R.id.makeRow);
        modelRow=(TableRow) findViewById(R.id.modelRow);
        powerRatRow=(TableRow) findViewById(R.id.powerRat_row);
        procRow=(TableRow) findViewById(R.id.procRow);
        wperiodrow=(TableRow) findViewById(R.id.warrantyPeriodRow);
        wexpiryrow=(TableRow) findViewById(R.id.wexpiryRow);
        ins_byRow=(TableRow) findViewById(R.id.ins_by_Row);
        ins_dateRow=(TableRow) findViewById(R.id.ins_dateRow);
        dep_of_proRow=(TableRow) findViewById(R.id.dep_of_pro_row);
        locationRow=(TableRow) findViewById(R.id.LocRow);
        configRow=(TableRow) findViewById(R.id.configrow);

        sn_snNum=(TextView)findViewById(R.id.sn_snNum);
        sn_make=(TextView)findViewById(R.id.sn_make);
        sn_model=(TextView)findViewById(R.id.sn_model);
        sn_proc=(TextView)findViewById(R.id.sn_procDate);
        sn_powerRat=(TextView)findViewById(R.id.sn_powerRat);
        sn_wperiod=(TextView)findViewById(R.id.sn_warperiod);
        sn_wexpiry=(TextView)findViewById(R.id.sn_wexpiry);
        sn_ins_by=(TextView)findViewById(R.id.sn_ins_by);
        sn_ins_date=(TextView)findViewById(R.id.sn_insdate);
        sn_dep_of_pro=(TextView)findViewById(R.id.sn_dep_of_pro);
        sn_loc=(TextView)findViewById(R.id.sn_loc);
        sn_config= (TextView)findViewById(R.id.sn_config);

        config=(TextView)findViewById(R.id.Config);

        complaint_subBtn = (Button) findViewById(R.id.button_complaint_submit);

        complaint_qrcode=(Spinner) findViewById(R.id.complaint_Qrcode);
        complainted_by_name = (EditText) findViewById(R.id.scan_qr_com_name);
        complainted_by_mob = (EditText) findViewById(R.id.scan_qr_com_mob);

        other_com=(EditText)findViewById(R.id.others_complaint_qr);

        //complainted_by_dep = (Spinner) findViewById(R.id.scan_qr_com_dep);

//        vhigh =(CheckBox)findViewById(R.id.veryhighpriority);
//        high=(CheckBox)findViewById(R.id.highpriority);
//        low =(CheckBox)findViewById(R.id.lowpriority);

        LinearLayout priority = (LinearLayout) findViewById(R.id.prioritylayout);


//        TextView scanText = (TextView) findViewById(R.id.textView);
        s = getIntent().getStringExtra("SCAN_RESULT");
        manual_name=getIntent().getStringExtra("MANUAL_NAME");
        manual_mob=getIntent().getStringExtra("MANUAL_MOB");

        otpcode=getIntent().getStringExtra("otpcodev");

        refDash1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String pos = snapshot.child("position").getValue(String.class);
                if(snapshot.exists()){
                    if(pos.equals("user")){
                        complainted_by_mob_str=getIntent().getStringExtra("mobile");
                        s=getIntent().getStringExtra("SCAN_RESULT");
                        complainted_by_mob.setText(complainted_by_mob_str);
                        complainted_by_mob.setEnabled(false);
                        tblayout_com_page.setVisibility(View.GONE);
                        uniqueIDGen=otpcode+complainted_by_mob_str+((int) (Math.random() * (99 - 01)) + 01);
                        complainted_by_mob.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_verified_24, 0, 0, 0);
                    }
                    else {
                        uniqueIDGen=String.valueOf(((long) (Math.random() * (999999999 - 100000000)) + 100000000)+((int) (Math.random() * (9999999 - 1000000)) + 1000000));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //String[] dept_com_scan={"Department ","CSE","IT","ADS","ECE","EEE","MECH","MCT","CIVIL"};
        //complainted_by_dep.setAdapter(new ArrayAdapter<String>(this, simple_spinner_dropdown_item,dept_com_scan));

        databaseReference = FirebaseDatabase.getInstance().getReference("Datas");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(s)){
                    //mob_str = String.valueOf(snapshot.child(s).child("0").child("mob").getValue(Long.class));
                    sn_str = String.valueOf(snapshot.child(s).child("sn_no").getValue(Long.class));
                    make_str = snapshot.child(s).child("make").getValue(String.class);
                    model_str = snapshot.child(s).child("model").getValue(String.class);
                    procurement_str = snapshot.child(s).child("procurement").getValue(String.class);
                    powerRating_str = snapshot.child(s).child("power_rating").getValue(String.class);
                    wexpiry_str = snapshot.child(s).child("wexpiry").getValue(String.class);
                    wperiod_str = snapshot.child(s).child("wperiod").getValue(String.class);
                    ins_by_str = snapshot.child(s).child("ins_by").getValue(String.class);
                    ins_date_str = snapshot.child(s).child("ins_date").getValue(String.class);
                    dep_of_pro_str = snapshot.child(s).child("dep_of_pro").getValue(String.class);
                    location_str = snapshot.child(s).child("location").getValue(String.class);
                    config_str=snapshot.child(s).child("config").getValue(String.class);
                    rating = 0.0f;
                    rating_str = rating.toString();
                    FeedBack_str="None";

                    uid_tv.setText(s);
                    setComDropDown();

//                if(dep_of_pro_str.equals("Assets")){
//                    cost1.setVisibility(View.VISIBLE);
//                    cost2.setVisibility(View.VISIBLE);
//                    cost.setVisibility(View.VISIBLE);
//                    config1.setVisibility(View.VISIBLE);
//                    config2.setVisibility(View.VISIBLE);
//                    config.setVisibility(View.VISIBLE);
//                }
                    //SERIAL NUMBER
                    if(!sn_str.equals("NIL")){
                        sn.setText(sn_str);
                        sn_snNum.setText(String.valueOf(snNumber));
                        snNumber++;
                    }
                    else{
                        snRow.setVisibility(View.GONE);
                    }

                    //MAKE
                    if(!make_str.equals("NIL")){
                        make.setText(make_str);
                        sn_make.setText(String.valueOf(snNumber));
                        snNumber++;
                    }
                    else{
                        makeRow.setVisibility(View.GONE);
                    }

                    //MODEL
                    if(!model_str.equals("NIL")){
                        model.setText(model_str);
                        sn_model.setText(String.valueOf(snNumber));
                        snNumber++;
                    }
                    else{
                        modelRow.setVisibility(View.GONE);
                    }

                    //PROCUREMENT DATE
                    if(!procurement_str.equals("NIL")){
                        procurement.setText(procurement_str);
                        sn_proc.setText(String.valueOf(snNumber));
                        snNumber++;
                    }
                    else{
                        procRow.setVisibility(View.GONE);
                    }

                    //POWER RATING
                    if(!powerRating_str.equals("NIL")){
                        powerRating.setText(powerRating_str);
                        sn_powerRat.setText(String.valueOf(snNumber));
                        snNumber++;
                    }
                    else{
                        powerRatRow.setVisibility(View.GONE);
                    }

                    //WPERIOD
                    if(!wperiod_str.equals("NIL")){
                        wperiod.setText(wperiod_str);
                        sn_wperiod.setText(String.valueOf(snNumber));
                        snNumber++;
                    }
                    else{
                        wperiodrow.setVisibility(View.GONE);
                    }

                    //WEXPIRY
                    if(!wexpiry_str.equals("NIL")){
                        wexpiry.setText(wexpiry_str);
                        sn_wexpiry.setText(String.valueOf(snNumber));
                        snNumber++;
                    }
                    else{
                        wexpiryrow.setVisibility(View.GONE);
                    }

                    //INS_DATE
                    if(!ins_date_str.equals("NIL")){
                        ins_date.setText(ins_date_str);
                        sn_ins_date.setText(String.valueOf(snNumber));
                        snNumber++;
                    }
                    else{
                        ins_dateRow.setVisibility(View.GONE);
                    }

                    //INS_BY
                    if(!ins_by_str.equals("NIL")){
                        ins_by.setText(ins_by_str);
                        sn_ins_by.setText(String.valueOf(snNumber));
                        snNumber++;
                    }
                    else{
                        ins_byRow.setVisibility(View.GONE);
                    }

                    //DEP_OF_PRO
                    if(!dep_of_pro_str.equals("NIL")){
                        dep_of_pro.setText(dep_of_pro_str);
                        sn_dep_of_pro.setText(String.valueOf(snNumber));
                        snNumber++;

                    }
                    else{
                        dep_of_proRow.setVisibility(View.GONE);
                    }

                    //LOCATION
                    if(!location_str.equals("NIL")){
                        location.setText(location_str);
                        sn_loc.setText(String.valueOf(snNumber));
                        snNumber++;
                    }
                    else{
                        locationRow.setVisibility(View.GONE);
                    }

                    //DEP_OF_PRO
                    if(!config_str.equals("NIL")){
                        config.setText(config_str);
                        sn_config.setText(String.valueOf(snNumber));
                        snNumber++;
                    }
                    else{
                        configRow.setVisibility(View.GONE);
                    }


                    //mob.setText(mob_str);


//                if(!manual_name.equals("null")){
//                    complainted_by_name.setText(manual_name);
//                }
//                if(!manual_mob.equals("null")){
//                    complainted_by_mob.setText(manual_mob);
//                }

                }
                else{
                    Intent intent=new Intent(complaint_Page.this, ScannerPage.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    Toast.makeText(complaint_Page.this, "Invalid QR Code", Toast.LENGTH_SHORT).show();
                }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        complaint_qrcode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                complaint_txt=complaint_qrcode.getSelectedItem().toString();
                if(complaint_txt.equals("Others")){
                    other_com.setVisibility(View.VISIBLE);
                }
                else{
                    other_com.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        String[] complaint = {"Light Not working", "Network issue", "Fan not working", "Bathroom Problem", "Furniture defects"};
//        ArrayAdapter<String> complaintAdapter = new ArrayAdapter<>(complaint_Page.this, R.layout.dropdowncomplaintcontent, complaint);
//        complaint_content_text.setAdapter(complaintAdapter);


        complaint_subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(complaint_txt.equals("Others") && other_com.getText().toString().isEmpty()){
                    Toast.makeText(complaint_Page.this,"Please specify your complaint",Toast.LENGTH_SHORT).show();
                    other_com.requestFocus();
                }
                else if (complaint_txt.equals("Complaint")) {
                    Toast.makeText(complaint_Page.this, "Please select the Complaint", Toast.LENGTH_SHORT).show();
                    complaint_qrcode.requestFocus();
                }else if (complainted_by_name.getText().toString().isEmpty()) {
                    complainted_by_name.setError("Empty");
                    complainted_by_name.requestFocus();
                } else if (complainted_by_mob.getText().toString().isEmpty()) {
                    complainted_by_mob.setError("Empty");
                    complainted_by_mob.requestFocus();
                }
                else{
                    getReciverEmail();
                    submitComplaint();
                }
//                else if (vhigh.getText().toString().isEmpty()) {
//                    vhigh.setError("Empty");
//                    vhigh.requestFocus();
//                }
                }

        });
        //scanText.setText(s);

//        String input = complaint_txt;
//        Intent serviceIntent = new Intent(this, ExampleService.class);
//        serviceIntent.putExtra("inputExtra", input);
//        ContextCompat.startForegroundService(this, serviceIntent);
    }

    private void setComDropDown() {
        String[] com_scan_elec={"Complaint","Not Working","Broken","Low Voltage","Bursted","Others"};
        String[] com_scan_plumber={"Complaint","Broken","Leakage","Water not coming","Others"};
        String[] com_scan_carpenter={"Complaint","Partially Broken","Fully Broken","Others"};
        String[] com_scan_Network={"Complaint","Not Working","Slow Network","Hanging Problem","Others"};
        String[] com_scan_assets={"Complaint","Not Working","Broken","Others"};
        String[] com_scan_painting={"Complaint","Bleach","Little Ugly","Very Ugly","Others"};
        String[] com_scan_others={"Complaint","Not Working","Others"};

        switch(dep_of_pro_str){
            case "Electricity":
                complaint_qrcode.setAdapter(new ArrayAdapter<String>(this, simple_spinner_dropdown_item,com_scan_elec));
                break;
            case "Pluming" :
                complaint_qrcode.setAdapter(new ArrayAdapter<String>(this, simple_spinner_dropdown_item,com_scan_plumber));
                break;
            case "Painting":
                complaint_qrcode.setAdapter(new ArrayAdapter<String>(this, simple_spinner_dropdown_item,com_scan_painting));
                break;
            case "Carpenter":
                complaint_qrcode.setAdapter(new ArrayAdapter<String>(this, simple_spinner_dropdown_item,com_scan_carpenter));
                break;
            case "Network":
                complaint_qrcode.setAdapter(new ArrayAdapter<String>(this, simple_spinner_dropdown_item,com_scan_Network));
                break;
            case "Assets":
                complaint_qrcode.setAdapter(new ArrayAdapter<String>(this, simple_spinner_dropdown_item,com_scan_assets));
                break;
            case "Others":
                complaint_qrcode.setAdapter(new ArrayAdapter<String>(this, simple_spinner_dropdown_item,com_scan_others));
                break;
        }
    }

    private void sendemail() {
        SendMail mail=new SendMail(Senderemail,
                "ywfcjyswheezxmde",
                ReceiverEmail,//panneerselvamm@sonatech.ac.in
                "Complaint in "+dep_of_pro_str+" Department",
                "Complained by "+ complainted_by_name.getText().toString() +"\n" +
                        "COMPLAINT: "+ complaint_txt
        );
        mail.execute();
    }

    private void getReciverEmail() {
        refemail=FirebaseDatabase.getInstance().getReference("Emails");
        if(dep_of_pro_str.equals("Electricity")){
            refemail.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    ReceiverEmail=snapshot.child(dep_of_pro_str).child("email").getValue(String.class);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else if(dep_of_pro_str.equals("Plumber")) {
            refemail.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    ReceiverEmail = snapshot.child(dep_of_pro_str).child("email").getValue(String.class);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else if(dep_of_pro_str.equals("Network")){
            refemail.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    ReceiverEmail=snapshot.child(dep_of_pro_str).child("email").getValue(String.class);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else if(dep_of_pro_str.equals("Carpenter")){
            refemail.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    ReceiverEmail=snapshot.child(dep_of_pro_str).child("email").getValue(String.class);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else if(dep_of_pro_str.equals("Painting")){
            refemail.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    ReceiverEmail=snapshot.child(dep_of_pro_str).child("email").getValue(String.class);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else{
            refemail.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    ReceiverEmail=snapshot.child("other").child("email").getValue(String.class);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }
    }


    private void submitComplaint() {

        dbRef = FirebaseDatabase.getInstance().getReference().child("complaints").child(dep_of_pro_str);
        final String uniqueKey = dbRef.push().getKey();

        complainted_by_name_str = complainted_by_name.getText().toString();
        complainted_by_mob_str = complainted_by_mob.getText().toString();
        if(complaint_txt.equals("Others")){
            complaint_txt=other_com.getText().toString();
        }

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
        String date = currentDate.format(calForDate.getTime());

        Calendar calForTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
        String time = currentTime.format(calForTime.getTime());


        Complaint_details complaint_details = new Complaint_details(complainted_by_name_str, complainted_by_mob_str,
                complaint_txt, sn_str,
                make_str, model_str, procurement_str,
                powerRating_str, wperiod_str, wexpiry_str, ins_by_str, ins_date_str, date, time, uniqueIDGen, s,
                status,dep_of_pro_str,location_str,rating_str,FeedBack_str,config_str,"NO","NO","NIL","NIL");

        refDash= FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getUid());


        dbRef.child(uniqueIDGen).setValue(complaint_details).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                sendemail();
                Toast.makeText(complaint_Page.this, "Complaint Registered Successfully", Toast.LENGTH_SHORT).show();
                sendMessage();
                refDash.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String pos=snapshot.child("position").getValue(String.class);
                        if(pos.equals("admin") || pos.equals("superadmin")){
                            Intent intent= new Intent(complaint_Page.this, dashboard.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                        else{
                            Intent intent=new Intent(complaint_Page.this, ScannerPage.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(complaint_Page.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendMessage()  {
        // Replace with your username
        String user = "Sonatech";

        // Replace with your API KEY (We have sent API KEY on activation email, also available on panel)
        String apikey = "Y7VVzSPtX3vfsq5AKYCG";

        // Replace with the destination mobile Number to which you want to send sms
        String mobile = complainted_by_mob_str;

        // Replace if you have your own Sender ID, else donot change
        String senderid = "SONASR";

        String str="Your complaint has been closed and solved";

        String feed="https://trackmysonafeebackweb";
        String feed1=".web.app/network";

        String var="https://www.sonatech";
        String var1=".ac.in/";

        // Replace with your Message content
        String message = "Thank you. Your complaint has been registered successfully. For more details click here" +var+var1+ "- SONA STAR.";

                // For Plain Text, use "txt" ; for Unicode symbols or regional Languages like hindi/tamil/kannada use "uni"
        String type="txt";

        //Prepare Url
        URLConnection myURLConnection=null;
        URL myURL=null;
        BufferedReader reader=null;

        //encoding message
        String encoded_message= URLEncoder.encode(message);

        //Send SMS API
        String mainUrl="https://smshorizon.co.in/api/sendsms.php?user=Sonatech&apikey=Y7VVzSPtX3vfsq5AKYCG&mobile=" +mobile+ "&senderid=SONASR&type=txt&tid=1507167447559320708&message="+message;

        StrictMode.ThreadPolicy gfgPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(gfgPolicy);

        try
        {
            //prepare connection
            myURL = new URL(mainUrl);
            myURLConnection = myURL.openConnection();
            myURLConnection.connect();
            reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
            //reading response
            String response;
            while ((response = reader.readLine()) != null)
                //print response
                System.out.println(response);

            //finally close connection
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
//
//    public void startService(View v) {
//        String input = complaint_qrcode.getText().toString();
//        String input = "Hello Vicky";
//        Intent serviceIntent = new Intent(this, ExampleService.class);
//         serviceIntent.putExtra("inputExtra", input);
//
//        ContextCompat.startForegroundService(this, serviceIntent);
//    }
}