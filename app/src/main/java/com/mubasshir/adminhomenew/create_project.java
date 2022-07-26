package com.mubasshir.adminhomenew;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class create_project extends AppCompatActivity {
    private EditText pr_name, pr_address, pr_city,amount_in,amount_out;
    private Button create_project;
    private FirebaseDatabase firebaseDatabase;
    public myadapter Adapter;
    public ArrayList<Projects>list;
    private DatabaseReference databaseProjects;
    Projects project;
    private long id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);

        create_project=(Button)findViewById(R.id.btn_create);
        pr_name=(EditText)findViewById(R.id.edp_name);
        pr_address=(EditText)findViewById(R.id.edp_address);
        pr_city=(EditText)findViewById(R.id.edp_city);
        amount_in=(EditText)findViewById(R.id.edamountin);
        amount_out=(EditText)findViewById(R.id.edamountout);

        project=new Projects();
        databaseProjects=FirebaseDatabase.getInstance().getReference().child("Projects");

        databaseProjects.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    id=(long)snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        create_project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String projectname=pr_name.getText().toString();
                String projectaddress=pr_address.getText().toString();
                String projectcity=pr_city.getText().toString();
                String amountin=pr_city.getText().toString();
                String amountout=pr_city.getText().toString();

                project.setName(pr_name.getText().toString());
                project.setAddress(pr_address.getText().toString());
                project.setCity(pr_city.getText().toString());
                project.setAmountin(amount_in.getText().toString());
                project.setAmountout(amount_in.getText().toString());

                databaseProjects.child(String.valueOf(id+1)).setValue(project);

//                databaseProjects.setValue(projectname);
//                databaseProjects.setValue(projectaddress);
//                databaseProjects.setValue(projectcity);
//                databaseProjects.setValue(amountin);
//                databaseProjects.setValue(amountout);

                Toast.makeText(com.mubasshir.adminhomenew.create_project.this, "Data Inserted Successfully", Toast.LENGTH_LONG).show();


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

                if (amountin.isEmpty()) {
                    amount_in.setError("Required!");
                    amount_in.requestFocus();
                    return;
                }

                if (amountout.isEmpty()) {
                    amount_out.setError("Required!");
                    amount_out.requestFocus();
                    return;
                }
            }
        });

    }

}