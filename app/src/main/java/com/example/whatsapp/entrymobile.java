package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.whatsapp.databinding.ActivityEntrymobileBinding;
import com.example.whatsapp.databinding.ActivityLoginBinding;
import com.example.whatsapp.databinding.ActivityMainBinding;


public class entrymobile extends AppCompatActivity {

    ActivityEntrymobileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityEntrymobileBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        binding.ccp.registerCarrierNumberEditText(binding.phone);

        binding.btngetOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ManageOTp.class);
                intent.putExtra("mobile",binding.ccp.getFullNumberWithPlus().replace(" ",""));
                startActivity(intent);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
            }
        });

    }
}