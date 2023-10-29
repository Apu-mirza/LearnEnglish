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
import android.text.style.UnderlineSpan;
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

        contactEmail = findViewById(R.id.contactEmailTv);
        contactEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });

        TextView contactFacebook = findViewById(R.id.contactFacebookTv);
        //make underlined facebook
        String textToUnderline = "Facebook";
        SpannableString content = new SpannableString(textToUnderline);

        content.setSpan(new UnderlineSpan(), 0, textToUnderline.length(), 0);

        contactFacebook.setText(content);
        contactFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactFacebook();
            }
        });

        TextView contactWhatsapp = findViewById(R.id.contactWhatsappTv);
        //make underlined whatsapp
        String underlineWhatsapp = "WhatsApp";
        SpannableString content1 = new SpannableString(underlineWhatsapp);

        content1.setSpan(new UnderlineSpan(), 0, underlineWhatsapp.length(), 0);

        contactWhatsapp.setText(content1);
        contactWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactWhatsapp();
            }
        });

        privacy.setText(privacyWords);

        //make underlined gmail
        String underlinedEmail = "Gmail";
        SpannableString content2 = new SpannableString(underlinedEmail);

        content2.setSpan(new UnderlineSpan(), 0, underlinedEmail.length(), 0);

        contactEmail.setText(content2);

        contactEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }

    public void sendEmail(){
        String recipientEmail = "apumirza2001@gmail.com";

        // Create an Intent with a "mailto" URI
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + recipientEmail));

        // Start the email Intent
        startActivity(Intent.createChooser(emailIntent, "Send Email"));
    }
    public void contactFacebook(){
        Uri uri = Uri.parse("http://m.me/{100046545540147}?ref={referral_param}");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);


// Check if the Facebook Messenger app is installed on the device
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            // If Messenger is not installed, you can provide a fallback action, like opening the Facebook website or displaying a message to install the app.
        }
    }
    public void contactWhatsapp(){
        String phoneNumber = "+8801764134762"; // Replace with the recipient's phone number
        Uri uri = Uri.parse("https://wa.me/" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

// Check if the WhatsApp app is installed on the device
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            // If WhatsApp is not installed, you can provide a fallback action, like displaying a message to install the app.
        }
    }
}