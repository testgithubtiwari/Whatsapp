package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.whatsapp.Models.users;
import com.example.whatsapp.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mauth;
    ActivityRegisterBinding binding;
    private FirebaseDatabase database;
    private ProgressDialog pd;
    private String name,email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        mauth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        getSupportActionBar().hide();

        pd=new ProgressDialog(this);
        pd.setTitle("Creating Account");
        pd.setMessage("We're creating your account. please wait!");

        binding.btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=binding.username.getText().toString();
                email=binding.useremail.getText().toString();
                password=binding.password.getText().toString();

                if(name.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "Enter username!", Toast.LENGTH_SHORT).show();
                }
                else if(email.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "Enter e-mail id", Toast.LENGTH_SHORT).show();
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(RegisterActivity.this, "Enter valid e-mail", Toast.LENGTH_SHORT).show();
                }
                else if(password.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                }
                else if (!isValidPassword(password)) {
                    Toast.makeText(RegisterActivity.this, "Password should be of more than 6 digits and " +
                            "it contains numbers,special characters and alphabets!", Toast.LENGTH_SHORT).show();
                }
                else {
                    pd.show();
                    mauth.createUserWithEmailAndPassword(email,password).
                            addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {
                                        users Users=new users(name,email,password);
                                        String id=task.getResult().getUser().getUid();

                                        database.getReference().child("Users").child(id).setValue(Users);
                                        Toast.makeText(RegisterActivity.this, "SignUp Successful!", Toast.LENGTH_SHORT).show();
                                        pd.dismiss();
                                    }else {
                                        Toast.makeText(RegisterActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                        pd.dismiss();
                                    }
                                }
                            });
                }
            }
        });

        binding.txtalreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
            }
        });
    }

    private boolean isValidPassword(String password) {
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password.matches(passwordPattern);
    }
}