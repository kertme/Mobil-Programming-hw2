package com.example.week5test;

import java.util.ArrayList;

public class User {
    private ArrayList<Model> users = new ArrayList<>();


    public User() {
        Model m1 = new Model("admin","1234",R.drawable.img);
        Model m2 = new Model("testuser","testpw111",R.drawable.img);
        Model m3 = new Model("1","1",R.drawable.img);
        users.add(m1);
        users.add(m2);
        users.add(m3);
    }

    public ArrayList<Model> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<Model> users) {
        this.users = users;
    }

}
