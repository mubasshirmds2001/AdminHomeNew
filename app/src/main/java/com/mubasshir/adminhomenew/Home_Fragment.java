package com.mubasshir.adminhomenew;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Home_Fragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    //    myadapter madapter;
    private EditText pr_name, pr_address, pr_city;
    private TextView closeButton;
    private Button create_project, addproject;
    DatabaseReference databaseProjects;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mlayoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Inflate the layout for this fragment
        addproject = (Button) view.findViewById(R.id.btn_add_project);


        mRecyclerView = (RecyclerView) view.findViewById(R.id.list_projects);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        FirebaseRecyclerOptions<model> Options = new FirebaseRecyclerOptions.Builder<model>()
//                .setQuery(FirebaseDatabase.getInstance().getReference().child("Projects"), model.class)
//                .build();
//
//        madapter = new myadapter(Options);
        mRecyclerView.setAdapter(mAdapter);


        addproject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        return view;

    }


    private void showDialog() {

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.create_project_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(dialogView);


        pr_name = (EditText) dialogView.findViewById(R.id.p_name);
        pr_address = (EditText) dialogView.findViewById(R.id.p_address);
        pr_city = (EditText) dialogView.findViewById(R.id.p_city);
        create_project = dialogView.findViewById(R.id.btn_create);
        closeButton = dialogView.findViewById(R.id.btn_close);

        databaseProjects = FirebaseDatabase.getInstance().getReference("Admin");

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();


        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });


        create_project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                createdata();

                if (validateInput(pr_name, pr_address, pr_city)) {
                    String projectName = pr_name.getText().toString();
                    Toast.makeText(getContext(), "Project name: " + projectName, Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(getContext(), "not validated", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private boolean validateInput(EditText pr_name, EditText pr_address, EditText pr_city) {

        String projectName = pr_name.getText().toString();
        String address = pr_address.getText().toString();
        String cityName = pr_city.getText().toString();


        if (projectName.isEmpty()) {
            pr_name.setError("project name is required");
            pr_name.requestFocus();
            return false;
        }

        if (address.isEmpty()) {
            pr_address.setError("Address is required");
            pr_name.requestFocus();
            return false;
        }

        if (cityName.isEmpty()) {
            pr_city.setError("City name is required");
            pr_city.requestFocus();
            return false;
        }

        return true;
    }


    private void createdata() {
        String projectname = pr_name.getText().toString();
        String projectaddress = pr_address.getText().toString();
        String projectcity = pr_city.getText().toString();
        String id = databaseProjects.push().getKey();


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
        }

    }

}