package com.minhal.fyp.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.minhal.fyp.Fragments.VideoFragment;
import com.minhal.fyp.Fragments.HumidityFragment;
import com.minhal.fyp.Fragments.ProfileFragment;
import com.minhal.fyp.R;

public class StatusActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //https://www.youtube.com/watch?v=bjYstsO1PgI&t=19s
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        String type = getIntent().getStringExtra("FROM");

        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer=findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_draw_open,R.string.navigation_draw_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //if (savedInstanceState == null) {
            if(type!=null && type.equals("Notification")){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new VideoFragment()).commit();
            }
            else{
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HumidityFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_humidity);
            }
        //}
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_humidity:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HumidityFragment()).commit();
                break;
            case R.id.nav_video:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new VideoFragment()).commit();
                break;
            case R.id.nav_others:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }
}
