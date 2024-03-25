package com.app.musicplayer;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.app.musicplayer.Activitys.Flutter_Activity;
import com.app.musicplayer.Adapters.Song_List_View_Holder;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;


public class MainActivity extends Flutter_Activity {

    RecyclerView Song_List_view;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Song_List_view = findViewById(R.id.Song_list);

        Song_List_view.setLayoutManager(new LinearLayoutManager(this));

        context = getApplicationContext();

        Permission(this);



    }

    public void Permission(Context context) {

        Dexter.withContext(context).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                Fetch_Songs songs = new Fetch_Songs(context);

                Song_List_View_Holder list = new Song_List_View_Holder(context, songs.Load_Songs());
                Song_List_view.setAdapter(list);

            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Toast.makeText(context, "Please allow the permission to load songs", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();

    }


}
