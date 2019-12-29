package com.minhal.fyp.Activities;

import android.app.Notification;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.minhal.fyp.R;

public class HomeActivity extends AppCompatActivity {

    ImageView ivStatus,ivNotifications;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ivStatus=findViewById(R.id.status);
        ivNotifications=findViewById(R.id.notifications);

        ivStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,StatusActivity.class));
            }
        });
        ivNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, Notifications.class));
            }
        });
    }

}
