package com.example.bozsi.progression;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Routine extends AppCompatActivity {
    String[] spinnerlist ={"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    String filename = "";
    String outputtext="";
    FileOutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.routine);
        final Spinner spinner = findViewById(R.id.spinner);
        final EditText text = findViewById(R.id.text);
        final Button save = findViewById(R.id.button);
        final Button load = findViewById(R.id.button2);
        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerlist);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                filename = spinner.getSelectedItem().toString();
                File file = new File(getApplicationContext().getFilesDir(),filename);
                if (file.exists()) {
                    try {
                        outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                        outputStream.write(text.getText().toString().getBytes());
                        outputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else
                {
                    try {
                        outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                        outputStream.write(text.getText().toString().getBytes());
                        outputStream.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(getApplicationContext(),"Routine description saved!",Toast.LENGTH_SHORT).show();
            }
        });
        load.setOnClickListener(new View.OnClickListener(){
            public void  onClick(View v){
                filename = spinner.getSelectedItem().toString();
                File file = getApplicationContext().getFileStreamPath(filename);
                if(file.exists()) {
                    FileInputStream inputStream;
                    String str = "";
                    StringBuffer input=new StringBuffer();
                    try {
                        inputStream = openFileInput(filename);
                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                            while ((str = reader.readLine())!=null) {
                                input.append(str+"\n");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        text.setText(input);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                filename = spinner.getSelectedItem().toString();
                File file = getApplicationContext().getFileStreamPath(filename);
                if(file.exists()) {
                    FileInputStream inputStream;
                    String str = "";
                    StringBuffer input=new StringBuffer();
                    try {
                        inputStream = openFileInput(filename);
                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                            while ((str = reader.readLine())!=null) {
                                input.append(str+"\n");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        text.setText(input);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                else text.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
