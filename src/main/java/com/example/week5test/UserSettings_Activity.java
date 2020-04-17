package com.example.week5test;

import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import java.util.ArrayList;
import java.util.List;
import android.widget.ArrayAdapter;

public class UserSettings_Activity extends Activity implements OnItemSelectedListener{
    /***dark mode seçeneğini ayarlamayı unutma***/
    TextView tw1;
    EditText ed1,ed2,ed3;
    Button b1;
    CheckBox cb;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static String Username,savedString;
    public static final String Sex = "sexKey";
    public static final String Age = "ageKey";
    public static final String Height = "heightKey";
    public static final String Weight = "weightKey";
    public static final String Dark = "darkKey";

    SharedPreferences sharedpreferences;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersettings);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        tw1= findViewById(R.id.textView11);
        ed1=(EditText)findViewById(R.id.editText);
        ed2=(EditText)findViewById(R.id.editText2);
        ed3=(EditText)findViewById(R.id.editText3);

        cb = (CheckBox)findViewById(R.id.checkBox);

        b1=(Button)findViewById(R.id.button);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        spinner.setOnItemSelectedListener(this);
        List<String> options = new ArrayList<String>();
        options.add("Male");
        options.add("Female");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, options);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


        Username = sharedpreferences.getString("usernameKey","");

        //String savedString = sharedpreferences.getString(Username,"");
        tw1.setText(Username);

        savedString = sharedpreferences.getString(Username+Age,"");
        ed1.setText(savedString);

        savedString = sharedpreferences.getString(Username+Height,"");
        ed2.setText(savedString);

        savedString = sharedpreferences.getString(Username+Weight,"");
        ed3.setText(savedString);

        savedString = sharedpreferences.getString(Username+Dark,"");
        if (savedString == "true")
            cb.setChecked(true);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = spinner.getSelectedItem().toString();
                String a  = ed1.getText().toString();
                String h  = ed2.getText().toString();
                String w  = ed2.getText().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(Username+Sex, s);
                editor.putString(Username+Age, a);
                editor.putString(Username+Height, h);
                editor.putString(Username+Weight, w);
                if (cb.isChecked())
                    editor.putString(Username+Dark, "true");
                else
                    editor.putString(Username+Dark, "false");

                editor.commit();
                Toast.makeText(UserSettings_Activity.this,"Saved",Toast.LENGTH_LONG).show();
                //editor.clear().commit();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}