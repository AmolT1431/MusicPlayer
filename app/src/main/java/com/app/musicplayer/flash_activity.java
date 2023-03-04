package com.app.musicplayer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class flash_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flash_activity);
        // For hide Actionbar
        getSupportActionBar().hide();
        //For flash Activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(flash_activity.this,MainActivity.class);
                startActivity(i);

            }
        },1500);


    }
}