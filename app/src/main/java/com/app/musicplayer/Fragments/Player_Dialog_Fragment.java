package com.app.musicplayer.Fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.app.musicplayer.Model_Class.Player;
import com.app.musicplayer.R;
import com.app.musicplayer.Model_Class.Song;

public class Player_Dialog_Fragment extends DialogFragment {
    Song song;
    ImageView imageView;
    RelativeLayout player;
    TextView song_name;
    Button play_button;
    Player player_d;

    public Player_Dialog_Fragment(Player player_d, Song song) {
        this.song = song;
        this.player_d = player_d;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);
        // Apply the full screen dialog style
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new Dialog(requireContext(), getTheme());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_player__dialog_, container, false);
        imageView = view.findViewById(R.id.song_image);
        player = view.findViewById(R.id.player);
        song_name = view.findViewById(R.id.song_name);
        play_button = view.findViewById(R.id.play_btn);

        imageView.setImageBitmap(song.getSong_img());
        song_name.setText(song.getName());
        song_name.setSelected(true);


        if (player_d.is_playing()) {
            play_button.setBackgroundResource(R.drawable.player_play);
        } else {
            play_button.setBackgroundResource(R.drawable.player_stop);
        }

        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player_d.is_playing()) {
                    player_d.stop();
                    play_button.setBackgroundResource(R.drawable.player_stop);
                } else {
                    player_d.start();
                    play_button.setBackgroundResource(R.drawable.player_play);
                }

            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        // for black navigation bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getDialog().getWindow();
            window.setNavigationBarColor(Color.BLACK);
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }
    }
}
