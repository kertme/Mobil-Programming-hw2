package com.example.week5test;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Userlist_Activity extends Activity {
    /**Userları tek yerden çağırmayı düzenle*/
    ArrayList<Model> users = new ArrayList<>();
    RecyclerView mRecyclerView;
    MyAdapter myAdapter;
    private User user = new User();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);

        users = user.getUsers();


        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new MyAdapter(this, users);
        mRecyclerView.setAdapter(myAdapter);

    }

}
