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
    private LineGraphSeries<DataPoint> mSeries2;
    private double graph2LastXValue = 0;
    GraphView graph2;
    Scanner scanner;
    String filename = "shoulderpress.txt";
    static String numbers = "";
    Double progressions[];

    public String getoutput(){
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return numbers;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plancheprogression);

        graph2 = findViewById(R.id.graph2);
        mSeries2 = new LineGraphSeries<>();
        mSeries2.setDrawBackground(true);
        graph2.addSeries(mSeries2);
        graph2.getViewport().setXAxisBoundsManual(true);
        graph2.getViewport().setMinX(0);
        graph2.getViewport().setMaxX(10);
        scanner = new Scanner(getoutput()).useDelimiter(" ");
        for(int i=0;i<10;i++) {
            if (scanner.hasNext()) {
                progressions[i] = Double.parseDouble(scanner.next());
            } else {
                if(i>0) progressions[i] = progressions[i-1];
                else progressions[i] = 0.0;
            }
        }
    }

    private void addEntry() {
        if (scanner.hasNext()) {
            mSeries2.appendData(new DataPoint(graph2LastXValue++, progressions[(int)graph2LastXValue-1]), false, 10);
        } else {
            mSeries2.appendData(new DataPoint(graph2LastXValue++, progressions[(int)graph2LastXValue-1]), false, 10);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mTimer1 = new Runnable() {
            @Override
            public void run() {
                addEntry();
                if(graph2LastXValue==11) {graph2LastXValue=0;graph2.removeAllSeries();mSeries2 = new LineGraphSeries<>();graph2.addSeries(mSeries2);scanner.reset();
                    scanner = new Scanner(getoutput()).useDelimiter(" ");}
                mHandler.postDelayed(this, 1000);
            }
        };
        mHandler.postDelayed(mTimer1, 1000);
    }

    @Override
    public void onPause() {
        mHandler.removeCallbacks(mTimer1);
        super.onPause();
    }
}