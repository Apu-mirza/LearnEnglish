package com.example.learnenglish.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

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
    public static void shareApp(Context context) {
        try {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + context.getPackageName());
            intent.setType("text/plain");
            intent = Intent.createChooser(intent, "send via: ");
            context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(context, "Unable to share this app", Toast.LENGTH_SHORT).show();
        }
    }

    public static void rateApp(Context context) {
        Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + context.getPackageName());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        try {
            context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(context, "Unable to rate this app", Toast.LENGTH_SHORT).show();
        }
    }
}
