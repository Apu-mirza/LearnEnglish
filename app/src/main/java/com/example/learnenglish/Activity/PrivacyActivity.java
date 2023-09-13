package com.example.learnenglish.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;

import com.example.learnenglish.R;

public class PrivacyActivity extends AppCompatActivity {

    TextView privacy, contactEmail, contactWhatsapp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        String privacyWords = getResources().getString(R.string.privacy);
        privacy = findViewById(R.id.privacytextId);
        contactEmail = findViewById(R.id.contactEmail);

        privacy.setText(privacyWords);

        contactEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }

    public void sendEmail(){
        String recipientEmail = "mirzaopu360@gmail.com";

        // Create an Intent with a "mailto" URI
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + recipientEmail));

        // Start the email Intent
        startActivity(Intent.createChooser(emailIntent, "Send Email"));
    }
}