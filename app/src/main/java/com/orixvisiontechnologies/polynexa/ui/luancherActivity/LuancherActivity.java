package com.orixvisiontechnologies.polynexa.ui.luancherActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.orixvisiontechnologies.polynexa.ui.onboardActivity.OnboardActivity;
import com.orixvisiontechnologies.polynexa.R;

public class LuancherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luancher);

        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                startActivity(new Intent(LuancherActivity.this, OnboardActivity.class));
                finish();
            }
        };
        handler.postDelayed(r, 1000);

    }
}