package com.example.learnenglish.Activity;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;

public class MyHelper {

    public static void setCustomBackground(View view) {
        final Drawable previousBackground = view.getBackground();
        //view.setBackgroundResource(R.drawable.rounded_background2); // Set your desired background drawable

        view.setBackgroundResource(android.R.color.holo_blue_dark);
        // Create a handler to remove the background after 200ms
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (previousBackground != null) {
                    view.setBackground(previousBackground); // Restore the previous background
                } else {
                    view.setBackgroundResource(android.R.color.transparent); // Set a transparent background if there was no previous background
                }
            }
        }, 200);
    }
}
