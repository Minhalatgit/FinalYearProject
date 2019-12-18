package com.minhal.fyp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.minhal.fyp.Model.SensorData;
import com.minhal.fyp.R;

import static android.support.constraint.Constraints.TAG;

public class HumidityFragment extends Fragment {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("sensor/dht/");
    TextView temperature,humidity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_humidity, container, false);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                SensorData sensorData=dataSnapshot.getValue(SensorData.class);

                temperature=view.findViewById(R.id.temperature);
                humidity=view.findViewById(R.id.humidity);
                temperature.setText(sensorData.getTemperature().toString());
                humidity.setText(sensorData.getHumidity().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("onCancelled","Failed");
            }
        });
        return view;
    }
}
