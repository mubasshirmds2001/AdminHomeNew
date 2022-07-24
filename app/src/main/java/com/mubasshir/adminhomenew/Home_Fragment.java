package com.mubasshir.adminhomenew;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.User;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

import java.util.ArrayList;


public class Home_Fragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    myadapter madapter;
    private EditText pr_name, pr_address, pr_city;
    private Button create_project,addproject;
    DatabaseReference databaseProjects;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mlayoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        View dialogLayout = inflater.inflate(R.layout.create_dialog, container, false);
        // Inflate the layout for this fragment
        addproject=(Button)view.findViewById(R.id.btn_add_project);
        create_project=(Button)dialogLayout.findViewById(R.id.btn_create);


        mRecyclerView=(RecyclerView) view.findViewById(R.id.list_projects);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<model> Options=new FirebaseRecyclerOptions.Builder<model>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Projects"),model.class)
                .build();

        madapter=new myadapter(Options);
        mRecyclerView.setAdapter(mAdapter);


        addproject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog(dialogLayout);
            }
        });

        return view;

    }

    @Override
    public void onStart() {
       super.onStart();
       madapter.startListening();
    }


    @Override
   public void onStop() {
        super.onStop();
        madapter.stopListening();
   }

    private void showdialog(View dialogLayout) {
        Dialog dialog = new Dialog(requireActivity());
        dialog.setCancelable(true);

        dialog.setContentView(R.layout.create_dialog);
        dialog.show();//Controlling width and height.

        pr_name=(EditText) dialogLayout.findViewById(R.id.p_name);
        pr_address=(EditText) dialogLayout.findViewById(R.id.p_address);
        pr_city=(EditText) dialogLayout.findViewById(R.id.p_city);
        databaseProjects= FirebaseDatabase.getInstance().getReference("Admin");

        create_project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createdata();
                Toast.makeText(getContext(), "Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void createdata() {
        String projectname=pr_name.getText().toString();
        String projectaddress=pr_address.getText().toString();
        String projectcity=pr_city.getText().toString();
        String id=databaseProjects.push().getKey();


        if (projectname.isEmpty()) {
            pr_name.setError("Required!");
            pr_name.requestFocus();
            return;
        }

        if (projectaddress.isEmpty()) {
            pr_address.setError("Required!");
            pr_address.requestFocus();
            return;
        }

        if (projectcity.isEmpty()) {
            pr_city.setError("Required!");
            pr_city.requestFocus();
            return;
        }

    }

}