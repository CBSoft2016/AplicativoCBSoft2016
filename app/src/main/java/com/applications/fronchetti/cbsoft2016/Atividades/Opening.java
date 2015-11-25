package com.applications.fronchetti.cbsoft2016.Atividades;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.CountDownTimer;

import com.applications.fronchetti.cbsoft2016.R;

public class Opening extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);

        new CountDownTimer(500, 1000) {
            public void onFinish() {
                Intent startActivity = new Intent(Opening.this, Login.class);
                startActivity(startActivity);
                finish();
            }

            public void onTick(long millisUntilFinished) {
            }

        }.start();
    }
}
