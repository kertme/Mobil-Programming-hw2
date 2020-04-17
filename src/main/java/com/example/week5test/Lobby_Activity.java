package com.example.week5test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Lobby_Activity extends Activity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        final Button email = (Button) findViewById(R.id.send_email);
        final Button userlist = (Button) findViewById(R.id.user_list);
        final Button user_settings = (Button) findViewById(R.id.user_settings);
        final Button notes = (Button) findViewById(R.id.notes);
        final Button sensor = (Button) findViewById(R.id.sensor_button);

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lobby_Activity.this, Email_Activity.class);
                startActivity(intent);
            }
        });

        userlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lobby_Activity.this, Userlist_Activity.class);
                startActivity(intent);
            }
        });

        user_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lobby_Activity.this, UserSettings_Activity.class);
                startActivity(intent);
            }
        });

        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lobby_Activity.this, Note_Activity.class);
                startActivity(intent);
            }
        });

        sensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lobby_Activity.this, Sensor_Activity.class);
                startActivity(intent);
            }
        });

    }
}

