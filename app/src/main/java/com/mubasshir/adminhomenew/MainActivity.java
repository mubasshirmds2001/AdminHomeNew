package com.mubasshir.adminhomenew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private Home_Fragment homeFragment;
    private Profile_fragment profileFragment;
    private BookingFragment bookingFragment;
    private int selectedFragmentID;

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
