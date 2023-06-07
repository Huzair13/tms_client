package com.example.tms_sona2.assets.tv;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tms_sona2.R;
import com.example.tms_sona2.assets.AssetDeatils1;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AssetTvAdapter extends RecyclerView.Adapter<AssetTvAdapter.Viewholder_Assetv> {

    private ArrayList<AssetDeatils1> arrayList1;
    ArrayList<String> locationNames = new ArrayList<>();
    private Context context;
    AssetTvAdapter.OnItemClickListerner onItemClickListerner;
    DatabaseReference reference;
    boolean testclick=false;

    public AssetTvAdapter(ArrayList<AssetDeatils1> arrayList, Context context){
        this.arrayList1 =arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public AssetTvAdapter.Viewholder_Assetv onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.activity_asset_layout,parent,false);
        return new AssetTvAdapter.Viewholder_Assetv(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssetTvAdapter.Viewholder_Assetv holder, int position) {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference locationRef = rootRef.child("Assets").child("Televisions");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key = ds.getKey();
                    locationNames.add(key);
                }
                holder.lapassetID.setText(locationNames.get(position));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        locationRef.addListenerForSingleValueEvent(eventListener);
    }

    @Override
    public int getItemCount() {
        return arrayList1.size();
    }

    public class Viewholder_Assetv extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView lapassetID;

        public Viewholder_Assetv(@NonNull View itemView) {
            super(itemView);

            lapassetID=itemView.findViewById(R.id.laptop_asset_id);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int positon=getAdapterPosition();
            System.out.println("CLICKED........");
            Intent intent=new Intent(context, Asset_Tv_detail_view.class);
            intent.putExtra("tv_ID",locationNames.get(positon));
            context.startActivity(intent);
        }
    }

    public interface OnItemClickListerner{
        void onClick(int position);
    }
    public void setOnItemClickListerner(AssetTvAdapter.OnItemClickListerner onItemClickListerner){
        this.onItemClickListerner=onItemClickListerner;
    }

}
