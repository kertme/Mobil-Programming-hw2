package com.example.week5test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Note_Activity extends Activity {
    private String FILE_NAME = "example.txt";
    private String txt = ".txt";
    EditText mEditText,m2EditText;
    Button delete,save;
    ListView listView;
    private ArrayList<String> filelist = new ArrayList<String>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        mEditText = findViewById(R.id.edit_text);
        m2EditText = findViewById(R.id.edit_text2);
        delete = findViewById(R.id.button_delete);
        save = findViewById(R.id.button_save);
        listView = findViewById(R.id.listview);

        String path = getFilesDir().toString();
        File directory = new File(path);
        File[] files = directory.listFiles();
        for ( File aFile : files ){
            int j = aFile.toString().indexOf("/files") + 7;
            String subString = aFile.toString().substring(j, aFile.toString().length());
            filelist.add(subString);
        }


        //Create Adapter
        final ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,filelist);

//assign adapter to listview
        listView.setAdapter(arrayAdapter);

//add listener to listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(),"clicked item:"+i+" "+filelist.get(i).toString(),Toast.LENGTH_SHORT).show();


                String tempstring =  filelist.get(i);
                Toast.makeText(getApplicationContext(),tempstring,Toast.LENGTH_SHORT).show();
                FILE_NAME = tempstring;
                FileInputStream fis = null;

                try {
                    fis = openFileInput(FILE_NAME);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();
                    String text;

                    while ((text = br.readLine()) != null) {
                        sb.append(text).append("\n");
                    }

                    mEditText.setText(sb.toString());
                    String[] parts = FILE_NAME.split("\\.");

                    m2EditText.setText(parts[0]);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = mEditText.getText().toString();
                FileOutputStream fos = null;
                FILE_NAME = m2EditText.getText().toString() + txt;
                if(filelist.contains(FILE_NAME)){
                    deleteFile(FILE_NAME);
                    filelist.remove(FILE_NAME);
                }


                try {
                    fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                    fos.write(text.getBytes());

                    mEditText.getText().clear();
                    m2EditText.getText().clear();
                    Toast.makeText(getApplicationContext(), "Saved to " + getFilesDir() + "/" + FILE_NAME,Toast.LENGTH_LONG).show();
                    filelist.add(FILE_NAME);
                    arrayAdapter.notifyDataSetChanged();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFile(FILE_NAME);
                filelist.remove(FILE_NAME);
                mEditText.getText().clear();
                m2EditText.getText().clear();
                arrayAdapter.notifyDataSetChanged();
            }
        });

    }

}
