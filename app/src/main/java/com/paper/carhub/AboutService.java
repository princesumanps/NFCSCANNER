package com.paper.carhub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paper.ModelClases.ServicesPojo;
import com.paper.carhub.databinding.ActivityAboutServiceBinding;
import com.paper.carhub.databinding.ActivitySubscriptionsBinding;

import java.util.Random;

public class AboutService extends AppCompatActivity {
    ActivityAboutServiceBinding binding;
    FirebaseAuth firebaseAuth;
    int max=10000,min=1000;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAboutServiceBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        Random rand = new Random();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding.approval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.approval.setVisibility(View.GONE);
                binding.otp.setVisibility(View.VISIBLE);
                binding.check.setVisibility(View.VISIBLE);
                firebaseDatabase.getReference().child("OTP").setValue((rand.nextInt(max - min + 1) + min)+"");
            }
        });
        binding.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    firebaseDatabase.getReference().child("OTP").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String s = snapshot.getValue(String.class);
                            if (binding.inputotp.getText().toString().equals(s)){
                                firebaseDatabase.getReference().child("OTP").setValue("");
                                Intent intent = new Intent(getApplicationContext(),DONECODE.class);
                                startActivity(intent);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }catch (Exception e){

                }
            }
        });
    }
}