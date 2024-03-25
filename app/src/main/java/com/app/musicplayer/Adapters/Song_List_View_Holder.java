package com.app.musicplayer.Adapters;


import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.app.musicplayer.Activitys.MyBottomSheetFragment;
import com.app.musicplayer.Activitys.Song_Menu_Dialog;
import com.app.musicplayer.MainActivity;
import com.app.musicplayer.PlayerActivity;
import com.app.musicplayer.R;
import com.app.musicplayer.Song;

import java.util.ArrayList;

public class Song_List_View_Holder extends RecyclerView.Adapter<Song_List_View_Holder.ViewHolder> {
    Context context;
    ArrayList<Song> Song_list;
    MyBottomSheetFragment bottomSheetFragment = new MyBottomSheetFragment();

    public Song_List_View_Holder() {
    }

    public Song_List_View_Holder(Context context, ArrayList<Song> song_list) {
        this.context = context;
        this.Song_list = song_list;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.song_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = Song_list.get(position);
        int pos = position;

        holder.name.setText(song.getName());
        holder.art_name.setText(song.getArt_name());
        if (song.getSong_img() != null) {
            holder.song_img.setImageBitmap(song.getSong_img());
        } else {
            holder.song_img.setImageResource(R.drawable.song);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String song_name = song.getName();
                Intent play = new Intent(context, PlayerActivity.class);
                play.putExtra("position", pos);
                play.putExtra("SongName", song_name);
                play.putExtra("songId", song.getId());
                context.startActivity(play);

            }
        });

        holder.thre_dotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bottomSheetFragment.show(((FragmentActivity) view.getContext()).getSupportFragmentManager(), bottomSheetFragment.getTag());
            }
        });


    }

    @Override
    public int getItemCount() {
        return Song_list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView art_name;
        ImageView song_img;
        ImageView thre_dotes;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.song_name);
            song_img = itemView.findViewById(R.id.song_img);
            art_name = itemView.findViewById(R.id.art_name);
            thre_dotes = itemView.findViewById(R.id.three_dote);

        }
    }
}
