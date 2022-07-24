package com.mubasshir.adminhomenew;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecycleViewHolder> {
    private ArrayList<ProjectList> mProjectList;

    public static class RecycleViewHolder extends RecyclerView.ViewHolder{
        public TextView pro_name;
        public TextView amount_in;
        public TextView amount_out;

        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            pro_name=itemView.findViewById(R.id.project_name);
            amount_in=itemView.findViewById(R.id.amountin);
            amount_out=itemView.findViewById(R.id.amountout);
        }
    }

    public RecycleViewAdapter(ArrayList<ProjectList>projectList){
        mProjectList = projectList;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_single_row,parent,false);
        RecycleViewHolder vh=new RecycleViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        ProjectList currentlist=mProjectList.get(position);
        holder.pro_name.setText(currentlist.getproject_name());
        holder.amount_in.setText(currentlist.getamount_in());
        holder.amount_out.setText(currentlist.getamount_out());

    }

    @Override
    public int getItemCount() {
        return mProjectList.size();
    }
}
