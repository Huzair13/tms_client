package com.example.map_my_sona.complaints;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.map_my_sona.ExcelExport;
import com.example.map_my_sona.R;
import com.example.map_my_sona.admin.AdminDashboard;
import com.example.map_my_sona.complaints.HistoryDetails.complaint_HistoryDetails_assets;
import com.example.map_my_sona.complaints.HistoryDetails.complaint_HistoryDetails_others;
import com.example.map_my_sona.complaints.HistoryDetails.Complaints_HistoryDetails_Carpenter;
import com.example.map_my_sona.complaints.HistoryDetails.Complaints_HistoryDetails_Networks;
import com.example.map_my_sona.complaints.HistoryDetails.Complaints_HistoryDetails_Electricity;
import com.example.map_my_sona.complaints.HistoryDetails.Complaints_HistoryDetails_Painting;
import com.example.map_my_sona.complaints.HistoryDetails.Complaints_HistoryDetails_Plumber;
import com.example.map_my_sona.dashboard;
import com.example.map_my_sona.loginpage;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class Dep_wise_history extends AppCompatActivity {

//    private Button networks,electricity,carpenter,plumber,painting;

    private MaterialCardView networks;
    private  MaterialCardView electricity;
    private MaterialCardView carpenter;
    private  MaterialCardView plumber;
    private MaterialCardView painting;
    private MaterialCardView others;
    private MaterialCardView assets;
    MaterialToolbar toolbar;

    private static DatabaseReference mDatabase;
    private ProgressDialog progressDialog;

    private Button export;
    private DatabaseReference refDash;
    AlertDialog.Builder builder11;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dep_wise_history);

        //progressDialouge
        progressDialog=new ProgressDialog(this);

        networks= findViewById(R.id.button_111);
        electricity= findViewById(R.id.button2_112);
        carpenter=findViewById(R.id.button_131);
        plumber=findViewById(R.id.plumber_history);
        painting=findViewById(R.id.painting_history);
        others=findViewById(R.id.others_history);
        assets=findViewById(R.id.assets_history);

        export=findViewById(R.id.export_history);

       export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Exporting please wait ......");
                progressDialog.setTitle("Exporting");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE, 101);
                askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, 101);
                try {
                    export1(progressDialog);

                    Toast.makeText(Dep_wise_history.this, "Exported Successfully", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        builder11=new AlertDialog.Builder(this);
        mAuth=FirebaseAuth.getInstance();

        refDash= FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getUid());

        networks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dep_wise_history.this, Complaints_HistoryDetails_Networks.class));

            }
        });
        assets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dep_wise_history.this, complaint_HistoryDetails_assets.class));
            }
        });
        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dep_wise_history.this, complaint_HistoryDetails_others.class));
            }
        });
        electricity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dep_wise_history.this, Complaints_HistoryDetails_Electricity.class));

            }
        });

        carpenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dep_wise_history.this, Complaints_HistoryDetails_Carpenter.class));
            }
        });

        plumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dep_wise_history.this, Complaints_HistoryDetails_Plumber.class));
            }
        });

        painting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dep_wise_history.this, Complaints_HistoryDetails_Painting.class));
            }
        });
        toolbar= findViewById(R.id.topAppBar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refDash.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String pos=snapshot.child("position").getValue(String.class);
                        if(pos.equals("admin")){
                            startActivity(new Intent(Dep_wise_history.this, dashboard.class));
                        }
                        else{
                            builder11.setTitle("Alert")
                                    .setMessage("Are you sure to Log out ?")
                                    .setCancelable(true)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            mAuth.signOut();
                                            Intent intent=new Intent(Dep_wise_history.this, loginpage.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(intent);
                                            finish();
                                        }
                                    })
                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    })
                                    .show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


    }
    public static void export1(ProgressDialog progressDialog) throws IOException {


        mDatabase= FirebaseDatabase.getInstance().getReference().child("complaints");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                java.io.File file1 = new java.io.File(Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                        + "/Complaints1.xls");

                HSSFWorkbook hwb = new HSSFWorkbook();
                HSSFSheet sheet = hwb.createSheet("Electricity");
                HSSFSheet sheet2 = hwb.createSheet("Carpenter");
                HSSFSheet sheet3 = hwb.createSheet("Pluming");
                HSSFSheet sheet4 = hwb.createSheet("Painting");
                HSSFSheet sheet5 = hwb.createSheet("Others");
                HSSFSheet sheet6 = hwb.createSheet("Network");
                HSSFSheet sheet7 = hwb.createSheet("Assets");


                String arr[]={"UniqueID","Department","Complainted_By","Complainted_From","Complaint",
                        "Complainted_On","Complaint_Id","Status","Escalated 1","Escalated 2"};

                int c= (int) snapshot.getChildrenCount();

                for(int x=0;x<1;x++){
                    HSSFRow row = sheet.createRow(x);
                    HSSFRow row2 = sheet2.createRow(x);
                    HSSFRow row3 = sheet3.createRow(x);
                    HSSFRow row4 = sheet4.createRow(x);
                    HSSFRow row5 = sheet5.createRow(x);
                    HSSFRow row6 = sheet6.createRow(x);
                    HSSFRow row7 = sheet7.createRow(x);

                    for(int i = 0; i< 10; i++)
                    {
                        HSSFCell cell = row.createCell(i);
                        HSSFCell cell2 = row2.createCell(i);
                        HSSFCell cell3 = row3.createCell(i);
                        HSSFCell cell4 = row4.createCell(i);
                        HSSFCell cell5 = row5.createCell(i);
                        HSSFCell cell6 = row6.createCell(i);
                        HSSFCell cell7 = row7.createCell(i);

                        String data = arr[i];

                        cell.setCellValue(data);
                        cell2.setCellValue(data);
                        cell3.setCellValue(data);
                        cell4.setCellValue(data);
                        cell5.setCellValue(data);
                        cell6.setCellValue(data);
                        cell7.setCellValue(data);

                    }

                }

                for(DataSnapshot ds: snapshot.getChildren()){
                    String dep=ds.getKey();
                    System.out.println(dep);
                    int r=1;
                    if(dep.equals("Electricity")){
                        for(DataSnapshot ds1: ds.getChildren()){
                            HSSFRow row = sheet.createRow(r);
                            Map<String,Object> map=(Map<String, Object>) ds1.getValue();
                            Object com_by_mob=map.get("com_by_mob");
                            Object com_by_name=map.get("com_by_name");
                            Object com_txt=map.get("com_txt");
                            Object date=map.get("date");
                            Object dep_of_pro=map.get("dep_of_pro");
                            Object escalated1=map.get("escalated1");
                            Object escalated2=map.get("escalated2");
                            Object key=map.get("key");
                            Object uniqueID=map.get("uniqueId");
                            Object status=map.get("status");

                            String com_by_mob_str=String.valueOf(com_by_mob);
                            String com_by_name_str=String.valueOf(com_by_name);
                            String com_txt_str=String.valueOf(com_txt);
                            String date_str=String.valueOf(date);
                            String dep_of_pro_str=String.valueOf(dep_of_pro);
                            String escalated1_str=String.valueOf(escalated1);
                            String escalated2_str=String.valueOf(escalated2);
                            String key_str=String.valueOf(key);
                            String uniqueId_str=String.valueOf(uniqueID);
                            String status_str=String.valueOf(status);


                            String arr1[]={uniqueId_str,dep_of_pro_str,com_by_name_str,com_by_mob_str,com_txt_str,date_str,key_str,status_str,escalated1_str,escalated2_str};

                            for(int i = 0; i< 10; i++)
                            {
                                HSSFCell cell = row.createCell(i);
                                String data = arr1[i];
                                cell.setCellValue(data);
                                //System.out.println(arr1[i]);

                            }
                            r+=1;
                        }
                    }
                    else if(dep.equals("Carpenter")){
                        for(DataSnapshot ds1: ds.getChildren()) {
                            HSSFRow row = sheet2.createRow(r);
                            Map<String,Object> map=(Map<String, Object>) ds1.getValue();
                            Object com_by_mob=map.get("com_by_mob");
                            Object com_by_name=map.get("com_by_name");
                            Object com_txt=map.get("com_txt");
                            Object date=map.get("date");
                            Object dep_of_pro=map.get("dep_of_pro");
                            Object escalated1=map.get("escalated1");
                            Object escalated2=map.get("escalated2");
                            Object key=map.get("key");
                            Object uniqueID=map.get("uniqueId");
                            Object status=map.get("status");

                            String com_by_mob_str=String.valueOf(com_by_mob);
                            String com_by_name_str=String.valueOf(com_by_name);
                            String com_txt_str=String.valueOf(com_txt);
                            String date_str=String.valueOf(date);
                            String dep_of_pro_str=String.valueOf(dep_of_pro);
                            String escalated1_str=String.valueOf(escalated1);
                            String escalated2_str=String.valueOf(escalated2);
                            String key_str=String.valueOf(key);
                            String uniqueId_str=String.valueOf(uniqueID);
                            String status_str=String.valueOf(status);


                            String arr1[]={uniqueId_str,dep_of_pro_str,com_by_name_str,com_by_mob_str,com_txt_str,date_str,key_str,status_str,escalated1_str,escalated2_str};

                            for(int i = 0; i< 10; i++)
                            {
                                HSSFCell cell = row.createCell(i);
                                String data = arr1[i];
                                cell.setCellValue(data);
                                System.out.println(arr1[i]);

                            }
                            r+=1;
                        }
                    }
                    else if(dep.equals("Pluming")){
                        for(DataSnapshot ds1: ds.getChildren()) {
                            HSSFRow row = sheet3.createRow(r);

                            Map<String,Object> map=(Map<String, Object>) ds1.getValue();
                            Object com_by_mob=map.get("com_by_mob");
                            Object com_by_name=map.get("com_by_name");
                            Object com_txt=map.get("com_txt");
                            Object date=map.get("date");
                            Object dep_of_pro=map.get("dep_of_pro");
                            Object escalated1=map.get("escalated1");
                            Object escalated2=map.get("escalated2");
                            Object key=map.get("key");
                            Object uniqueID=map.get("uniqueId");
                            Object status=map.get("status");

                            String com_by_mob_str=String.valueOf(com_by_mob);
                            String com_by_name_str=String.valueOf(com_by_name);
                            String com_txt_str=String.valueOf(com_txt);
                            String date_str=String.valueOf(date);
                            String dep_of_pro_str=String.valueOf(dep_of_pro);
                            String escalated1_str=String.valueOf(escalated1);
                            String escalated2_str=String.valueOf(escalated2);
                            String key_str=String.valueOf(key);
                            String uniqueId_str=String.valueOf(uniqueID);
                            String status_str=String.valueOf(status);


                            String arr1[]={uniqueId_str,dep_of_pro_str,com_by_name_str,com_by_mob_str,com_txt_str,date_str,key_str,status_str,escalated1_str,escalated2_str};

                            for(int i = 0; i< 10; i++)
                            {
                                HSSFCell cell = row.createCell(i);
                                String data = arr1[i];
                                cell.setCellValue(data);
                                System.out.println(arr1[i]);

                            }
                            r+=1;
                        }
                    }
                    else if(dep.equals("Assets")){
                        for(DataSnapshot ds1: ds.getChildren()) {
                            HSSFRow row = sheet7.createRow(r);

                            Map<String,Object> map=(Map<String, Object>) ds1.getValue();
                            Object com_by_mob=map.get("com_by_mob");
                            Object com_by_name=map.get("com_by_name");
                            Object com_txt=map.get("com_txt");
                            Object date=map.get("date");
                            Object dep_of_pro=map.get("dep_of_pro");
                            Object escalated1=map.get("escalated1");
                            Object escalated2=map.get("escalated2");
                            Object key=map.get("key");
                            Object uniqueID=map.get("uniqueId");
                            Object status=map.get("status");

                            String com_by_mob_str=String.valueOf(com_by_mob);
                            String com_by_name_str=String.valueOf(com_by_name);
                            String com_txt_str=String.valueOf(com_txt);
                            String date_str=String.valueOf(date);
                            String dep_of_pro_str=String.valueOf(dep_of_pro);
                            String escalated1_str=String.valueOf(escalated1);
                            String escalated2_str=String.valueOf(escalated2);
                            String key_str=String.valueOf(key);
                            String uniqueId_str=String.valueOf(uniqueID);
                            String status_str=String.valueOf(status);


                            String arr1[]={uniqueId_str,dep_of_pro_str,com_by_name_str,com_by_mob_str,com_txt_str,date_str,key_str,status_str,escalated1_str,escalated2_str};

                            for(int i = 0; i< 10; i++)
                            {
                                HSSFCell cell = row.createCell(i);
                                String data = arr1[i];
                                cell.setCellValue(data);
                                System.out.println(arr1[i]);

                            }
                            r+=1;
                        }
                    }
                    else if(dep.equals("Painting")){
                        for(DataSnapshot ds1: ds.getChildren()) {
                            HSSFRow row = sheet4.createRow(r);

                            Map<String,Object> map=(Map<String, Object>) ds1.getValue();
                            Object com_by_mob=map.get("com_by_mob");
                            Object com_by_name=map.get("com_by_name");
                            Object com_txt=map.get("com_txt");
                            Object date=map.get("date");
                            Object dep_of_pro=map.get("dep_of_pro");
                            Object escalated1=map.get("escalated1");
                            Object escalated2=map.get("escalated2");
                            Object key=map.get("key");
                            Object uniqueID=map.get("uniqueId");
                            Object status=map.get("status");

                            String com_by_mob_str=String.valueOf(com_by_mob);
                            String com_by_name_str=String.valueOf(com_by_name);
                            String com_txt_str=String.valueOf(com_txt);
                            String date_str=String.valueOf(date);
                            String dep_of_pro_str=String.valueOf(dep_of_pro);
                            String escalated1_str=String.valueOf(escalated1);
                            String escalated2_str=String.valueOf(escalated2);
                            String key_str=String.valueOf(key);
                            String uniqueId_str=String.valueOf(uniqueID);
                            String status_str=String.valueOf(status);


                            String arr1[]={uniqueId_str,dep_of_pro_str,com_by_name_str,com_by_mob_str,com_txt_str,date_str,key_str,status_str,escalated1_str,escalated2_str};

                            for(int i = 0; i< 10; i++)
                            {
                                HSSFCell cell = row.createCell(i);
                                String data = arr1[i];
                                cell.setCellValue(data);
                                System.out.println(arr1[i]);

                            }
                            r+=1;
                        }
                    }
                    else if(dep.equals("Network")){
                        for(DataSnapshot ds1: ds.getChildren()) {
                            HSSFRow row = sheet6.createRow(r);

                            Map<String,Object> map=(Map<String, Object>) ds1.getValue();
                            Object com_by_mob=map.get("com_by_mob");
                            Object com_by_name=map.get("com_by_name");
                            Object com_txt=map.get("com_txt");
                            Object date=map.get("date");
                            Object dep_of_pro=map.get("dep_of_pro");
                            Object escalated1=map.get("escalated1");
                            Object escalated2=map.get("escalated2");
                            Object key=map.get("key");
                            Object uniqueID=map.get("uniqueId");
                            Object status=map.get("status");

                            String com_by_mob_str=String.valueOf(com_by_mob);
                            String com_by_name_str=String.valueOf(com_by_name);
                            String com_txt_str=String.valueOf(com_txt);
                            String date_str=String.valueOf(date);
                            String dep_of_pro_str=String.valueOf(dep_of_pro);
                            String escalated1_str=String.valueOf(escalated1);
                            String escalated2_str=String.valueOf(escalated2);
                            String key_str=String.valueOf(key);
                            String uniqueId_str=String.valueOf(uniqueID);
                            String status_str=String.valueOf(status);


                            String arr1[]={uniqueId_str,dep_of_pro_str,com_by_name_str,com_by_mob_str,com_txt_str,date_str,key_str,status_str,escalated1_str,escalated2_str};

                            for(int i = 0; i< 10; i++)
                            {
                                HSSFCell cell = row.createCell(i);
                                String data = arr1[i];
                                cell.setCellValue(data);
                                System.out.println(arr1[i]);

                            }
                            r+=1;
                        }
                    }
                    else if(dep.equals("Others")){
                        for(DataSnapshot ds1: ds.getChildren()) {
                            HSSFRow row = sheet5.createRow(r);

                            Map<String,Object> map=(Map<String, Object>) ds1.getValue();
                            Object com_by_mob=map.get("com_by_mob");
                            Object com_by_name=map.get("com_by_name");
                            Object com_txt=map.get("com_txt");
                            Object date=map.get("date");
                            Object dep_of_pro=map.get("dep_of_pro");
                            Object escalated1=map.get("escalated1");
                            Object escalated2=map.get("escalated2");
                            Object key=map.get("key");
                            Object uniqueID=map.get("uniqueId");
                            Object status=map.get("status");

                            String com_by_mob_str=String.valueOf(com_by_mob);
                            String com_by_name_str=String.valueOf(com_by_name);
                            String com_txt_str=String.valueOf(com_txt);
                            String date_str=String.valueOf(date);
                            String dep_of_pro_str=String.valueOf(dep_of_pro);
                            String escalated1_str=String.valueOf(escalated1);
                            String escalated2_str=String.valueOf(escalated2);
                            String key_str=String.valueOf(key);
                            String uniqueId_str=String.valueOf(uniqueID);
                            String status_str=String.valueOf(status);


                            String arr1[]={uniqueId_str,dep_of_pro_str,com_by_name_str,com_by_mob_str,com_txt_str,date_str,key_str,status_str,escalated1_str,escalated2_str};

                            for(int i = 0; i< 10; i++)
                            {
                                HSSFCell cell = row.createCell(i);
                                String data = arr1[i];
                                cell.setCellValue(data);
                                System.out.println(arr1[i]);

                            }
                            r+=1;
                        }
                    }

                }
                FileOutputStream fileOut = null;
                try {
                    fileOut = new FileOutputStream(file1);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    hwb.write(fileOut);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fileOut.close();
                    progressDialog.dismiss();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(Dep_wise_history.this, permission)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    Dep_wise_history.this, permission)) {
                ActivityCompat.requestPermissions(Dep_wise_history.this,
                        new String[]{permission}, requestCode);

            } else {
                ActivityCompat.requestPermissions(Dep_wise_history.this,
                        new String[]{permission}, requestCode);
            }
        }
    }

}