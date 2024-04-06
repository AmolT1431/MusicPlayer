package com.app.musicplayer;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.app.musicplayer.Activitys.Flutter_Activity;
import com.app.musicplayer.Adapters.Song_List_View_Holder;

import com.app.musicplayer.Fragments.Home_Fragment;
import com.app.musicplayer.Fragments.Library_Fragment;
import com.app.musicplayer.Fragments.Premium_Fragment;
import com.app.musicplayer.Fragments.Search_Fragment;
import com.app.musicplayer.Layouts.Bottom_Navigation_Bar;
import com.app.musicplayer.Permission.Permission;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.IOException;


public class MainActivity extends Flutter_Activity {


    Context context;

    FrameLayout main_frame;

    TextView song_name_txt;

    ImageView song_img;

    CardView play_card;
    CardView play_layout;
    Button play;
    boolean play_button = false;
    Button add;
    boolean add_toggle = false;
    TextView art;
    Player player;
    Bottom_Navigation_Bar bottomNavigationBar;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setNavigationBarColor(Color.BLACK);
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }


        bottomNavigationBar = findViewById(R.id.bottom_navigation);// Bottom navigation bar

        load_ids(); // fot the loading components ids

        player = new Player(this, new MediaPlayer());// creating player
        new Permission(this);// for the storage permission

        play_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, PlayerActivity.class);
                startActivity(i);
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (play_button == true) {
                    play.setBackgroundResource(R.drawable.encore_icon_pause_24);
                    player.start();
                    play_button = false;
                } else {
                    play.setBackgroundResource(R.drawable.encore_icon_play);
                    player.stop();
                    play_button = true;
                }
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (add_toggle) {
                    add.setBackgroundResource(R.drawable.ic_filled_checkmark);
                    add_toggle = false;
                } else {
                    add.setBackgroundResource(R.drawable.icn_plus);
                    add_toggle = true;
                }
            }
        });
        Home_Fragment homeFragment = new Home_Fragment();
        Search_Fragment searchFragment = new Search_Fragment();
        Library_Fragment library_Fragment = new Library_Fragment();
        Premium_Fragment premiumFragment = new Premium_Fragment();

        Load_Fragment(homeFragment, true);
        homeFragment.onUpdateListener(new Home_Fragment.update() {
            @Override
            public void onChange(String song_name, String id, String artist, Bitmap Song_img) {
                Ui_item_change(song_name, id, artist, Song_img);

            }
        });// item update listener

        // switching fragments
        bottomNavigationBar.setOnNavigationItemSelectedListener(new Bottom_Navigation_Bar.OnNavigationItemSelectedListener() {
            @Override
            public void onNavigationItemSelected(View view) {
                switch (view.getId()) {
                    case R.id.nav_home:
                        Load_Fragment(homeFragment, false);
                        break;
                    case R.id.search:
                        Load_Fragment(searchFragment, false);

                        break;
                    case R.id.library:
                        Load_Fragment(library_Fragment, false);
                        break;
                    case R.id.premium:
                        Load_Fragment(premiumFragment, false);
                        break;
                }

            }

        });

    }

    public void load_ids() {
        main_frame = findViewById(R.id.main_fragment);

        play_card = findViewById(R.id.play_back);
        play_layout = findViewById(R.id.play_back_layout);

        add = findViewById(R.id.add);

        song_name_txt = findViewById(R.id.song_name);
        song_name_txt.setSelected(true);

        song_img = findViewById(R.id.song_img_2);
        art = findViewById(R.id.art_name);
        play = findViewById(R.id.play);

        play_card.setVisibility(View.INVISIBLE);
        play_layout.setVisibility(View.INVISIBLE);
    }


    public void Ui_item_change(String song_name, String id, String artist, @NonNull Bitmap Song_img) {
        play_card.setVisibility(View.VISIBLE);
        play_layout.setVisibility(View.VISIBLE);
        player.play(id);
        play.setBackgroundResource(R.drawable.encore_icon_pause_24);
        song_name_txt.setText(song_name);
        art.setText(artist);
        song_img.setImageBitmap(Song_img);


        Palette.from(Song_img).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@Nullable Palette palette) {
                Palette.Swatch dominantSwatch = palette.getDominantSwatch();
                int dominantColor = dominantSwatch.getRgb();
                play_layout.setCardBackgroundColor(dominantColor);
                //   play_card.setBackgroundColor(dominantColor);
                play_card.setCardBackgroundColor(dominantColor);

            }
        });
    }


    public void Load_Fragment(Fragment fragment, boolean is_init) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (is_init == true) {
            transaction.add(R.id.main_fragment, fragment);
            Log.e(TAG, "added:" + fragment);
        } else {
            transaction.replace(R.id.main_fragment, fragment);
            Log.e(TAG, "replaced:" + fragment);
        }
        transaction.commit();
    }


}
