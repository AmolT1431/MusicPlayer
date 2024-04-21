package com.app.musicplayer.Activitys;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.app.musicplayer.MainActivity;
import com.app.musicplayer.R;


public class flash_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flash_activity);

        //For flash Activity
        new Handler().postDelayed(() -> {
            Intent i=new Intent(flash_activity.this, MainActivity.class);
            startActivity(i);
            finish();

        },1500);


    }
}