package com.example.whatsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp.ChatDetailActivity;
import com.example.whatsapp.Models.Message;
import com.example.whatsapp.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter{

    ArrayList<Message> messages;
    Context context;
    String recId;

    int SENDER_TYPE_VIEW=1;
    int RCEIVER_TYPE_VIEW=2;



    public ChatAdapter(ArrayList<Message> messages, Context context) {
        this.messages = messages;
        this.context = context;
    }

    public ChatAdapter(ArrayList<Message> messages, Context context, String recId) {
        this.messages = messages;
        this.context = context;
        this.recId = recId;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==SENDER_TYPE_VIEW)
        {
             View view= LayoutInflater.from(context).inflate(R.layout.sample_sender,parent,false);
             return new senderViewHolder(view);
        }else {
            View view= LayoutInflater.from(context).inflate(R.layout.sample_receiver,parent,false);
            return new RecieverViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (messages.get(position).getuId().equals(FirebaseAuth.getInstance().getUid())) {
            return SENDER_TYPE_VIEW;
        } else {
            return RCEIVER_TYPE_VIEW;
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message=messages.get(position);
        if(holder.getClass()==senderViewHolder.class)
        {
            ((senderViewHolder)holder).sendermessage.setText(message.getMessage());
        }
        else {
            ((RecieverViewHolder)holder).receivermeaage.setText(message.getMessage());
        }
    }


    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class RecieverViewHolder extends RecyclerView.ViewHolder{

        TextView receivermeaage,receiveTime;

        public RecieverViewHolder(@NonNull View itemView) {
            super(itemView);

            receivermeaage=itemView.findViewById(R.id.receivetext);
            receiveTime=itemView.findViewById(R.id.receiverTime);

        }
    }

    public class senderViewHolder extends RecyclerView.ViewHolder{
        TextView sendermessage,sendertime;

        public senderViewHolder(@NonNull View itemView) {
            super(itemView);

            sendermessage=itemView.findViewById(R.id.sender_text);
            sendertime=itemView.findViewById(R.id.snedertime);


        }
    }
}
