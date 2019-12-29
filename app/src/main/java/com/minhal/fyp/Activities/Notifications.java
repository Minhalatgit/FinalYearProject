package com.minhal.fyp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.minhal.fyp.Adapters.MyAdapter;
import com.minhal.fyp.Model.NotificationList;
import com.minhal.fyp.R;

import java.util.ArrayList;

public class Notifications extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    ArrayList<NotificationList> notificationList=new ArrayList<NotificationList>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        recyclerView=findViewById(R.id.rvNoti);
        recyclerView.setHasFixedSize(true);
        layoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        notificationList.add(new NotificationList("Hello","Hey my name is minhal"));
        notificationList.add(new NotificationList("Hello","Hey my name is minhal"));
        notificationList.add(new NotificationList("Hello","Hey my name is minhal"));
        notificationList.add(new NotificationList("Hello","Hey my name is minhal"));
        notificationList.add(new NotificationList("Hello","Hey my name is minhal"));
        notificationList.add(new NotificationList("Hello","Hey my name is minhal"));

        adapter=new MyAdapter(notificationList);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,HomeActivity.class));
    }
}
