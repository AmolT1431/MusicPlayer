package com.app.musicplayer.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.app.musicplayer.Activitys.MyBottomSheetFragment;
import com.app.musicplayer.FireBase.FSong;
import com.app.musicplayer.Model_Class.Song;
import com.app.musicplayer.R;
import com.app.musicplayer.Testing;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;


public class Online_Song_LIst_View_Holder extends RecyclerView.Adapter<Online_Song_LIst_View_Holder.ViewHolder> {
    Context context;
    ArrayList<FSong> Song_list;

    private OnItemClickListener listener;

    public Online_Song_LIst_View_Holder(Context context, ArrayList<FSong> song_list) {
        this.context = context;
        this.Song_list = song_list;
    }

    public Online_Song_LIst_View_Holder(Context context, ArrayList<FSong> song_list, OnItemClickListener listener) {
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

        FSong song = Song_list.get(position);
        int pos = position;


        holder.name.setText(song.getTitle());
        holder.art_name.setText(song.getArtist());
        if (song.getImage_url() != null) {
            Glide.with(context).load(song.getImage_url()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.song_img);
        } else {
            holder.song_img.setImageResource(R.drawable.app_logo1);


        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(song.getTitle(), song.getSong_url(), song.getArtist(), song.getImage_url());
                }
            }
        });

        holder.three_dote_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  bottomSheetFragment.show(((FragmentActivity) view.getContext()).getSupportFragmentManager(), bottomSheetFragment.getTag());
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

            three_dote_layout = itemView.findViewById(R.id.three_dote_layout);

        }
    }

    public interface OnItemClickListener {
        void onItemClick(String song_name, String url, String artist, String image_url);
    }

}
