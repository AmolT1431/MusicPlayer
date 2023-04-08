package com.app.musicplayer;


import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;


public class MainActivity extends Activity {
    ListView listView;
    Context context;
    ContentResolver contentresolver;
    Cursor cursor;

    ArrayList<String> SongNames;
    ArrayList<String> songId;

    Uri uri;

    String[] items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview);
        //getsong
        context = getApplicationContext();
        SongNames = new ArrayList<String>();
        songId = new ArrayList<String>();
        runtimepermission();


    }

    public void runtimepermission() {
        Dexter.withContext(this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                getsong();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                System.exit(1);

            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }

    //song fetching

    public void getsong() {

        contentresolver = context.getContentResolver();
        uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        cursor = contentresolver.query(uri, null, null, null, null);

        if (cursor == null) {
            Toast.makeText(this, "something is wrong", Toast.LENGTH_SHORT).show();

        } else if (!cursor.moveToFirst()) {
            Toast.makeText(this, "No song found", Toast.LENGTH_SHORT).show();
        } else {

            int Titel = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int SongId = cursor.getColumnIndex(MediaStore.Audio.Media._ID);


            do {
                String st = cursor.getString(Titel);
                String Id = cursor.getString(SongId);


                songId.add(Id);
                SongNames.add(st);

            } while (cursor.moveToNext());
        }

        items = new String[SongNames.size()];
        for (int i = 0; i < SongNames.size(); i++) {
            items[i] = SongNames.get(i);
        }

        Myadapter songs = new Myadapter();
        listView.setAdapter(songs);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String songname = (String) listView.getItemAtPosition(position);
                Intent i = new Intent(getApplicationContext(), PlayerActivity.class);
                i.putExtra("position", position);
                i.putExtra("SongName", SongNames);
                i.putExtra("songId", songId);
                startActivity(i);

            }
        });

    }

    public class Myadapter extends BaseAdapter {
        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View tview = getLayoutInflater().inflate(R.layout.list_view, null, true);
            TextView txt = tview.findViewById(R.id.txtSong);
            txt.setSelected(true);
            txt.setText(items[position]);

            return tview;
        }

    }
}
