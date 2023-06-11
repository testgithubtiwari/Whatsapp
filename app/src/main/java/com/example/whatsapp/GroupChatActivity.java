package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.whatsapp.Adapters.ChatAdapter;
import com.example.whatsapp.Models.Message;
import com.example.whatsapp.databinding.ActivityChatDetailBinding;
import com.example.whatsapp.databinding.ActivityGroupChatBinding;
import com.example.whatsapp.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class GroupChatActivity extends AppCompatActivity {

    ActivityGroupChatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityGroupChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);

            }
        });

        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        final ArrayList<Message> messages=new ArrayList<>();

        final String senderId= FirebaseAuth.getInstance().getUid();
        binding.username.setText("Group Chat");

        final ChatAdapter chatAdapter=new ChatAdapter(messages,this);
        binding.groupchatRecyclerview.setAdapter(chatAdapter);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.groupchatRecyclerview.setLayoutManager(layoutManager);

        database.getReference().child("Group Chat")
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                messages.clear();
                                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                                {
                                    Message message=dataSnapshot.getValue(Message.class);
                                    messages.add(message);
                                }
                                chatAdapter.notifyDataSetChanged();
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(GroupChatActivity.this,error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String message=binding.enterMessage.getText().toString();
                final Message message1=new Message(senderId,message);
                message1.setTimesatmp(new Date().getTime());

                binding.enterMessage.setText("");
                database.getReference().child("Group Chats").push().setValue(message1)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(GroupChatActivity.this, "Message Send!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}