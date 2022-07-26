package com.mubasshir.adminhomenew;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.common.internal.service.Common;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Home_Fragment extends Fragment {
    myadapter madapter;
//    private EditText pr_name, pr_address, pr_city;
    private Button addproject;
    public RecyclerView.LayoutManager layoutManager;
    public myadapter Adapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseProjects;
    private RecyclerView mRecyclerView;
    ArrayList<Projects> list;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        View dialogLayout = inflater.inflate(R.layout.create_dialog, container, false);
        // Inflate the layout for this fragment
        addproject=(Button)view.findViewById(R.id.btn_add_project);
//        create_project=(Button)dialogLayout.findViewById(R.id.btn_create);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseProjects = firebaseDatabase.getReference("Projects");


        mRecyclerView=(RecyclerView) view.findViewById(R.id.list_projects);
//        databaseProjects=FirebaseDatabase.getInstance().getReference("Projects");
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        list=new ArrayList<>();
        madapter=new myadapter(getActivity(),list);
        mRecyclerView.setAdapter(madapter);

        databaseProjects.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Projects projects=dataSnapshot.getValue(Projects.class);
                    list.add(projects);

                }
                madapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("projectData", "data failed");
            }
        });


        addproject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showdialog(dialogLayout);
                Intent intent=new Intent(getActivity(),create_project.class);
                startActivity(intent);
            }
        });

        return view;

    }

    @Override
    public void onStart() {
       super.onStart();
//       madapter.startListening();
    }


    @Override
   public void onStop() {
        super.onStop();
//        madapter.stopListening();
   }

    private void showdialog(View dialogLayout) {
        Dialog dialog = new Dialog(requireActivity());
        dialog.setCancelable(true);

        dialog.setContentView(R.layout.create_dialog);
        dialog.show();//Controlling width and height.

//        pr_name=(EditText) dialogLayout.findViewById(R.id.p_name);
//        pr_address=(EditText) dialogLayout.findViewById(R.id.p_address);
//        pr_city=(EditText) dialogLayout.findViewById(R.id.p_city);


//        create_project.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                createdata();
//                Toast.makeText(getContext(), "Button Clicked", Toast.LENGTH_SHORT).show();
//            }
//        });


    }


//    private void createdata() {
//        String projectname=pr_name.getText().toString();
//        String projectaddress=pr_address.getText().toString();
//        String projectcity=pr_city.getText().toString();
//        String id=databaseProjects.push().getKey();
//
//
//        if (projectname.isEmpty()) {
//            pr_name.setError("Required!");
//            pr_name.requestFocus();
//            return;
//        }
//
//        if (projectaddress.isEmpty()) {
//            pr_address.setError("Required!");
//            pr_address.requestFocus();
//            return;
//        }
//
//        if (projectcity.isEmpty()) {
//            pr_city.setError("Required!");
//            pr_city.requestFocus();
//            return;
//        }

    }

//}