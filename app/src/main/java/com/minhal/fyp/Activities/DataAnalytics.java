package com.minhal.fyp.Activities;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.minhal.fyp.Model.PIR;
import com.minhal.fyp.Model.SensorData;
import com.minhal.fyp.R;

import java.util.ArrayList;

public class DataAnalytics extends AppCompatActivity {

    GraphView graph;
    ArrayList<Integer> detectList;
    ArrayList<String> timeList;
    PIR pirSensor;
    LineGraphSeries<DataPoint> series;
    DataPoint point;
    Integer x=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_analytics);
        detectList=new ArrayList<>();
        graph= findViewById(R.id.graph);

        graph.setVisibility(View.VISIBLE);
        Viewport viewport = graph.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setMinY(0);
        viewport.setMaxY(100);
        viewport.setMinX(0);
        viewport.setMaxX(100);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("sensor/pir/");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pirSensor = dataSnapshot.getValue(PIR.class);
                Log.d("detect", pirSensor.getDetected().toString());
                Log.d("detect", pirSensor.getTime());
                Integer detect=pirSensor.getDetected();
                detectList.add(detect);
                series=new LineGraphSeries<>();

                for (Integer i:detectList) {
                   point = new DataPoint(i, i);
                    series.appendData(point,true,100);
                }
                graph.addSeries(series);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("onCancelled", "Failed");
            }
        });

    }
}
