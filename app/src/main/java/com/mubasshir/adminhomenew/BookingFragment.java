package com.mubasshir.adminhomenew;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class BookingFragment extends Fragment {
    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_booking, container, false);
        recyclerView=view.findViewById(R.id.BookingsRecycler);
        recyclerView.setLayoutManager((new LinearLayoutManager(getContext())));
        return view;
    }
}