package com.paper.carhub;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.paper.ModelClases.Modelforwritedata;
import com.paper.carhub.databinding.ActivityWritedataBinding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Array;

public class WRITEDATA extends AppCompatActivity {
    ActivityWritedataBinding binding;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseStorage firebaseStorage;
    ActivityResultLauncher<String> launcher;
    ActivityResultLauncher<String> launchersig;
    static String filtercoutomername = "";
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWritedataBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        // initalization section
        firebaseStorage = FirebaseStorage.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        s = getIntent().getStringExtra("responce");
        // signature launcher call
        launchersig = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri o) {
                StorageReference referencei = firebaseStorage.getReference().child("Users").child(binding.customername.getText().toString()+" "+binding.email.getText().toString()).child("Signature");
                Bitmap bmp = null;
                try {
                    bmp = MediaStore.Images.Media.getBitmap(getContentResolver(), o);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG,25, baos);
                byte[] data = baos.toByteArray();
                referencei.putBytes(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        referencei.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                firebaseDatabase.getReference().child("CustomerData").child(filtercoutomername).child("Signature").setValue(uri.toString());
                            }
                        });
                    }
                });
            }
        });

        // photo launcher call
        launcher =registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri o) {
                StorageReference reference = firebaseStorage.getReference().child("Users").child("LOPO").child("Photo");
                Bitmap bmp = null;
                try {
                    bmp = MediaStore.Images.Media.getBitmap(getContentResolver(), o);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG,25, baos);
                byte[] data = baos.toByteArray();

    reference.putBytes(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
        @Override
        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
        @Override
        public void onSuccess(Uri uri) {
            Toast.makeText(WRITEDATA.this, "POCO", Toast.LENGTH_SHORT).show();

                firebaseDatabase.getReference().child("CustomerData").child("filtercoutomername").child("Photo").setValue(uri.toString());
    }
});
    }
});
            }
        });


//        binding.imageofprofile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                launcher.launch("image/*");
//            }
//        });


        Modelforwritedata modelforwritedata = new Modelforwritedata(binding.customername.getText().toString(),binding.mobileno.getText().toString(),binding.email.getText().toString(),binding.addressline1.getText().toString(),binding.addressline2.getText().toString(),binding.zipcode.getText().toString(),binding.customername.getText().toString()+binding.zipcode.getText().toString()+binding.mobileno.getText().toString(),null,null);
        binding.selectplane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FilldataCheck()) {
                    firebaseDatabase.getReference().child("Responcei").child(s+"").child("name").setValue(s+"");
                    firebaseDatabase.getReference().child("Customer").child(s).setValue(modelforwritedata);
                    Intent intent = new Intent(getApplicationContext(), DONECODE.class);
                    startActivity(intent);
                }
            }
        });
//        binding.selectplane.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (FilldataCheck()) {
//                    try {
//                        firebaseDatabase.getReference().child("Counter").addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                String value = snapshot.getValue(String.class);
//                                assert value != null;
//                                int valueint = Integer.parseInt(value);
//                                valueint++;
//                                firebaseDatabase.getReference().child("Counter").setValue(String.valueOf(valueint));
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//
//                            }
//                        });
//                    } catch (Exception e) {
//
//                    }
//                    filtercoutomername = filtercoustomername(binding.customername.getText().toString());
//                    firebaseDatabase.getReference().child("CustomerData").child(filtercoutomername).setValue(modelforwritedata).addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void unused) {
//                            firebaseDatabase.getReference().child("Responce").setValue(filtercoutomername);
//                            Toast.makeText(WRITEDATA.this, "Data is set suceed", Toast.LENGTH_SHORT).show();
////                            while ()
//                            checkwritecard();
//                        }
//                    });
//                }
//            }
//        });
        // writecard datacheck

        // Upload Signature or Idproof Image

    }

    boolean FilldataCheck(){
        if (binding.customername==null){
            return false;
        } else if (binding.zipcode==null) {
            return false;
        } else if (binding.mobileno==null) {
            return false;
        } else if (binding.addressline1==null) {
            return false;
        } else if (binding.addressline2==null) {
            return false;
        }else if (binding.email==null){
            return false;
        } else {
            return true;
        }
    }

    //public class Main {
    //    public static void main(String[] args) {
    //        final String[] namei = new String[1];
    //        namei[0] = "HALLOPRINCE";
    //       String name = namei[0];
    //       for (int i = 0; i<namei[0].length(); i++) {
    //           System.out.println(namei[0].charAt(i));
    //       }
    //    }
    //}


    String filtercoustomername(String name){
        final String[] namei = new String[1];
        final String[] filteredid = new String[1];
        try {
            firebaseDatabase.getReference().child("Counter").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String value = snapshot.getValue(String.class);
                    namei[0] = name.substring(0,2)+binding.mobileno.toString().substring(6,9)+name.substring(4,6)+value;
                    int b = 0;
                    for (int i = 0; i< namei[0].length(); i++){
                        if (checkspecialcharacter(namei[0].charAt(i))){

                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }catch (Exception e){

        }

        return namei[0];
    }
    boolean checkspecialcharacter(char names){
        if (names=='#' & names=='@' & names=='!' & names=='$' & names=='%' & names=='^' & names=='*' & names=='(' & names==')'& names=='_' & names=='-' & names=='+' & names=='=' & names =='/'){
            return false;
        }
        return true;
    }
    boolean writecard(){
        final boolean[] respo = new boolean[1];
        firebaseDatabase.getReference().child("Responce").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String responce = snapshot.getValue(String.class);
                if (responce == "Suceed"){
                    respo[0] = true;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return respo[0];
    }
    void checkwritecard(){

        if (writecard()){
            firebaseDatabase.getReference().child("Responce").setValue("false");
        }
    }
}