package com.paper.carhub;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paper.ModelClases.ServicesPojo;
import com.paper.Recyclers.ServiceRecycler;

import java.util.ArrayList;

public class APPROVED extends AppCompatActivity {
    FirebaseAuth Auth;
    FirebaseDatabase firebaseDatabase;
    ArrayList<ServicesPojo> Productlist;
    RecyclerView recyclerView;
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_approved);
        Productlist = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler);
        firebaseDatabase = FirebaseDatabase.getInstance();
        frameLayout = findViewById(R.id.back);
        Auth = FirebaseAuth.getInstance();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        try {
            firebaseDatabase.getReference().child("BasicPlan").child("Plane").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Productlist.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        ServicesPojo Pojoforproducts = dataSnapshot.getValue(ServicesPojo.class);
                        Productlist.add(Pojoforproducts);
                    }
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    ServiceRecycler serviceRecycler = new ServiceRecycler(getApplicationContext(),Productlist);
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
    }
}