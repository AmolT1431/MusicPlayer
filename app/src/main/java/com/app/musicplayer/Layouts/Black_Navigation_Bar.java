package com.app.musicplayer.Layouts;

import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;

public class Black_Navigation_Bar {
    public static void SetBlack_Navigation_Bar(Window window) {
        // for black navigation bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setNavigationBarColor(Color.BLACK);
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }
    }
}
