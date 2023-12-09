package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.whatsapp.Models.users;
import com.example.whatsapp.databinding.ActivityLoginBinding;
import com.example.whatsapp.databinding.ActivitySettingsBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    ActivitySettingsBinding binding;

    FirebaseAuth mauth;
    FirebaseDatabase database;
    FirebaseStorage storage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySettingsBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        mauth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();

        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
            }
        });

        binding.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/");
                startActivityForResult(intent,25);
            }
        });

        binding.savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!binding.txtusername.getText().toString().equals("") && !binding.etAbout.getText().toString().equals(""))
                {
                    String status=binding.etAbout.getText().toString();
                    String username=binding.txtusername.getText().toString();

                    HashMap<String,Object> obj=new HashMap<>();
                    obj.put("username",username);
                    obj.put("About",status);

                    database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                            .updateChildren(obj);

                    Toast.makeText(SettingsActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SettingsActivity.this, "Please fill the details!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        users User=snapshot.getValue(users.class);
                        Picasso.get()
                                .load(User.getProfilepic())
                                .placeholder(R.drawable.avatar).into(binding.profileImage);

                        binding.etAbout.setText(User.getStatus());
                        binding.txtusername.setText(User.getUsername());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(SettingsActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


        binding.tvprivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the URL of the PDF file on Google Drive
                String driveUrl = "https://drive.google.com/file/d/12xjzIhoeHaflybkRFBbObotGjuPhqqtQ/view?usp=sharing";

                // Extract the file ID from the URL
                String fileId = extractFileIdFromUrl(driveUrl);

                // Create an intent to open the PDF file using the Google Drive app
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://drive.google.com/file/d/" + fileId));
                intent.setPackage("com.google.android.apps.docs");

                // Verify that the Google Drive app is installed on the device
                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
                boolean isIntentSafe = activities.size() > 0;

                // Start the activity to open the PDF file if the Google Drive app is installed
                if (isIntentSafe) {
                    startActivity(intent);
                } else {
                    // Handle the case where the Google Drive app is not installed
                    Toast.makeText(getApplicationContext(), "Google Drive app not found", Toast.LENGTH_SHORT).show();
                }
            }

            private String extractFileIdFromUrl(String driveUrl) {
                int startIndex = driveUrl.indexOf("/d/") + 3;
                int endIndex = driveUrl.indexOf("/view");
                return driveUrl.substring(startIndex, endIndex);
            }
        });


        binding.tvabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the URL you want to open
                String url = "https://www.linkedin.com/in/ritiktiwari95/";

                // Create an intent to open the URL
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));

                // Start the activity to open the URL
                startActivity(intent);
            }
        });

        binding.tvinvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingsActivity.this, "Still I am working on it!", Toast.LENGTH_SHORT).show();
            }
        });

        binding.tvnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingsActivity.this, "Still I am working on it...", Toast.LENGTH_SHORT).show();
            }
        });

        binding.tvhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define your email address
                String emailAddress = "rt936649@gmail.com";

                // Create an intent to send an email
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + emailAddress));

                // Verify that there is an app available to handle the email intent
                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
                boolean isIntentSafe = activities.size() > 0;

                // Start the activity to compose the email if an app is available
                if (isIntentSafe) {
                    startActivity(intent);
                } else {
                    // Handle the case where no email app is available
                    Toast.makeText(getApplicationContext(), "No email app found", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data.getData()!=null)
        {
            Uri sfile=data.getData();
            binding.profileImage.setImageURI(sfile);
            final StorageReference reference= storage.getReference().child("profile_pic")
                .child(FirebaseAuth.getInstance().getUid());

            reference.putFile(sfile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).
                                    child("Profile_pic").setValue(uri.toString());
                        }
                    });
                }
            });
        }
    }
}