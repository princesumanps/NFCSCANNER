package com.paper.Subscription;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paper.ModelClases.ServicesPojo;
import com.paper.Recyclers.ServiceRecycler;
import com.paper.carhub.R;

import java.util.ArrayList;

public class AdvancePlan extends Fragment {
    FirebaseAuth Auth;
    FirebaseDatabase firebaseDatabase;
    ArrayList<ServicesPojo> Productlist;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_advance_plan, container, false);
        Productlist = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler);
        firebaseDatabase = FirebaseDatabase.getInstance();
        Auth = FirebaseAuth.getInstance();
        try {
            firebaseDatabase.getReference().child("BasicPlan").child("Plane").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Productlist.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        ServicesPojo Pojoforproducts = dataSnapshot.getValue(ServicesPojo.class);
                        Productlist.add(Pojoforproducts);
                    }
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    ServiceRecycler serviceRecycler = new ServiceRecycler(getContext(),Productlist);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(serviceRecycler);
                    serviceRecycler.notifyDataSetChanged();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }catch (Exception e){

        }
        return  view;
    }
}