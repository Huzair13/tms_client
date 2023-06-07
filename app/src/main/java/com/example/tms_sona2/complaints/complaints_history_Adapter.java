package com.example.tms_sona2.complaints;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tms_sona2.R;
import com.example.tms_sona2.complaints.viewDetails.historyviewdetails_carpenter;
import com.example.tms_sona2.complaints.viewDetails.historyviewdetails_networks;
import com.example.tms_sona2.complaints.viewDetails.historyviewdetails;
import com.example.tms_sona2.complaints.viewDetails.historyviewdetails_painting;
import com.example.tms_sona2.complaints.viewDetails.historyviewdetails_plumber;
import com.example.tms_sona2.complaints.viewDetails.historyviewdetails_others;
import com.example.tms_sona2.complaints.viewDetails.historyviewdetails_assets;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class complaints_history_Adapter extends RecyclerView.Adapter<complaints_history_Adapter.Viewholder_complaints_history> {

    private ArrayList<Complaint_details> arrayList1;
    private Context context;
    complaints_history_Adapter.OnItemClickListerner onItemClickListerner;
    DatabaseReference reference;
    boolean testclick=false;

    public complaints_history_Adapter(ArrayList<Complaint_details> arrayList, Context context){
        this.arrayList1 =arrayList;
        this.context=context;
    }


    @NonNull
    @Override
    public complaints_history_Adapter.Viewholder_complaints_history onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.history_table,parent,false);
        return new Viewholder_complaints_history(view);

    }

    @Override
    public void onBindViewHolder(@NonNull complaints_history_Adapter.Viewholder_complaints_history holder, int position) {

        Complaint_details complaint_details=arrayList1.get(position);

        String postkey= complaint_details.getKey();
        String status_com=complaint_details.getStatus();
        int size1=arrayList1.size();
        int pos_sn=position+1;
        int pos_sn1=(size1-position);
        String pos_sn_Str=String.valueOf(pos_sn1);
        String uniqueId=complaint_details.getUniqueId();

        if(status_com.equals("Pending")){
            holder.status_h.setBackgroundResource(R.color.Red);
        }else{
            holder.status_h.setBackgroundResource(R.color.green);
        }
        holder.status_h.setText(complaint_details.getStatus());
        holder.dandt_h.setText(complaint_details.getDate());
        holder.comName_h.setText(complaint_details.getCom_txt());
        holder.sn_h.setText(pos_sn_Str);
        holder.uniqueiD.setText(uniqueId);
//start
        final ArrayList<String> locationNames = new ArrayList<>();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference locationRef = rootRef.child("complaints");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key = ds.getKey();
                    Log.d("TAG", key);
                    System.out.println(key);
                    locationNames.add(key);
                    System.out.println(locationNames);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        locationRef.addListenerForSingleValueEvent(eventListener);

    }
    //end

    @Override
    public int getItemCount() {
        return arrayList1.size();
    }

    public class Viewholder_complaints_history extends RecyclerView.ViewHolder implements  View.OnClickListener {

        TextView sn_h, dandt_h,comId_h,comName_h,status_h,uniqueiD;

        public Viewholder_complaints_history(@NonNull View itemView) {
            super(itemView);

            reference= FirebaseDatabase.getInstance().getReference("complaints").child("Electricity");

            sn_h=itemView.findViewById(R.id.sn_history);
            dandt_h=itemView.findViewById(R.id.dateandtime_history);
            comName_h=itemView.findViewById(R.id.com_name_history);
            status_h=itemView.findViewById(R.id.status_history);
            uniqueiD=itemView.findViewById(R.id.uniqueid);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int positon=getAdapterPosition();
            System.out.println(positon);
            Complaint_details complaint_details=arrayList1.get(positon);
            String pro_dep=complaint_details.getDep_of_pro();
            if(pro_dep.equals("Electricity")){
                Intent intent=new Intent(context, historyviewdetails.class);
                intent.putExtra("com_ID",arrayList1.get(positon).getKey());
                context.startActivity(intent);
            }
            else if(pro_dep.equals("Network")){
                Intent intent=new Intent(context, historyviewdetails_networks.class);
                intent.putExtra("com_ID",arrayList1.get(positon).getKey());
                context.startActivity(intent);
            }
            else if(pro_dep.equals("Carpenter")){
                Intent intent=new Intent(context, historyviewdetails_carpenter.class);
                intent.putExtra("com_ID",arrayList1.get(positon).getKey());
                context.startActivity(intent);
            }
            else if(pro_dep.equals("Pluming")){
                Intent intent=new Intent(context, historyviewdetails_plumber.class);
                intent.putExtra("com_ID",arrayList1.get(positon).getKey());
                context.startActivity(intent);
            }
            else if(pro_dep.equals("Painting")){
                Intent intent=new Intent(context, historyviewdetails_painting.class);
                intent.putExtra("com_ID",arrayList1.get(positon).getKey());
                context.startActivity(intent);
            }
            else if(pro_dep.equals("Others")){
                Intent intent=new Intent(context, historyviewdetails_others.class);
                intent.putExtra("com_ID",arrayList1.get(positon).getKey());
                context.startActivity(intent);
            }
            else if(pro_dep.equals("Assets")){
                Intent intent=new Intent(context, historyviewdetails_assets.class);
                intent.putExtra("com_ID",arrayList1.get(positon).getKey());
                context.startActivity(intent);
            }

        }
    }

    public interface OnItemClickListerner{
        void onClick(int position);
    }
    public void setOnItemClickListerner(complaints_history_Adapter.OnItemClickListerner onItemClickListerner){
        this.onItemClickListerner=onItemClickListerner;
    }

}