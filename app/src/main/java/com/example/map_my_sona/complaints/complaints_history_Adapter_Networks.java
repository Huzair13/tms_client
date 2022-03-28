package com.example.map_my_sona.complaints;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.map_my_sona.R;
import com.example.map_my_sona.complaints.Electricity.complaints_history_Adapter_Electricity;
import com.example.map_my_sona.historyviewdetails;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class complaints_history_Adapter_Networks extends RecyclerView.Adapter<complaints_history_Adapter_Networks.Viewholder_complaints_history_Netwroks> {

    private ArrayList<Complaint_details> arrayList1;
    private Context context;
    complaints_history_Adapter_Networks.OnItemClickListerner onItemClickListerner;
    DatabaseReference reference;
    boolean testclick_networks=false;

    public complaints_history_Adapter_Networks(ArrayList<Complaint_details> arrayList, Context context){
        this.arrayList1 =arrayList;
        this.context=context;
    }


    @NonNull
    @Override
    public complaints_history_Adapter_Networks.Viewholder_complaints_history_Netwroks onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.historylayout,parent,false);
        return new complaints_history_Adapter_Networks.Viewholder_complaints_history_Netwroks(view);
    }

    @Override
    public void onBindViewHolder(@NonNull complaints_history_Adapter_Networks.Viewholder_complaints_history_Netwroks holder, int position) {
        Complaint_details complaint_details=arrayList1.get(position);

        String postkey= complaint_details.getKey();
        String status_com=complaint_details.getStatus();

        if(status_com.equals("Pending")){
            holder.status_h_networks.setBackgroundResource(R.color.Red);
        }else{
            holder.status_h_networks.setBackgroundResource(R.color.green);
        }
        holder.status_h_networks.setText(complaint_details.getStatus());

        holder.comId_h_networks.setText(complaint_details.getKey());
        holder.dandt_h_networks.setText(complaint_details.getDate());
        holder.comName_h_networks.setText(complaint_details.getCom_txt());
    }

    @Override
    public int getItemCount() {
        return arrayList1.size();
    }

    public class Viewholder_complaints_history_Netwroks extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView sn_h_networks, dandt_h_networks,comId_h_networks,comName_h_networks,status_h_networks;

        public Viewholder_complaints_history_Netwroks(@NonNull View itemView) {
            super(itemView);

            sn_h_networks=itemView.findViewById(R.id.sn_history);
            dandt_h_networks=itemView.findViewById(R.id.dateandtime_history);
            comId_h_networks=itemView.findViewById(R.id.com_id_history);
            comName_h_networks=itemView.findViewById(R.id.com_name_history);
            status_h_networks=itemView.findViewById(R.id.status_history);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int positon=getAdapterPosition();
            Intent intent=new Intent(context, historyviewdetails.class);
            intent.putExtra("com_ID",arrayList1.get(positon).getKey());
            context.startActivity(intent);
        }
    }
    public interface OnItemClickListerner{
        void onClick(int position);
    }
    public void setOnItemClickListerner(complaints_history_Adapter_Networks.OnItemClickListerner onItemClickListerner){
        this.onItemClickListerner=onItemClickListerner;
    }

}
