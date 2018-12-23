package com.example.bozsi.progression;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Plancheprogression extends AppCompatActivity {
    private final Handler mHandler = new Handler();
    private Runnable mTimer1;
    private Runnable mTimer2;
    private LineGraphSeries<DataPoint> mSeries1;
    private LineGraphSeries<DataPoint> mSeries2;
    private double graph2LastXValue = 0;
    String filename = "shoulderpress.txt";
    String numbers = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plancheprogression);

        GraphView graph = findViewById(R.id.graph);
        mSeries1 = new LineGraphSeries<>();
        graph.addSeries(mSeries1);

        GraphView graph2 = findViewById(R.id.graph2);
        mSeries2 = new LineGraphSeries<>();
        graph2.addSeries(mSeries2);
        graph2.getViewport().setXAxisBoundsManual(true);
        graph2.getViewport().setMinX(0);
        graph2.getViewport().setMaxX(40);
    }

    @Override
    public void onResume() {
        super.onResume();
        mTimer1 = new Runnable() {
            @Override
            public void run() {
                mSeries1=mSeries2;
                mHandler.postDelayed(this, 300);
            }
        };
        mHandler.postDelayed(mTimer1, 300);

        mTimer2 = new Runnable() {
            @Override
            public void run() {
                graph2LastXValue += 1d;
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
                        for(int i=0;i<10;i++){
                        if(scanner.hasNext()) {mSeries2.appendData(new DataPoint(graph2LastXValue,Double.parseDouble(scanner.next())),true,10);graph2LastXValue++;}
                        else{mSeries2.appendData(new DataPoint(graph2LastXValue,0),true,10);graph2LastXValue++;}}
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                mHandler.postDelayed(this, 200);
            }
        };
        mHandler.postDelayed(mTimer2, 1000);
    }

    @Override
    public void onPause() {
        mHandler.removeCallbacks(mTimer1);
        mHandler.removeCallbacks(mTimer2);
        super.onPause();
    }
}