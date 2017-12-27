package com.marcoscg.xmassnowsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.marcoscg.xmassnow.XmasSnow;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        XmasSnow.on(this)
                .belowActionBar(true)
                .start();
    }
}
