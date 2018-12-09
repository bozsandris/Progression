package com.example.bozsi.progression;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by bozsi on 10/31/2018.
 */

public class Triceps   extends AppCompatActivity {

    String filename = "triceps.txt";
    String numbers = "";
    FileOutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.triceps);
        final EditText weight = findViewById(R.id.weight);
        final EditText rep = findViewById(R.id.rep);
        final EditText weight2 =  findViewById(R.id.weight2);
        final EditText rep2 = findViewById(R.id.rep2);
        final Button save = findViewById(R.id.button);
        final Button load = findViewById(R.id.button2);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                File file = new File(getApplicationContext().getFilesDir(),filename);
                if (file.exists()) {
                    try {
                        outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                        numbers = weight.getText().toString() + " " + rep.getText().toString() + " "
                                + weight2.getText().toString() + " " + rep2.getText().toString()
                                + " ";
                        outputStream.write(numbers.getBytes());
                        numbers = "";
                        outputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else
                {
                    try {
                        outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                        numbers = weight.getText().toString() + " " + rep.getText().toString() + " "
                                + weight2.getText().toString() + " " + rep2.getText().toString()
                                + " ";
                        outputStream.write(numbers.getBytes());
                        numbers = "";
                        outputStream.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(getApplicationContext(),"Progression saved!",Toast.LENGTH_SHORT).show();
            }
        });
        load.setOnClickListener(new View.OnClickListener(){
            public void  onClick(View v){
                weight.setText("0");
                weight.setText("0");
                rep.setText("0");
                weight2.setText("0");
                rep2.setText("0");
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
                        numbers="";
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}