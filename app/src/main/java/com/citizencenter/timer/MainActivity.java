package com.citizencenter.timer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    TextView textView;
    Button button;
    CountDownTimer countDownTimer;
    boolean isRunning;
    long timeLeftInMilliSeconds = 600000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStop();
            }
        });
    }

    private void startStop()
    {
        if (isRunning)
            stopTimer();
        else
            startTimer();
    }

    private void startTimer()
    {
        countDownTimer = new CountDownTimer(timeLeftInMilliSeconds,1000)
        {
            @Override
            public void onTick(long l)
            {
                timeLeftInMilliSeconds = l;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();
        button.setText("pause");
    }

    private void updateTimer()
    {
        int minutes = (int)timeLeftInMilliSeconds/60000;
        int seconds = (int)timeLeftInMilliSeconds%60000/1000;

        String timeLeftText;

        timeLeftText = ""+minutes;
        timeLeftText += ":";
        if (seconds < 10)timeLeftText += "0";
        timeLeftText += seconds;

        textView.setText(timeLeftText);
    }

    private void stopTimer()
    {
        countDownTimer.cancel();
        button.setText("pause");
        isRunning = false;
    }
}
