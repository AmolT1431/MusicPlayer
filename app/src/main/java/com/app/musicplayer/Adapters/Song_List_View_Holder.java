package com.app.musicplayer.Adapters;




import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.app.musicplayer.Activitys.MyBottomSheetFragment;
import com.app.musicplayer.MainActivity;
import com.app.musicplayer.PlayerActivity;
import com.app.musicplayer.R;
import com.app.musicplayer.Song;

import java.util.ArrayList;

public class Song_List_View_Holder extends RecyclerView.Adapter<Song_List_View_Holder.ViewHolder> {
    Context context;
    ArrayList<Song> Song_list;
    MyBottomSheetFragment bottomSheetFragment = new MyBottomSheetFragment();
    private OnItemClickListener listener;

    public Song_List_View_Holder(Context context, ArrayList<Song> song_list) {
        this.context = context;
        this.Song_list = song_list;
    }

    public Song_List_View_Holder(Context context, ArrayList<Song> song_list, OnItemClickListener listener) {
        this.context = context;
        this.Song_list = song_list;
        this.listener = listener;

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
        Bitmap img;

        holder.name.setText(song.getName());
        holder.art_name.setText(song.getArt_name());
        if (song.getSong_img() != null) {
            holder.song_img.setImageBitmap(song.getSong_img());
            img=song.getSong_img();
        } else {
            holder.song_img.setImageResource(R.drawable.app_logo1);
            img = BitmapFactory.decodeResource(context.getResources(), R.drawable.app_logo1);

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String song_name = song.getName();
                Intent play = new Intent(context, PlayerActivity.class);
                play.putExtra("position", pos);
                play.putExtra("SongName", song_name);
                play.putExtra("songId", song.getId());

                if (listener != null) {
                    listener.onItemClick(song.getName(), song.getId(), song.getArt_name(), img);
                }
            }
        });

        holder.three_dote_layout.setOnClickListener(new View.OnClickListener() {
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
        RelativeLayout three_dote_layout;
        ImageView song_img;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.song_name);
            song_img = itemView.findViewById(R.id.song_img);
            art_name = itemView.findViewById(R.id.art_name);

            three_dote_layout=itemView.findViewById(R.id.three_dote_layout);

        }
    }

    public interface OnItemClickListener {
        void onItemClick(String song_name, String id, String artist, Bitmap Song_img);
    }

}
