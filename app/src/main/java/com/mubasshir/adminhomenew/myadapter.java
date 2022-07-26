package com.mubasshir.adminhomenew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
import java.util.List;

public class myadapter extends RecyclerView.Adapter<myadapter.MyViewHolder> {

    Context context;
    ArrayList<Projects>list;

    public myadapter(Context context, ArrayList<Projects> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =LayoutInflater.from(context).inflate(R.layout.project_single_row,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myadapter.MyViewHolder holder, int position) {

        Projects projects=list.get(position);
        holder.projectname.setText(projects.getName());
        holder.projectaddress.setText(projects.getAddress());
        holder.projectcity.setText(projects.getCity());
        holder.amountin.setText(projects.getAmountin());
        holder.amountout.setText(projects.getAmountout());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView projectname,projectaddress,projectcity,amountin,amountout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            projectname=itemView.findViewById(R.id.tvproject_name);
            projectaddress=itemView.findViewById(R.id.tvproject_address);
            projectcity=itemView.findViewById(R.id.tvproject_city);
            amountin=itemView.findViewById(R.id.tvamountin);
            amountout=itemView.findViewById(R.id.tvamountout);
        }
    }


}
