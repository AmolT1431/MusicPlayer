package com.app.musicplayer.Layouts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.app.musicplayer.Activitys.Flutter_Activity;
import com.app.musicplayer.Adapters.Song_List_View_Holder;
import com.app.musicplayer.Fragments.Home_Fragment;
import com.app.musicplayer.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class Bottom_Navigation_Bar extends ConstraintLayout implements View.OnClickListener {
    View bar_view;

    public interface OnNavigationItemSelectedListener {
        void onNavigationItemSelected(View view);
    }

    private OnNavigationItemSelectedListener listener;


    public void setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener listener) {
        this.listener = listener;
    }


    public Bottom_Navigation_Bar(Context context) {
        super(context);
        init(context);
    }


    public Bottom_Navigation_Bar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public Bottom_Navigation_Bar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        bar_view = LayoutInflater.from(context).inflate(R.layout.test_layout, this, true);
        set_click();
    }

    public void set_click() {
        ConstraintLayout home = bar_view.findViewById(R.id.nav_home);

        ImageView imageView = (ImageView) bar_view.findViewById(R.id.nav_home_img);
        TextView textView = (TextView) bar_view.findViewById(R.id.text_home);
        imageView.setBackgroundResource(R.drawable.encore_icon_home_active_16);
        textView.setTextColor(Color.WHITE);

        ConstraintLayout search = bar_view.findViewById(R.id.search);
        ConstraintLayout library = bar_view.findViewById(R.id.library);
        ConstraintLayout premium = bar_view.findViewById(R.id.premium);

        Home_item(home);
        Search_item(search);
        Library_item(library);
        Premium_item(premium);

        home.setOnClickListener(this::onClick);
        search.setOnClickListener(this::onClick);
        library.setOnClickListener(this::onClick);
        premium.setOnClickListener(this::onClick);


    }

    public void Home_item(View view) {
        ImageView imageView = (ImageView) bar_view.findViewById(R.id.nav_home_img);
        TextView textView = (TextView) bar_view.findViewById(R.id.text_home);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.animate().scaleX(0.95f).scaleY(0.95f).setDuration(260).start();
                        break;
                    case MotionEvent.ACTION_UP:
                        reset();
                        v.animate().scaleX(1.0f).scaleY(1.0f).setDuration(260).start();
                        imageView.setBackgroundResource(R.drawable.encore_icon_home_active_16);
                        textView.setTextColor(Color.WHITE);
                        listener.onNavigationItemSelected(view);
                        break;
                }
                return true; // Return false to allow other touch listeners (if any) to be called
            }
        });


    }

    public void Search_item(View view) {
        ImageView imageView = (ImageView) bar_view.findViewById(R.id.search_img);
        TextView textView = (TextView) bar_view.findViewById(R.id.text_search);

        ConstraintLayout search_layout = bar_view.findViewById(R.id.search);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Scale down when pressed
                        v.animate().scaleX(0.95f).scaleY(0.95f).setDuration(260).start();
                        break;
                    case MotionEvent.ACTION_UP:
                        // Scale back to normal size when released
                        reset();
                        v.animate().scaleX(1.0f).scaleY(1.0f).setDuration(260).start();

                        imageView.setBackgroundResource(R.drawable.encore_icon_search_active_16);
                        textView.setTextColor(Color.WHITE);

                        break;
                }
                return false; // Return false to allow other touch listeners (if any) to be called
            }
        });

    }

    public void Library_item(View view) {
        ImageView imageView = (ImageView) bar_view.findViewById(R.id.library_img);
        TextView textView = (TextView) bar_view.findViewById(R.id.text_library);


        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Scale down when pressed
                        v.animate().scaleX(0.95f).scaleY(0.95f).setDuration(260).start();
                        break;
                    case MotionEvent.ACTION_UP:
                        // Scale back to normal size when released
                        reset();
                        v.animate().scaleX(1.0f).scaleY(1.0f).setDuration(260).start();

                        imageView.setBackgroundResource(R.drawable.encore_icon_collection_active_16);
                        textView.setTextColor(Color.WHITE);

                        break;
                }
                return false; // Return false to allow other touch listeners (if any) to be called
            }
        });


    }

    public void Premium_item(View view) {
        ImageView imageView = (ImageView) bar_view.findViewById(R.id.premium_img);
        TextView textView = (TextView) bar_view.findViewById(R.id.text_premium);


        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Scale down when pressed
                        v.animate().scaleX(0.95f).scaleY(0.95f).setDuration(260).start();
                        break;
                    case MotionEvent.ACTION_UP:
                        // Scale back to normal size when released
                        reset();
                        v.animate().scaleX(1.0f).scaleY(1.0f).setDuration(260).start();
                        imageView.setBackgroundResource(R.drawable.white_logo);
                        textView.setTextColor(Color.WHITE);

                        break;
                }
                return false;
            }
        });


    }

    public void reset() {
        ImageView home_imageView = (ImageView) bar_view.findViewById(R.id.nav_home_img);
        TextView home_textView = (TextView) bar_view.findViewById(R.id.text_home);
        home_imageView.setBackgroundResource(R.drawable.encore_icon_home_16);
        home_textView.setTextColor(Color.parseColor("#B8B3B3"));

        ImageView search_imageView = (ImageView) bar_view.findViewById(R.id.search_img);
        TextView search_textView = (TextView) bar_view.findViewById(R.id.text_search);
        search_imageView.setBackgroundResource(R.drawable.encore_icon_search_16);
        search_textView.setTextColor(Color.parseColor("#B8B3B3"));

        ImageView library_imageView = (ImageView) bar_view.findViewById(R.id.library_img);
        TextView library_textView = (TextView) bar_view.findViewById(R.id.text_library);
        library_imageView.setBackgroundResource(R.drawable.encore_icon_collection_16);
        library_textView.setTextColor(Color.parseColor("#B8B3B3"));

        ImageView premium_imageView = (ImageView) bar_view.findViewById(R.id.premium_img);
        TextView premium_textView = (TextView) bar_view.findViewById(R.id.text_premium);
        premium_imageView.setBackgroundResource(R.drawable.start_screen_logo);
        premium_textView.setTextColor(Color.parseColor("#B8B3B3"));
    }


    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.nav_home:
                Home_item(view);
                break;
            case R.id.search:
                Search_item(view);
                break;
            case R.id.library:
                Library_item(view);

                break;
            case R.id.premium:
                Premium_item(view);
                break;
        }
        if (listener != null) {
            listener.onNavigationItemSelected(view);
        }


    }


}

