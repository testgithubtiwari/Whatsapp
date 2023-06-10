package com.example.whatsapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp.ChatDetailActivity;
import com.example.whatsapp.Models.users;
import com.example.whatsapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UsersAdapter extends  RecyclerView.Adapter<UsersAdapter.ViewHolder>{

    ArrayList<users> list;
    Context context;

    public UsersAdapter(ArrayList<users> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_show_user,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        users User=list.get(position);
        Picasso.get().load(User.getProfilepic()).placeholder(R.drawable.avatar3).into(holder.userimage);
        holder.username.setText(User.getUsername());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ChatDetailActivity.class);
                intent.putExtra("userid",User.getUserId());
                intent.putExtra("userprofile",User.getProfilepic());
                intent.putExtra("username",User.getUsername());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView userimage;
        TextView username,lastmessage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userimage=itemView.findViewById(R.id.profile_image);
            username=itemView.findViewById(R.id.usernameList);
            lastmessage=itemView.findViewById(R.id.lastmessage);

        }
    }
}
