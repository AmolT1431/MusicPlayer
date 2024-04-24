package com.app.musicplayer.Fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.musicplayer.FireBase.Add_Song;
import com.app.musicplayer.R;


public class Library_Fragment extends Fragment {

    private static final int PICK_AUDIO_REQUEST = 1;
    Button uplooad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);
        uplooad = view.findViewById(R.id.upload);
        uplooad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAndUploadSong();
            }
        });
        return view;
    }

    private void selectAndUploadSong() {
        Intent intent = new Intent();
        intent.setType("audio/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Audio"), PICK_AUDIO_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_AUDIO_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri audioUri = data.getData();
            Log.d("library:",audioUri.toString());
           // new Add_Song().uploadSong(getActivity(), audioUri);
        }
    }


}