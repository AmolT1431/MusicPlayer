package com.app.musicplayer.Fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.musicplayer.Adapters.Song_List_View_Holder;
import com.app.musicplayer.Fetch_Songs;
import com.app.musicplayer.R;
import com.app.musicplayer.Song;

import java.util.ArrayList;


public class Library_Fragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);
        return view;
    }


}