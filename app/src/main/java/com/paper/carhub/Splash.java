package com.paper.carhub;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paper.carhub.databinding.ActivitySplashBinding;

import java.util.ArrayList;
import java.util.Objects;

public class Splash extends AppCompatActivity {
    ActivitySplashBinding binding;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        list = new ArrayList<>();
        list.add(0,"0");
        new Handler().postDelayed(() -> {
                binding.imageView.setVisibility(View.GONE);
                binding.waitcard.setVisibility(View.VISIBLE);
        }, 3000);

        try {
            firebaseDatabase.getReference().child("Responce").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Long respo = snapshot.getValue(Long.class);
                    final String[] responce = {respo.toString()};

                    firebaseDatabase.getReference().child("Responcei").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int check = 0;
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                                testingpojo Testingpojo = dataSnapshot.getValue(testingpojo.class);
                                assert Testingpojo != null;
                                if(responce[0].equals(Testingpojo.getName())){
                                    check = 1;
                                }
                            }
                            if (check!=1){
                                if (!Objects.equals(responce[0], "0")) {
                                    Intent intent = new Intent(Splash.this, WRITEDATA.class);
                                    intent.putExtra("responce", responce[0]);
                                    responce[0] = "0";
                                    firebaseDatabase.getReference().child("Responce").setValue(0);
                                    startActivity(intent);
                                }
                            }else {
                                if (!Objects.equals(responce[0], "0")) {
                                    Intent intent = new Intent(Splash.this, MainActivity.class);
                                    intent.putExtra("responce", responce[0]);
                                    Toast.makeText(Splash.this, "" + responce[0], Toast.LENGTH_SHORT).show();
                                    responce[0] = "0";
                                    firebaseDatabase.getReference().child("Responce").setValue(0);
                                    check = 0;
                                    startActivity(intent);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } catch (Exception e) {

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        firebaseDatabase.getReference().child("Responce").setValue(0);
    }
}