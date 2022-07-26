package com.mubasshir.adminhomenew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseDatabase firebaseDatabase;
    private Home_Fragment homeFragment;
    private Profile_fragment profileFragment;
    private BookingFragment bookingFragment;
    private int selectedFragmentID;
    private String pname,paddress,pcity,amtin,amtout;
    private DatabaseReference databaseProjects;
    private String projectID = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFragment=new Home_Fragment();
        bookingFragment=new BookingFragment();
        profileFragment=new Profile_fragment();

        bottomNavigationView = findViewById(R.id.bottom_nav);

        loadDefaultFragment();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch(item.getItemId()){
                    case R.id.home:
                        selectedFragment = new Home_Fragment();
                        break;

                    case R.id.projects:
                        selectedFragment = new BookingFragment();
                        break;

                    case R.id.profile:
                        selectedFragment = new Profile_fragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,selectedFragment).commit();
                return true;
            }
        });
        saveData();

    }

    private void saveData() {
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        databaseProjects = FirebaseDatabase.getInstance().getReference("Projects");
        Log.d("logInfo", databaseProjects.toString());
        if (user != null){
            projectID = user.getUid();
        }

//        databaseProjects.child(projectID).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Projects projectInfo = snapshot.getValue(Projects.class);
//
//                if (projectInfo != null){
//                    pname = projectInfo.name;
//                    paddress = projectInfo.address;
//                    pcity = projectInfo.city;
//                    amtin = projectInfo.amountin;
//                    amtout = projectInfo.amountout;
//
//
//                    SharedPreferences.Editor editor = getSharedPreferences("userInfo", MODE_PRIVATE).edit();
//                    editor.putString("ProjectName", pname);
//                    editor.putString("ProjectAddress", paddress);
//                    editor.putString("ProjectCity", pcity);
//                    editor.putString("AmountIn", amtin);
//                    editor.putString("AmountOut", amtout);
//                    editor.apply();
//                }
//
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void loadDefaultFragment() {
        Intent intent = getIntent();
        selectedFragmentID = intent.getIntExtra("openBookings", 1);

        if (selectedFragmentID == 1){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container,homeFragment).commit();
        }
        else if(selectedFragmentID == 2){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container,bookingFragment).commit();

        }
    }
}
