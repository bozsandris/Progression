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
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by bozsi on 10/31/2018.
 */

public class Planche extends AppCompatActivity {

    String filename = "planche.txt",filename2 = "shoulderpress.txt";
    String numbers = "",numbers2="";
    FileOutputStream outputStream,outputStream2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.planche);
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
        final EditText rep6 = findViewById(R.id.rep6);
        final Button save = findViewById(R.id.button);
        final Button load = findViewById(R.id.button2);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                File file = new File(getApplicationContext().getFilesDir(),filename2);
                    try {
                        outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                        numbers = weight.getText().toString() + " " + rep.getText().toString() + " "
                                + weight2.getText().toString() + " " + rep2.getText().toString()
                                + " "
                                + weight3.getText().toString() + " " + rep3.getText().toString()
                                + " "
                                + weight4.getText().toString() + " " + rep4.getText().toString() + " "
                                + weight5.getText().toString() + " " + rep5.getText().toString() + " "
                                + rep6.getText().toString() + " ";
                        outputStream.write(numbers.getBytes());
                        numbers = "";
                        outputStream.close();
                        if(file.exists()){
                            FileInputStream inputStream = openFileInput(filename2);
                            int n;
                            try {
                                while ((n = inputStream.read()) != -1) {
                                  numbers2 += (char)n;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            inputStream.close();
                        }
                        outputStream2 = openFileOutput(filename2, Context.MODE_PRIVATE);
                        if(numbers2.length()>=20) numbers2="";
                        numbers2 += weight5.getText().toString() + " ";
                        outputStream2.write(numbers2.getBytes());
                        numbers2 = "";
                        outputStream2.close();
                        Toast.makeText(getApplicationContext(),"Progression saved!",Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        });
        load.setOnClickListener(new View.OnClickListener(){
            public void  onClick(View v){
                weight.setText("type");
                rep.setText("0");
                weight2.setText("type");
                rep2.setText("0");
                weight3.setText("0");
                rep3.setText("0");
                weight4.setText("0");
                rep4.setText("0");
                weight5.setText("0");
                rep5.setText("0");
                rep6.setText("0");
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
                            if(scanner.hasNext()) rep6.setText(scanner.next());
                            numbers="";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}