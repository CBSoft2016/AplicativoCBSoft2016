package com.applications.fronchetti.cbsoft2016.Atividades;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.CountDownTimer;
import android.widget.ProgressBar;

import com.applications.fronchetti.cbsoft2016.R;

public class Opening extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        new CountDownTimer(5000, 500) {
            public void onFinish() {
                Intent startActivity = new Intent(Opening.this, Login.class);
                startActivity(startActivity);
                progressBar.setProgress(0);
                finish();
            }

            public void onTick(long millisUntilFinished) {
                int progress = (int) (millisUntilFinished/100);
                System.out.println(progress);
                progressBar.setProgress(progress);
            }

        }.start();
    }
}
