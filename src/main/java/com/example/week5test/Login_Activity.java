package com.example.week5test;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Login_Activity extends AppCompatActivity {
    /**Userları tek yerden çağırmayı düzenle*/
    SharedPreferences sharedpreferences;
    int counter = 3;
    boolean login = false;
    ArrayList<Model> users = new ArrayList<>();
    private User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText username = (EditText) findViewById(R.id.usernameText);
        final EditText password = (EditText) findViewById(R.id.passwordText);
        final Button  loginButton = (Button) findViewById(R.id.loginButton);
        final TextView attemptCount = findViewById(R.id.countText);


        attemptCount.setText(Integer.toString(counter));

        users = user.getUsers();



        View.OnClickListener test = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Model test : users){
                    if(username.getText().toString().equals(test.getUsername()) && password.getText().toString().equals(test.getPassword()))
                        login = true;
                }
                if(login == true){

                    sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("usernameKey", username.getText().toString());
                    editor.commit();

                    Intent intent = new Intent(Login_Activity.this, Lobby_Activity.class);
                    startActivity(intent);
                }
                else{
                    counter--;
                    attemptCount.setText(Integer.toString(counter));
                    Toast.makeText(getApplicationContext(),"Your username or password is wrong", Toast.LENGTH_SHORT).show();
                    if (counter == 0){
                        finish();
                        Toast.makeText(getApplicationContext(),"You have entered incorrectly 3 times", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };
        loginButton.setOnClickListener(test);
    }
}
