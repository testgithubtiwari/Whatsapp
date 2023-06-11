package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.whatsapp.databinding.ActivityLoginBinding;
import com.example.whatsapp.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {

    ActivitySettingsBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySettingsBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        getSupportActionBar().hide();
    }
}