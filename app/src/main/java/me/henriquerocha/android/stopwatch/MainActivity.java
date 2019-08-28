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
                    chronometer.setBase(base);
                } else {
                    chronometer.setBase(SystemClock.elapsedRealtime());
                }
                chronometer.start();
                started = true;
            }
        });

        Button stopButton = findViewById(R.id.stopButton);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long b = chronometer.getBase();
                base = b + (SystemClock.elapsedRealtime() - b);

                chronometer.stop();
            }
        });
    }
}
