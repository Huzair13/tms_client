package com.example.map_my_sona.manualComplaints;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.map_my_sona.R;
import com.example.map_my_sona.manualComplaints.ManualViewDetails.historyviewdetails_manual;
import com.example.map_my_sona.manualComplaints.ManualViewDetails.historyviewdetails_manual_carpenter;
import com.example.map_my_sona.manualComplaints.ManualViewDetails.historyviewdetails_manual_networks;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class manual_complaints_history_adapter extends RecyclerView.Adapter<manual_complaints_history_adapter.Viewholder_manual_complaint_history> {

    private ArrayList<ManualComplaint_details> arrayList1;
    private Context context;
    manual_complaints_history_adapter.OnItemClickListerner onItemClickListerner;
    DatabaseReference reference;
    boolean testclick=false;

    public manual_complaints_history_adapter(ArrayList<ManualComplaint_details> arrayList, Context context){
        this.arrayList1 =arrayList;
        this.context=context;
    }


    @NonNull
    @Override
    public manual_complaints_history_adapter.Viewholder_manual_complaint_history onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.manuallayout,parent,false);
        return new manual_complaints_history_adapter.Viewholder_manual_complaint_history(view);
    }

    @Override
    public void onBindViewHolder(@NonNull manual_complaints_history_adapter.Viewholder_manual_complaint_history holder, int position) {
        ManualComplaint_details manualComplaint_details=arrayList1.get(position);

        String postkey= manualComplaint_details.getKey();
        String status_com=manualComplaint_details.getStatus();

        if(status_com.equals("Pending")){
            holder.status_h.setBackgroundResource(R.color.Red);
        }else{
            holder.status_h.setBackgroundResource(R.color.green);
        }
        holder.status_h.setText(manualComplaint_details.getStatus());
        holder.dandt_h.setText(manualComplaint_details.getDate());
        holder.comName_h.setText(manualComplaint_details.getCom_details());
    }

    @Override
    public int getItemCount() {
        return arrayList1.size();
    }

    public class Viewholder_manual_complaint_history extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView sn_h, dandt_h,comId_h,comName_h,status_h;

        public Viewholder_manual_complaint_history(@NonNull View itemView) {
            super(itemView);

            sn_h = itemView.findViewById(R.id.sn_history_manual);
            dandt_h = itemView.findViewById(R.id.dateandtime_history_manual);
            comName_h = itemView.findViewById(R.id.com_name_history_manual);
            status_h = itemView.findViewById(R.id.status_history_manual);

            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {
            int positon=getAdapterPosition();
            ManualComplaint_details manualComplaint_details=arrayList1.get(positon);
            String pro_dep=manualComplaint_details.getDep_res();
            if(pro_dep.equals("electronics")){
                Intent intent=new Intent(context, historyviewdetails_manual.class);
                intent.putExtra("com_IDM",arrayList1.get(positon).getKey());
                context.startActivity(intent);
            }
            else if(pro_dep.equals("Network")){
                Intent intent=new Intent(context, historyviewdetails_manual_networks.class);
                intent.putExtra("com_IDM",arrayList1.get(positon).getKey());
                context.startActivity(intent);
            }
            else if(pro_dep.equals("Carpenting")){
                Intent intent=new Intent(context, historyviewdetails_manual_carpenter.class);//
                intent.putExtra("com_IDM",arrayList1.get(positon).getKey());
                context.startActivity(intent);
            }
//            else if(pro_dep.equals("Plumber")){
//                Intent intent=new Intent(context, historyviewdetails_plumber.class);//
//                intent.putExtra("com_IDM",arrayList1.get(positon).getKey());
//                context.startActivity(intent);
//            }
//            else if(pro_dep.equals("Painting")){
//                Intent intent=new Intent(context, historyviewdetails_painting.class);//
//                intent.putExtra("com_IDM",arrayList1.get(positon).getKey());
//                context.startActivity(intent);
//            }
        }
    }

    public interface OnItemClickListerner{
        void onClick(int position);
    }
    public void setOnItemClickListerner(manual_complaints_history_adapter.OnItemClickListerner onItemClickListerner){
        this.onItemClickListerner=onItemClickListerner;
    }
}
