package com.example.whatsapp.Adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position){
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete")
                        .setMessage("Are you sure you want to delete this message?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                String senderRoom = FirebaseAuth.getInstance().getUid() + recId;
                                database.getReference().child("chats").child(senderRoom)
                                        .child(messages.get(position).getMessageId()).setValue(null);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
                return false;
            }
        });

        Message message = messages.get(position);
        if (holder instanceof senderViewHolder) {
            ((senderViewHolder) holder).sendermessage.setText(message.getMessage());
            Date date=new Date(message.getTimesatmp());
            SimpleDateFormat  simpleDateFormat=new SimpleDateFormat("h:mm a");
            String strDate=simpleDateFormat.format(date);
            ((senderViewHolder)holder).sendertime.setText(strDate);


        } else if (holder instanceof RecieverViewHolder) {
            ((RecieverViewHolder) holder).receivermeaage.setText(message.getMessage());
            Date date=new Date(message.getTimesatmp());
            SimpleDateFormat  simpleDateFormat=new SimpleDateFormat("h:mm a");
            String strDate=simpleDateFormat.format(date);
            ((RecieverViewHolder)holder).receiveTime.setText(strDate);
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
