package com.example.tms_sona2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Ulist_Adapter extends RecyclerView.Adapter<Ulist_Adapter.Viewholder_Ulist> {

    private List<UDetails_class> list;
    private Context context;


    public Ulist_Adapter(List<UDetails_class> list1, Ulist_Activity context) {
        this.list = list1;
        this.context = context;
    }

    @NonNull
    @Override
    public Ulist_Adapter.Viewholder_Ulist onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.userlist_layout,parent,false);
        return new Viewholder_Ulist(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Ulist_Adapter.Viewholder_Ulist holder, int position) {

        UDetails_class item=list.get(position);
        holder.name.setText(item.getUname());
        holder.pos.setText(item.getPos());

//        holder.trash.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DeleteUser(item.getUserID());
//            }
//        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder_Ulist extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView pos;
        //private ImageView trash;

        public Viewholder_Ulist(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.Uname_list);
            pos=itemView.findViewById(R.id.Upos_list);
            //trash=itemView.findViewById(R.id.user_delete);

        }
    }
}
