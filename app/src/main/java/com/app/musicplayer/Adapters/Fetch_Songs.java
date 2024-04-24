package com.app.musicplayer.Adapters;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import com.app.musicplayer.Model_Class.Song;

import java.io.IOException;
import java.util.ArrayList;

public class Fetch_Songs {
    ContentResolver contentresolver;
    Cursor cursor;
    Context context;
    Uri uri;

    public Fetch_Songs(Context context) {
        this.context = context;
    }

    public ArrayList<Song> Load_Songs() {
        ArrayList<Song> Song_list = new ArrayList<>();

        contentresolver = context.getContentResolver();
        uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        cursor = contentresolver.query(uri, null, null, null, null);

        if (cursor == null) {
            Toast.makeText(context, "something is wrong", Toast.LENGTH_SHORT).show();

        } else if (!cursor.moveToFirst()) {
            Toast.makeText(context, "No song found", Toast.LENGTH_SHORT).show();
        } else {

            int Song_Name = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int SongId = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int artistName = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int album_img = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);

            do {
                Song item = new Song();
                String st = cursor.getString(Song_Name);
                String artist = cursor.getString(artistName);
                String Id = cursor.getString(SongId);
                String album_img_Id = cursor.getString(album_img);

                item.setName(st);
                item.setId(Id);
                item.setArt_name(artist);
                Bitmap albumArt = getAlbumArt(album_img_Id);
                item.setSong_uri(getSongUri(Id));

                if (albumArt != null) {
                    item.setSong_img(albumArt);
                }

                if (!isUnknownArtist(artist) && !isUnknownName(st)) {
                    Song_list.add(item);
                }

            } while (cursor.moveToNext());
        }

        return Song_list;

    }

    private Bitmap getAlbumArt(String albumId) {
        Uri uri = Uri.parse("content://media/external/audio/albumart");
        Uri albumArtUri = Uri.withAppendedPath(uri, albumId);
        try {
            return BitmapFactory.decodeStream(contentresolver.openInputStream(albumArtUri));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isUnknownArtist(String artist) {
        return artist == null || artist.isEmpty() || artist.equals("<unknown>");
    }

    private boolean isUnknownName(String name) {
        return name == null || name.isEmpty() || name.equals("");
    }

    private Uri getSongUri(String songId) {
        long id = Long.parseLong(songId);
        return ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id);
    }

}
