package com.app.musicplayer.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.musicplayer.R;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;

import java.util.List;


public class Search_Fragment extends Fragment {
    private FirebaseStorage storage;
    private StorageReference storageRef;
    Context context;

    public Search_Fragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        FirebaseApp.initializeApp(context);
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        createDirectory("ok");

        return view;
    }

    private void createDirectory(String directoryName) {
        // Points to "Songs/test/directoryName" directory
        StorageReference directoryRef = storageRef.child("Songs/" + directoryName + "/.keep");

        // Check if the directory already exists
        directoryRef.listAll()
                .addOnSuccessListener(listResult -> {
                    // If directory doesn't exist, create it
                    if (listResult.getItems().isEmpty() && listResult.getPrefixes().isEmpty()) {
                        directoryRef.putBytes(new byte[]{})
                                .addOnSuccessListener(taskSnapshot -> {
                                    // Directory created successfully
                                    Log.d("MainActivity", "Directory created successfully");
                                    deleteKeepFile(directoryRef);
                                })
                                .addOnFailureListener(exception -> {
                                    // Handle unsuccessful creation of directory
                                    Log.e("MainActivity", "Directory creation failed: " + exception.getMessage());
                                });
                    } else {
                        // Directory already exists
                        Log.d("MainActivity", "Directory already exists");
                    }
                })
                .addOnFailureListener(exception -> {
                    // Handle any errors
                    Log.e("MainActivity", "Error occurred: " + exception.getMessage());
                });
    }
    private void deleteKeepFile(StorageReference directoryRef) {
        directoryRef.delete()
                .addOnSuccessListener(aVoid -> {
                    // .keep file deleted successfully
                    Log.d("MainActivity", ".keep file deleted successfully");
                })
                .addOnFailureListener(exception -> {
                    // Handle unsuccessful deletion of .keep file
                    Log.e("MainActivity", ".keep file deletion failed: " + exception.getMessage());
                });
    }
}