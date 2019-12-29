package com.minhal.fyp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.minhal.fyp.Model.NotificationList;
import com.minhal.fyp.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
     private ArrayList<NotificationList> notificationList;

     public MyAdapter(ArrayList<NotificationList> notificationList){
         this.notificationList=notificationList;
     }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.noti_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

         NotificationList noti=notificationList.get(i);
         myViewHolder.tvtitle.setText(noti.getTitle());
         myViewHolder.tvDescription.setText(noti.getDescription());
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
         TextView tvtitle,tvDescription;
         public MyViewHolder(View itemView){
             super(itemView);
             tvtitle=itemView.findViewById(R.id.tvTitle);
             tvDescription=itemView.findViewById(R.id.tvDescription);
         }
    }
}


