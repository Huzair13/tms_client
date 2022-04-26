package com.example.map_my_sona.rating;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.map_my_sona.R;
import com.example.map_my_sona.complaints.Complaint_details;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class rating_adapter_elec extends RecyclerView.Adapter<rating_adapter_elec.Viewholder_rating_elec> {

    private ArrayList<Complaint_details> arrayList1;
    private Context context;
    rating_adapter_elec.OnItemClickListerner onItemClickListerner;
    DatabaseReference reference;
    boolean testclick=false;

    public rating_adapter_elec(ArrayList<Complaint_details> arrayList, Context context){
        this.arrayList1 =arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public rating_adapter_elec.Viewholder_rating_elec onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.rating_layout,parent,false);
        return new rating_adapter_elec.Viewholder_rating_elec(view);
    }

    @Override
    public void onBindViewHolder(@NonNull rating_adapter_elec.Viewholder_rating_elec holder, int position) {

        Complaint_details complaint_details=arrayList1.get(position);


        String postkey= complaint_details.getKey();
        int rat_pos_sn=position+1;
        String rat_pos_sn_str=String.valueOf(rat_pos_sn);

        holder.sno.setText(rat_pos_sn_str);
        holder.comName_h.setText(complaint_details.getCom_txt());
        holder.rating.setText(complaint_details.getRating());

    }

    @Override
    public int getItemCount() {
        return arrayList1.size();
    }

    public class Viewholder_rating_elec extends RecyclerView.ViewHolder{

        TextView comName_h,rating,sno;
        public Viewholder_rating_elec(@NonNull View itemView) {
            super(itemView);

            sno=itemView.findViewById(R.id.sn_rating);
            comName_h=itemView.findViewById(R.id.com_name_rating);
            rating=itemView.findViewById(R.id.rating_box);

        }
    }
    public interface OnItemClickListerner{
        void onClick(int position);
    }
    public void setOnItemClickListerner(rating_adapter_elec.OnItemClickListerner onItemClickListerner){
        this.onItemClickListerner=onItemClickListerner;
    }
}
