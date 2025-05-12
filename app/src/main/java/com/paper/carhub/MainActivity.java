package com.paper.carhub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;
import com.paper.ModelClases.Modelforwritedata;
import com.paper.ModelClases.ServicesPojo;
import com.paper.Recyclers.ServiceRecycler;
import com.paper.Subscription.Subscriptions;
import com.paper.carhub.databinding.ActivityMainBinding;
import com.paper.carhub.databinding.ActivityWritedataBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    ActivityMainBinding binding;
    HashMap<String,String> Servicesdata;
    static String customerid = "";
    String responce;
    ArrayList<ServicesPojo> servicesPojos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        // initilazitation Code
        servicesPojos = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        Servicesdata = new HashMap<>();
        responce = getIntent().getStringExtra("responce");
//        firebaseDatabase.getReference().child("Responce").setValue(0);
        binding.approved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),APPROVED.class);
                startActivity(intent);
            }
        });
        binding.history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),APPROVED.class);
                startActivity(intent);
            }
        });
        // services
        binding.services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Subscriptions.class);
                startActivity(intent);
            }
        });
        // buy plans
        binding.platinumplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.platinumplan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        firebaseDatabase.getReference().child(binding.platinumplan.getText().toString()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                                    ServicesPojo servicesPojo = dataSnapshot.getValue(ServicesPojo.class);
                                    assert servicesPojo != null;
                                    firebaseDatabase.getReference().child("CustomerData").child(customerid).child("Services").child(servicesPojo.getServiceName()).setValue(servicesPojo);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }
                });
            }
        });
        try {
            firebaseDatabase.getReference().child("Customer").child(responce).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Modelforwritedata modelforwritedata = snapshot.getValue(Modelforwritedata.class);
                        assert modelforwritedata != null;
                        binding.customername.setText(modelforwritedata.getCustomername());
                        binding.Email.setText(modelforwritedata.getEmail());
                        binding.mobileno.setText(modelforwritedata.getMobileno());
                        binding.zipcode.setText(modelforwritedata.getZipcode());
                        binding.address.setText(modelforwritedata.getAddressline1());
                        binding.customerId.setText(modelforwritedata.getCustomerid());
                        customerid = modelforwritedata.getCustomerid();
                        Glide.with(getApplicationContext()).load(modelforwritedata.getPhoto()).into(binding.photo);
                        Glide.with(getApplicationContext()).load(modelforwritedata.getSignature()).into(binding.signature);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }catch (Exception e){

        }
        try{
            firebaseDatabase.getReference().child("BasicPlan").child("Plane").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    servicesPojos.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        ServicesPojo servicesPojo = dataSnapshot.getValue(ServicesPojo.class);
                        servicesPojos.add(servicesPojo);
                    }
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    ServiceRecycler serviceRecycler = new ServiceRecycler(getApplicationContext(),servicesPojos);
                    binding.recyclerforservices.setLayoutManager(linearLayoutManager);
                    binding.recyclerforservices.setAdapter(serviceRecycler);
                    serviceRecycler.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }catch (Exception e){

        }

        firebaseDatabase.getReference().child("Plans").child("Premium").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}