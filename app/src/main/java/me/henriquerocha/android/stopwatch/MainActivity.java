package me.henriquerocha.android.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends Activity {

    private long base;
    private boolean started;
    private long stopMillis;
    private long initialBase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Chronometer chronometer = findViewById(R.id.chronometer);

        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (started) {
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    long startMillis = elapsedRealtime - (elapsedRealtime - stopMillis);
                    long newBase = initialBase + (startMillis - stopMillis);
                    chronometer.setBase(newBase);
                } else {
                    initialBase = SystemClock.elapsedRealtime();
                    chronometer.setBase(initialBase);
                }
                chronometer.start();
                started = true;
            }
        });

        Button stopButton = findViewById(R.id.stopButton);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopMillis = SystemClock.elapsedRealtime();
                chronometer.stop();
            }
        });

        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialBase = SystemClock.elapsedRealtime();
                chronometer.setBase(initialBase);
            }
        });
    }
}
