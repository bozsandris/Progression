package com.example.bozsi.progression;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Timer extends AppCompatActivity {

    TextView timerTextView;
    ProgressBar progressBar;
    long startTime = 0;
    long millis;
    int beforeminutes;
    private Vibrator myVib;

    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            if(minutes>beforeminutes) myVib.vibrate(VibrationEffect.createOneShot(50,1));
            beforeminutes = minutes;
            seconds = seconds % 60;
            progressBar.setSecondaryProgress(seconds);
            timerTextView.setText(String.format("%d:%02d", minutes, seconds));

            timerHandler.postDelayed(this, 500);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);
        this.overridePendingTransition(R.anim.righttoleft,R.anim.stayincenter);
        myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
        timerTextView = findViewById(R.id.timerTextView);
        progressBar = findViewById(R.id.progressBar);
        final Button b = findViewById(R.id.button);
        final Button reset = findViewById(R.id.button3);
        b.setText("start");
        b.setBackgroundColor(Color.GREEN);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                if (b.getText().equals("stop")) {
                    timerHandler.removeCallbacks(timerRunnable);
                    b.setText("start");
                    b.setBackgroundColor(Color.GREEN);
                } else {
                    if(startTime==0) startTime = System.currentTimeMillis();
                    else startTime = System.currentTimeMillis()-millis;
                    timerHandler.postDelayed(timerRunnable, 500);
                    b.setBackgroundColor(Color.BLUE);
                    reset.setError(null);
                    b.setText("stop");
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button reset = (Button) v;
                if(startTime==0){
                    reset.setError("There's nothing to reset!");return;
                }
                if (b.getText().equals("start")) {
                    startTime = 0;
                    myVib.vibrate(VibrationEffect.createOneShot(50,1));
                }
                else if (b.getText().equals("stop")) {
                    timerHandler.removeCallbacks(timerRunnable);
                    b.setText("start");
                    b.setBackgroundColor(Color.GREEN);
                    myVib.vibrate(VibrationEffect.createOneShot(50,1));
                    startTime = 0;
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
        Button b = findViewById(R.id.button);
        b.setText("start");
        b.setBackgroundColor(Color.GREEN);
    }

}