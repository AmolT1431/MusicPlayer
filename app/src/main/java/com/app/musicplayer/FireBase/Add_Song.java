package com.app.musicplayer.FireBase;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.app.musicplayer.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class Add_Song {
    StorageReference mStorageRef;
    Context context;

    public void uploadSong(Context context, Uri audioUri) {
        this.context = context;
        mStorageRef = FirebaseStorage.getInstance().getReference();

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(context, audioUri);

        // Extract title and artist
        String title = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        String artist = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);

        // Image, if available
        byte[] imageBytes = retriever.getEmbeddedPicture();

        add_song(audioUri, title, artist);
        add_image(title, title, imageBytes);


    }

    public void add_song(Uri audioUri, String title, String artist) {
        StorageReference songRef = mStorageRef.child("Songs/" + title + "/" + "song/" + title + ".mp3");
        if (artist == null) {
            artist = "music_player";
        } else {

            StorageMetadata metadata = new StorageMetadata.Builder()
                    .setCustomMetadata("title", title)
                    .setCustomMetadata("artistName", artist)
                    .build();

            songRef.putFile(audioUri, metadata).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Log.d("Add_Song", "uploaded");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("Add_Song", "uploaded_filed");
                }
            });
        }

    }

    public void add_image(String imageName, String name, byte[] imageBytes) {
        StorageReference imageRef = mStorageRef.child("Songs/").child(name + "/").child("images/" + imageName + ".jpg");
        if (imageBytes == null) {
            byte[] Bytes = drawableToBytes(R.drawable.app_logo1);
            imageBytes = Bytes;
        } else {
            imageRef.putBytes(imageBytes)
                    .addOnSuccessListener(new OnSuccessListener<com.google.firebase.storage.UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(com.google.firebase.storage.UploadTask.TaskSnapshot taskSnapshot) {
                            Log.d("Add_Song", "Image_uploaded");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Log.d("Add_Song", "Image_uploaded_failed");
                        }
                    });
        }

    }

    private byte[] drawableToBytes(int drawableId) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), drawableId);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }


}
