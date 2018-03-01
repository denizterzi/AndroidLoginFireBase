package com.app.diceroid.nerede.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Alber on 04.12.2017.
 */

public class SplashActivity extends AppCompatActivity {

    private static final int splash_time=3000;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable(){
            @Override
                    public void run(){

                Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        },splash_time);
    }
}
