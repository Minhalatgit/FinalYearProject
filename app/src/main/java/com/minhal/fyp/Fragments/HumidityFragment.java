package com.minhal.fyp.Fragments;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.events.DecoEvent;
import com.minhal.fyp.Model.SensorData;
import com.minhal.fyp.R;

import static android.support.constraint.Constraints.TAG;

public class HumidityFragment extends Fragment {

    private ProgressDialog progressDialog;
    TextView tvHumid,tvTemp;
    SensorData sensorData;
    DecoView arcView,arcView2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view=inflater.inflate(R.layout.fragment_humidity, container, false);

        progressDialog = ProgressDialog.show(this.getContext(), "Loading", "Please wait...", true);
        progressDialog.setCancelable(false);
        progressDialog.show();

        arcView = view.findViewById(R.id.dynamicArcView);
        arcView2 = view.findViewById(R.id.dynamicArcView2);
        tvHumid=view.findViewById(R.id.tvHumid);
        tvTemp=view.findViewById(R.id.tvTemp);

        // Create background track for humidity
        arcView.addSeries(new SeriesItem.Builder(Color.argb(255, 218, 218, 218))
                .setRange(0, 100, 100)
                .setInitialVisibility(true)
                .setLineWidth(15f)
                .build());
        // Create background track for temperature
        arcView2.addSeries(new SeriesItem.Builder(Color.argb(255, 218, 218, 218))
                .setRange(0, 100, 100)
                .setInitialVisibility(true)
                .setLineWidth(15f)
                .build());

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("sensor/dht/");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sensorData=dataSnapshot.getValue(SensorData.class);
                Double temp=sensorData.getTemperature();
                Double humid=sensorData.getHumidity();

                tvTemp.setText(temp.toString()+" \u2109");
                tvHumid.setText(humid.toString()+"%");
                showDataInDecoView(humid.intValue(),temp.intValue());
                progressDialog.dismiss();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("onCancelled","Failed");
            }
        });

        return view;
    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "A";
            String description ="Temperature is above reading!";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("1", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = this.getContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void showDataInDecoView(int humid,int temp){
        //Create data series track for humidity
        SeriesItem seriesItem1 = new SeriesItem.Builder(Color.argb(255, 150, 209, 243))
                .setRange(0, 100, humid)
                .setLineWidth(15f)
                .build();

        //Create data series track for temperature
        SeriesItem seriesItem2 = new SeriesItem.Builder(Color.argb(255, 176, 31, 74))
                .setRange(0, 100, temp)
                .setLineWidth(15f)
                .build();

        //listeners for data change

        arcView.addSeries(seriesItem1);
        arcView2.addSeries(seriesItem2);
//        int series1Index = arcView.addSeries(seriesItem1);
//        int series2Index = arcView2.addSeries(seriesItem2);
//
//        arcView.addEvent(new DecoEvent.Builder(DecoEvent.EventType.EVENT_SHOW, true)
//                .setDelay(30)
//                .setDuration(30)
//                .build());
//
//        //arcView.addEvent(new DecoEvent.Builder(25).setIndex(series1Index).setDelay(4000).build());
//        arcView.addEvent(new DecoEvent.Builder(humid.intValue()).setIndex(series1Index).setDelay(500).build());
//        //arcView.addEvent(new DecoEvent.Builder(10).setIndex(series1Index).setDelay(12000).build());

    }

}
