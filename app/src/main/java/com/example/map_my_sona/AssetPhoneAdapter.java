package com.example.map_my_sona;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.map_my_sona.assets.AssetDeatils1;
import com.example.map_my_sona.assets.laptops.AssetLaptopAdapter;
import com.example.map_my_sona.assets.laptops.Asset_Laptop_detail_view;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AssetPhoneAdapter extends RecyclerView.Adapter<AssetPhoneAdapter.Viewholder_AssetPhone> {

    private ArrayList<AssetDeatils1> arrayList1;
    ArrayList<String> locationNames = new ArrayList<>();
    private Context context;
    AssetPhoneAdapter.OnItemClickListerner onItemClickListerner;
    DatabaseReference reference;
    boolean testclick=false;

    public AssetPhoneAdapter(ArrayList<AssetDeatils1> arrayList, Context context){
        this.arrayList1 =arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public Viewholder_AssetPhone onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.activity_asset_layout,parent,false);
        return new AssetPhoneAdapter.Viewholder_AssetPhone(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder_AssetPhone holder, int position) {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference locationRef = rootRef.child("Assets").child("Mobiles");
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

    public class Viewholder_AssetPhone extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView lapassetID;

        public Viewholder_AssetPhone(@NonNull View itemView) {
            super(itemView);

            lapassetID=itemView.findViewById(R.id.laptop_asset_id);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int positon=getAdapterPosition();
            System.out.println("CLICKED........");
            Intent intent=new Intent(context, Asset_Phone_detail_view.class);
            intent.putExtra("mob_ID",locationNames.get(positon));
            context.startActivity(intent);
        }
    }
    public interface OnItemClickListerner{
        void onClick(int position);
    }
    public void setOnItemClickListerner(AssetPhoneAdapter.OnItemClickListerner onItemClickListerner){
        this.onItemClickListerner=onItemClickListerner;
    }
}
