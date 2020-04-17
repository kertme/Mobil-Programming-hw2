package com.example.week5test;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import java.util.List;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static java.lang.Math.abs;

public class Sensor_Activity extends AppCompatActivity implements SensorEventListener {

    private Sensor senAccelerometer,senLight;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z,last_l = 0;
    private static final int THRESHOLD = 600;
    private TextView tx,ty,tz,tl,tl2,tl3,tl4,tl5;

    SensorManager smm;
    List<Sensor> sensor;
    ListView lv;

    ArrayAdapter<Sensor> adapter;
    ArrayAdapter<Sensor> adapter2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        tx = findViewById(R.id.tx);
        ty = findViewById(R.id.ty);
        tz = findViewById(R.id.tz);
        tl = findViewById(R.id.tl);
        tl2 = findViewById(R.id.tl2);
        tl3 = findViewById(R.id.tl3);
        tl4 = findViewById(R.id.tl4);
        tl5 = findViewById(R.id.tl5);

        smm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        lv = (ListView) findViewById (R.id.listView1);
        sensor = smm.getSensorList(Sensor.TYPE_ALL);

        adapter = new ArrayAdapter<Sensor>(this, android.R.layout.simple_list_item_1,  sensor);

        adapter2 = new ArrayAdapter<Sensor>(this, android.R.layout.simple_list_item_1,  sensor){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                textView.setTextColor(Color.WHITE);
                return textView;
            }
        };
        lv.setAdapter(adapter);


        senAccelerometer = smm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        smm.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);

        senLight = smm.getDefaultSensor(Sensor.TYPE_LIGHT);
        smm.registerListener(this, senLight , SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            tx.setText(String.valueOf(x));
            ty.setText(String.valueOf(y));
            tz.setText(String.valueOf(z));


            if(abs(x-last_x) > 0.5 || abs(y-last_y) > 0.5 || abs(z-last_z) > 0.5){//hareket varsa if'i
                long curTime = System.currentTimeMillis();
                lastUpdate = curTime;
                last_x = x;
                last_y = y;
                last_z = z;
            }
            else{//hareket yoksa
                long curTime = System.currentTimeMillis();
                if(curTime - lastUpdate > 5000){
                    Toast.makeText(this, "App Closing..", Toast.LENGTH_SHORT).show();
                    //finishAffinity();
                }

            }
        }

        if (mySensor.getType() == Sensor.TYPE_LIGHT){
            tl.setText(String.valueOf(sensorEvent.values[0]));
            if (sensorEvent.values[0] > 10 && tx.getCurrentTextColor() != Color.BLACK){
                getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                tx.setTextColor(Color.BLACK);
                ty.setTextColor(Color.BLACK);
                tz.setTextColor(Color.BLACK);
                tl.setTextColor(Color.BLACK);
                tl2.setTextColor(Color.BLACK);
                tl3.setTextColor(Color.BLACK);
                tl4.setTextColor(Color.BLACK);
                tl5.setTextColor(Color.BLACK);
                lv.setAdapter(adapter);
            }
            else if(sensorEvent.values[0] < 10 && tx.getCurrentTextColor() != Color.WHITE){
                getWindow().getDecorView().setBackgroundColor(Color.BLACK);
                tx.setTextColor(Color.WHITE);
                ty.setTextColor(Color.WHITE);
                tz.setTextColor(Color.WHITE);
                tl.setTextColor(Color.WHITE);
                tl2.setTextColor(Color.WHITE);
                tl3.setTextColor(Color.WHITE);
                tl4.setTextColor(Color.WHITE);
                tl5.setTextColor(Color.WHITE);
                lv.setAdapter(adapter2);
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

