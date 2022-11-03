package com.example.map_my_sona.complaints;

import static android.R.layout.simple_spinner_dropdown_item;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.map_my_sona.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class ComplaintPage_user extends AppCompatActivity {

    private TextView sn, make, model, procurement, powerRating, wexpiry, wperiod, ins_by, ins_date,dep_of_pro,config;
    private TextView sn_snNum,sn_make,sn_model,sn_proc,sn_powerRat,sn_wperiod,sn_wexpiry,sn_ins_by,sn_ins_date,sn_dep_of_pro,sn_config,sn_loc;
    private TableRow snRow,makeRow,modelRow,procRow,powerRatRow,wperiodrow,wexpiryrow,ins_byRow,ins_dateRow,dep_of_proRow,configRow,locationRow;
    private TextView location;
    private TextView cost1,cost2,config1,config2;
    private String location_str;
    private EditText complainted_by_name, complainted_by_mob ;
    private EditText other_com;
    //private Spinner complainted_by_dep;
    private Spinner complaint_qrcode;
    private Button complaint_subBtn;
    private Button verify;
    private int snNumber=1;
    private DatabaseReference refDash;

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
    DatabaseReference refDash1;

    TextInputLayout complaint_content;
    AutoCompleteTextView complaint_content_text;
    private float rating;
    private String  rating_str;
    private boolean VerifyBool;
    private int random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_page_user);


        uref= FirebaseAuth.getInstance().getUid();

        Senderemail="mapmysona@gmail.com";
        Sendpass="mms@2022";

        final ProgressBar progressBar =  findViewById(R.id.progressbarforotp);

        refDash1 = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getUid());

        //StringHost="smtp.outlook.com";

        //
//        complaint_content=findViewById(R.id.complaint_content);
//        complaint_content_text=findViewById(R.id.complaint_Qrcode);

        sn = (TextView) findViewById(R.id.sn_unit1);
        make = (TextView) findViewById(R.id.make_unit1);
        model = (TextView) findViewById(R.id.model_unit1);
        powerRating = (TextView) findViewById(R.id.powerRating_unit1);
        procurement = (TextView) findViewById(R.id.procurement_unit1);
        wperiod = (TextView) findViewById(R.id.warranty_unit1);
        wexpiry = (TextView) findViewById(R.id.warranty_exp_unit1);
        ins_by = (TextView) findViewById(R.id.ins_by_unit1);
        ins_date = (TextView) findViewById(R.id.ins_date_unit1);
        //mob = (TextView) findViewById(R.id.mob_unit);
        dep_of_pro=(TextView)findViewById(R.id.dep_of_pro_unit1);
        location=(TextView)findViewById(R.id.scanned_location1);

        snRow=(TableRow) findViewById(R.id.serialNumRow1);
        makeRow=(TableRow) findViewById(R.id.makeRow1);
        modelRow=(TableRow) findViewById(R.id.modelRow1);
        powerRatRow=(TableRow) findViewById(R.id.powerRat_row1);
        procRow=(TableRow) findViewById(R.id.procRow1);
        wperiodrow=(TableRow) findViewById(R.id.warrantyPeriodRow1);
        wexpiryrow=(TableRow) findViewById(R.id.wexpiryRow1);
        ins_byRow=(TableRow) findViewById(R.id.ins_by_Row1);
        ins_dateRow=(TableRow) findViewById(R.id.ins_dateRow1);
        dep_of_proRow=(TableRow) findViewById(R.id.dep_of_pro_row1);
        locationRow=(TableRow) findViewById(R.id.LocRow1);
        configRow=(TableRow) findViewById(R.id.configrow1);
        verify=findViewById(R.id.verifyButton);

        sn_snNum=(TextView)findViewById(R.id.sn_snNum1);
        sn_make=(TextView)findViewById(R.id.sn_make1);
        sn_model=(TextView)findViewById(R.id.sn_model1);
        sn_proc=(TextView)findViewById(R.id.sn_procDate1);
        sn_powerRat=(TextView)findViewById(R.id.sn_powerRat1);
        sn_wperiod=(TextView)findViewById(R.id.sn_warperiod1);
        sn_wexpiry=(TextView)findViewById(R.id.sn_wexpiry1);
        sn_ins_by=(TextView)findViewById(R.id.sn_ins_by1);
        sn_ins_date=(TextView)findViewById(R.id.sn_insdate1);
        sn_dep_of_pro=(TextView)findViewById(R.id.sn_dep_of_pro1);
        sn_loc=(TextView)findViewById(R.id.sn_loc1);
        sn_config= (TextView)findViewById(R.id.sn_config1);

        config=(TextView)findViewById(R.id.Config1);

        complaint_subBtn = (Button) findViewById(R.id.button_complaint_submit1);

        complaint_qrcode=(Spinner) findViewById(R.id.complaint_Qrcode1);
        complainted_by_name = (EditText) findViewById(R.id.scan_qr_com_name1);
        complainted_by_mob = (EditText) findViewById(R.id.scan_qr_com_mob1);

        other_com=(EditText)findViewById(R.id.others_complaint_qr1);

        //complainted_by_dep = (Spinner) findViewById(R.id.scan_qr_com_dep);

//        vhigh =(CheckBox)findViewById(R.id.veryhighpriority);
//        high=(CheckBox)findViewById(R.id.highpriority);
//        low =(CheckBox)findViewById(R.id.lowpriority);



//        TextView scanText = (TextView) findViewById(R.id.textView);
        s = getIntent().getStringExtra("SCAN_RESULT");
        manual_name=getIntent().getStringExtra("MANUAL_NAME");
        manual_mob=getIntent().getStringExtra("MANUAL_MOB");

        String[] com_scan={"Complaint","Not Working","Broken","Leakage","Others"};
        complaint_qrcode.setAdapter(new ArrayAdapter<String>(this, simple_spinner_dropdown_item,com_scan));

        databaseReference = FirebaseDatabase.getInstance().getReference("Datas");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
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
                rating_str = String.valueOf(rating);
                FeedBack_str="None";

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

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                complainted_by_mob_str=complainted_by_mob.getText().toString();
                if(VerifyBool){
                    Toast.makeText(ComplaintPage_user.this, "Already verified", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (!complainted_by_mob.getText().toString().trim().isEmpty()){
                        if ((complainted_by_mob.getText().toString().trim()).length() == 10){
                            progressBar.setVisibility(View.VISIBLE);
                            verify.setVisibility(View.INVISIBLE);
                            sendMessage();
                            progressBar.setVisibility(View.GONE);
                            verify.setVisibility(View.VISIBLE);
                            Intent i=new Intent(ComplaintPage_user.this, verificationpage.class);
                            i.putExtra("otpcode",String.valueOf(random));
                            i.putExtra("scanresult",s);
                            i.putExtra("mobileNum",complainted_by_mob_str);
                            startActivity(i);

                        }else {
                            Toast.makeText(ComplaintPage_user.this,"Please enter correct number",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(ComplaintPage_user.this,"Enter Mobile number",Toast.LENGTH_SHORT).show();
                    }
                }
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

        complaint_subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(complaint_txt.equals("Others") && other_com.getText().toString().isEmpty()){
                    Toast.makeText(ComplaintPage_user.this,"Please specify your complaint",Toast.LENGTH_SHORT).show();
                    other_com.requestFocus();
                }
                else if (complaint_txt.equals("Complaint")) {
                    Toast.makeText(ComplaintPage_user.this, "Please select the Complaint", Toast.LENGTH_SHORT).show();
                    complaint_qrcode.requestFocus();
                }else if (complainted_by_name.getText().toString().isEmpty()) {
                    complainted_by_name.setError("Empty");
                    complainted_by_name.requestFocus();
                } else if (complainted_by_mob.getText().toString().isEmpty()) {
                    complainted_by_mob.setError("Empty");
                    complainted_by_mob.requestFocus();
                }
                else{
                    Toast.makeText(ComplaintPage_user.this, "Your mobile number is not verified", Toast.LENGTH_SHORT).show();
                }
//                else if (vhigh.getText().toString().isEmpty()) {
//                    vhigh.setError("Empty");
//                    vhigh.requestFocus();
//                }
            }

        });

    }
    private void sendMessage()  {

        int upper=100000;
        int lower=900000;
        int r = (int) (Math.random() * (upper - lower)) + lower;
        random=r;

        // Replace with your username
        String user = "Sonatech";

        // Replace with your API KEY (We have sent API KEY on activation email, also available on panel)
        String apikey = "Y7VVzSPtX3vfsq5AKYCG";

        // Replace with the destination mobile Number to which you want to send sms
        String mobile = "9677381857";

        // Replace if you have your own Sender ID, else donot change
        String senderid = "SONACT";

        String str="Your complaint has been closed and solved";

        // Replace with your Message content
        String message = "For request initiated through Sonasoft the one time password is :" +r+". Do not share it with anyone for security reason";

        // For Plain Text, use "txt" ; for Unicode symbols or regional Languages like hindi/tamil/kannada use "uni"
        String type="txt";

        //Prepare Url
        URLConnection myURLConnection=null;
        URL myURL=null;
        BufferedReader reader=null;

        //encoding message
        String encoded_message= URLEncoder.encode(message);

        //Send SMS API
        String mainUrl="https://smshorizon.co.in/api/sendsms.php?user=Sonatech&apikey=Y7VVzSPtX3vfsq5AKYCG&mobile=" +mobile+ "&senderid=SONACT&type=txt&tid=1507159884977405639&message="+message;

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
            while ((response = reader.readLine()) != null){
                //print response
                System.out.println(response);
            }

            //finally close connection
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}