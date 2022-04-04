package com.example.map_my_sona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ManualEntryView extends AppCompatActivity {

    private DatabaseReference reference_complaints_history_fullView_manual;

    private TextView com_id_manual,staffName_manual,Dep_Res_manual,Branch_manual,loc_manual,object_manual,
            com_det_manual,level_of_com_manual,intercomNum_manual,phone_num_manual;

    private TextView com_id_manual_str,staffName_manual_str,Dep_Res_manual_str,Branch_manual_str,loc_manual_str,object_manual_str,
            com_det_manual_str,level_of_com_manual_str,intercomNum_manual_str,phone_num_manual_str;

    private TextView pro_id_manual,com_status_his_manual;
    private Button comp_close_manual;
    private TextView com_txt_manual;

    AlertDialog.Builder builder_manual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_entry_view);

        builder_manual=new AlertDialog.Builder(this);

        com_id_manual=(TextView) findViewById(R.id.Comid_unit_his_manual);
        staffName_manual=(TextView) findViewById(R.id.staff_name_unit_his_manual);
        Dep_Res_manual=(TextView) findViewById(R.id.dep_unit_his_manual);
        Branch_manual=(TextView) findViewById(R.id.branch_history_com_manual);
        loc_manual=(TextView) findViewById(R.id.loc_unit_his_manual);
        object_manual=(TextView) findViewById(R.id.object_unit_his_manual);
        com_det_manual=(TextView) findViewById(R.id.com_det_unit_his_manual);
        level_of_com_manual=(TextView) findViewById(R.id.level_of_com_unit_his_manual);
        intercomNum_manual=(TextView) findViewById(R.id.intercom_num_unit_his_manual);
        phone_num_manual=(TextView) findViewById(R.id.phone_unit_his_manual);

        pro_id_manual=(TextView) findViewById(R.id.Product_ID_history_manual);
        com_status_his_manual=(TextView) findViewById(R.id.complaint_status_his_manual);

        com_txt_manual=(TextView) findViewById(R.id.com_txt_history_manual);

        reference_complaints_history_fullView_manual= FirebaseDatabase.getInstance().getReference("Manual complaints").child("electronics");
        reference_complaints_history_fullView_manual.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ManualComplaint_details manualComplaint_details=snapshot.getValue(ManualComplaint_details.class);

//                com_id_manual=manualComplaint_details.get
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}