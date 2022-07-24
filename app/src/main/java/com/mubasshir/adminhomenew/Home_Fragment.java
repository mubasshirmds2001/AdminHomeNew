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
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;


public class Home_Fragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private EditText pr_name, pr_address, pr_city;
    private Button create_project,addproject;
    DatabaseReference databaseProjects;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        View dialogLayout = inflater.inflate(R.layout.create_dialog, container, false);
        // Inflate the layout for this fragment
        addproject=(Button)view.findViewById(R.id.btn_add_project);

        addproject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog(dialogLayout);
            }
        });

        return view;

    }

    private void showdialog(View dialogLayout) {
        Dialog dialog = new Dialog(requireActivity());
        dialog.setCancelable(true);

        dialog.setContentView(R.layout.create_dialog);
        dialog.show();//Controlling width and height.

        pr_name=(EditText) dialogLayout.findViewById(R.id.p_name);
        pr_address=(EditText) dialogLayout.findViewById(R.id.p_address);
        pr_city=(EditText) dialogLayout.findViewById(R.id.p_city);
        create_project=(Button)dialog.findViewById(R.id.btn_create);
        databaseProjects= FirebaseDatabase.getInstance().getReference();

        create_project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createdata();
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

        user user=new user(projectname,projectaddress,projectcity);
        databaseProjects.child("Projects").child(id).setValue("user").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getActivity(), "Project created", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}