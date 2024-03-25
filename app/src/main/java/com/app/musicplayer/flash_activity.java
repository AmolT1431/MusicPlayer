package com.app.musicplayer;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class flash_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flash_activity);

        //For flash Activity
        new Handler().postDelayed(() -> {
            Intent i=new Intent(flash_activity.this,MainActivity.class);
            startActivity(i);
            finish();

        },1500);


    }
}