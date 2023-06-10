package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.whatsapp.databinding.ActivityForgotPasswordactivityBinding;
import com.example.whatsapp.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;

public class ForgotPasswordactivity extends AppCompatActivity {

    ActivityForgotPasswordactivityBinding binding;

    FirebaseAuth authProfile;
    ProgressDialog pd;
    private static final  String TAG="ForgotPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityForgotPasswordactivityBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        pd=new ProgressDialog(this);


        binding.btngetpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=binding.textverifymail.getText().toString();

                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(ForgotPasswordactivity.this, "Enter E-mail", Toast.LENGTH_SHORT).show();
                }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    Toast.makeText(ForgotPasswordactivity.this, "Enter valid-email", Toast.LENGTH_SHORT).show();
                }else
                {
                    resetPassword(email);
                }
            }
        });


    }

    private void resetPassword(String email) {
        pd.setMessage("Loading..");
        pd.show();
        authProfile=FirebaseAuth.getInstance();
        authProfile.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    pd.dismiss();
                    Toast.makeText(getApplicationContext(),"Please check your email inbox to get new password",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                    // clear stack to prevent user coming back to this activity
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }else
                {
                    try{
                        throw task.getException();
                    }catch(FirebaseAuthInvalidCredentialsException e){
                        Toast.makeText(ForgotPasswordactivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }catch (Exception e){
                        Log.e(TAG,e.getMessage());
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
                pd.dismiss();
            }
        });
    }
}