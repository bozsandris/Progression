package com.example.bozsi.progression;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by bozsi on 10/31/2018.
 */

public class Chest  extends AppCompatActivity {

    String filename = "chest.txt";
    String numbers = "";
    FileOutputStream outputStream;
    Switch change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.chest);
        this.overridePendingTransition(R.anim.righttoleft,R.anim.stayincenter);
        final EditText weight = findViewById(R.id.weight);
        final EditText rep = findViewById(R.id.rep);
        final EditText weight2 =  findViewById(R.id.weight2);
        final EditText rep2 = findViewById(R.id.rep2);
        final EditText weight3 = findViewById(R.id.weight3);
        final EditText rep3 = findViewById(R.id.rep3);
        final EditText weight4 = findViewById(R.id.weight4);
        final EditText rep4 = findViewById(R.id.rep4);
        final EditText weight5 = findViewById(R.id.weight5);
        final EditText rep5 = findViewById(R.id.rep5);
        final Button save = findViewById(R.id.button);
        final Button load = findViewById(R.id.button2);
        change = findViewById(R.id.switch2);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    try {
                        outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                        numbers = weight.getText().toString() + " " + rep.getText().toString() + " "
                                + weight2.getText().toString() + " " + rep2.getText().toString()
                                + " "
                                + weight3.getText().toString() + " " + rep3.getText().toString()
                                + " "
                                + weight4.getText().toString() + " " + rep4.getText().toString()
                                + " "
                                + weight5.getText().toString() + " " + rep5.getText().toString() + " ";
                        outputStream.write(numbers.getBytes());
                        numbers = "";
                        outputStream.close();
                        Toast.makeText(getApplicationContext(),"Progression saved!",Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        });
        load.setOnClickListener(new View.OnClickListener(){
            public void  onClick(View v){
                weight.setText("0");
                rep.setText("0");
                weight2.setText("0");
                rep2.setText("0");
                weight3.setText("0");
                rep3.setText("0");
                weight4.setText("0");
                rep4.setText("0");
                weight5.setText("0");
                rep5.setText("0");
                File file = getApplicationContext().getFileStreamPath(filename);
                if(file.exists()) {
                    FileInputStream inputStream;
                    int n;
                    try {
                        inputStream = openFileInput(filename);
                        try {
                            while ((n = inputStream.read()) != -1) {
                                numbers += (char)n;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        inputStream.close();
                        Scanner scanner = new Scanner(numbers).useDelimiter(" ");
                        if(scanner.hasNext()) weight.setText(scanner.next());
                        if(scanner.hasNext()) rep.setText(scanner.next());
                        if(scanner.hasNext()) weight2.setText(scanner.next());
                        if(scanner.hasNext()) rep2.setText(scanner.next());
                        if(scanner.hasNext()) weight3.setText(scanner.next());
                        if(scanner.hasNext()) rep3.setText(scanner.next());
                        if(scanner.hasNext()) weight4.setText(scanner.next());
                        if(scanner.hasNext()) rep4.setText(scanner.next());
                        if(scanner.hasNext()) weight5.setText(scanner.next());
                        if(scanner.hasNext()) rep5.setText(scanner.next());
                        numbers="";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        change.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                { Intent intent = new Intent(getApplicationContext(),PowerDip.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void onResume(){
        super.onResume();
        change.setChecked(false);
    }
}