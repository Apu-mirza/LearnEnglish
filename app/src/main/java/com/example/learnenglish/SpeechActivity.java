package com.example.learnenglish;

import android.Manifest;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;


public class SpeechActivity extends AppCompatActivity {

    final static int REQUEST_CODE_SPEECH_INPUT = 100;

    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 2;

    TextView textHeader, textChild, resultText;
    private long startTime, elapsedTime, stopTime;
    TextView textHeaderEnglish, textHeaderBangla;
    ImageButton speechAudio, hearing;
    CircleImageView micButton;

    ImageButton micBackground, audioBackground;
    private static final int VISIBILITY_DURATION = 3000;
    TextToSpeech textToSpeech;
    SpeechRecognizer speechRecognizer;
    AdView mAdView;
    Animation animation;
    CardView cardView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Pronunciation");
        setContentView(R.layout.activity_speech);

        textHeaderEnglish = findViewById(R.id.headerEnglish);
        textHeaderBangla = findViewById(R.id.headerBangla);
        textChild = findViewById(R.id.speechText2);
        resultText = findViewById(R.id.speechText3);
        micButton = findViewById(R.id.speechMic);
        speechAudio = findViewById(R.id.audioSpeech);
        hearing = findViewById(R.id.hearingSpeech);
        cardView = findViewById(R.id.cardviewId);


        String headerEnglish = getIntent().getStringExtra("headerEnglish");
        String headerBangla = getIntent().getStringExtra("headerBangla");
        String childString = getIntent().getStringExtra("dataChild");

        //admob ad initialization
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        //admob ad loading
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(getApplicationContext(), "Language Not Supported", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Text to Speech conversion failed", Toast.LENGTH_LONG).show();
                }
            }
        });

        hearing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                setVisibilityWithDuration(audioBackground,1000);
                hearing.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounded_background));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Remove the rounded background
                        hearing.setBackground(null);
                    }
                }, 500);
                textToSpeech.setSpeechRate(0.1f);
                textToSpeech.speak(childString, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
        speechAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                setVisibilityWithDuration(audioBackground,1000);
                speechAudio.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounded_background));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Remove the rounded background
                        speechAudio.setBackground(null);
                    }
                }, 500);
                textToSpeech.setSpeechRate(1f);
                textToSpeech.speak(childString, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });


        textHeaderEnglish.setText(headerEnglish);
        textHeaderBangla.setText(headerBangla);
        textChild.setText(childString);

        micButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                micButton.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounded_background));
                micButton.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounded_background));
//                cardView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounded_background2));
                cardView.setCardBackgroundColor(ContextCompat.getColorStateList(getApplicationContext(),R.color.purple_700));

                final Handler handler = new Handler();
                final long totalTime = elapsedTime;  // Total elapsed time for the animation
                final long visibleTime = 800;       // Duration to keep the background visible
                final long goneTime = 300;           // Duration to keep the background gone

                Runnable runnable = new Runnable() {
                    boolean isVisible = true;
                    long currentTime = 0;

                    @Override
                    public void run() {
                        if (currentTime >= totalTime) {
                            micButton.setBackground(null);
                            cardView.setBackground(null);
                            return;
                        }

                        if (isVisible) {
                            cardView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounded_background2));
                            handler.postDelayed(this, visibleTime);
                        } else {
                            cardView.setBackground(null);
                            handler.postDelayed(this, goneTime);
                        }

                        isVisible = !isVisible;
                        currentTime += (isVisible ? visibleTime : goneTime);
                    }
                };

                handler.post(runnable);
                startSpeechToText();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Remove the rounded background
                        micButton.setBackground(null);
                        cardView.setBackground(null);
//                        micButton.setBorderColor(Color.TRANSPARENT);
                    }
                }, elapsedTime);
                startSpeechToText();
            }
        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            // Permission not granted, request it
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO},
                    REQUEST_RECORD_AUDIO_PERMISSION);
        } else {
            // Permission already granted, initialize speech recognizer
            initializeSpeechRecognizer();
        }

    }

    private void setVisibilityWithDuration(final View view, long duration) {
        view.setVisibility(View.VISIBLE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.GONE);
            }
        }, duration);
    }


    private void initializeSpeechRecognizer() {
        if (SpeechRecognizer.isRecognitionAvailable(this)) {
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
            speechRecognizer.setRecognitionListener(new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle params) {
                    startTime = System.currentTimeMillis();  // Store the start time
                    Toast.makeText(SpeechActivity.this, "Speak now...", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onBeginningOfSpeech() {
                }

                @Override
                public void onRmsChanged(float rmsdB) {
                }

                @Override
                public void onBufferReceived(byte[] buffer) {
                }

                @Override
                public void onEndOfSpeech() {
                    stopTime = System.currentTimeMillis();  // Store the stop time
                    elapsedTime = stopTime - startTime;
                }

                @Override
                public void onError(int error) {
                }

                @Override
                public void onResults(Bundle results) {
                    ArrayList<String> speechResults = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                    if (speechResults != null && !speechResults.isEmpty()) {
                        String spokenText = speechResults.get(0);
                        String referenceText = textChild.getText().toString();

                        if (spokenText.equals(referenceText)) {
                            resultText.setText(spokenText);
                        } else {
                            highlightDifferences(spokenText, referenceText);
                        }
                    }
                }

                @Override
                public void onPartialResults(Bundle partialResults) {
                }

                @Override
                public void onEvent(int eventType, Bundle params) {
                }
            });
        } else {
            Toast.makeText(this, "Speech recognition not available", Toast.LENGTH_SHORT).show();
        }
    }

    private void startSpeechToText() {
        if (speechRecognizer != null) {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now...");

            speechRecognizer.startListening(intent);
        }
    }

    private void highlightDifferences(String spokenText, String referenceText) {
        StringBuilder highlightedText = new StringBuilder();
        String[] spokenWords = spokenText.split("[\\s.]");
        String[] referenceWords = referenceText.split("[\\s.]");

        int minLength = Math.min(spokenWords.length, referenceWords.length);

        // Compare each word in the spoken text with the corresponding word in the reference text
        for (int i = 0; i < minLength; i++) {
            if (referenceWords[i].equalsIgnoreCase(spokenWords[i])) {
                highlightedText.append(spokenWords[i]).append(" ");
            } else {
                highlightedText.append("<font color='red'>").append(spokenWords[i]).append("</font> ");
            }
        }

        // Add any remaining words from the longer text
        if (spokenWords.length > referenceWords.length) {
            for (int i = minLength; i < spokenWords.length; i++) {
                highlightedText.append("<font color='red'>").append(spokenWords[i]).append("</font> ");
            }
        }
//        else if (spokenWords.length < referenceWords.length) {
//            for (int i = minLength; i < referenceWords.length; i++) {
//                highlightedText.append(referenceWords[i]).append(" ");
//            }
//        }

        // Set the text of the TextView with HTML formatting to display the highlighted differences
        resultText.setText(android.text.Html.fromHtml(highlightedText.toString()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        switch (id){
            case R.id.settings1:
                Toast.makeText(getApplicationContext(),"settings is clicked",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.share_app1:
                Toast.makeText(getApplicationContext(),"share is clicked",Toast.LENGTH_SHORT).show();
                shareApp();
                return true;
            case R.id.rate_app1:
                Toast.makeText(getApplicationContext(),"rate is clicked",Toast.LENGTH_SHORT).show();
                rateApp();
                return true;
            case R.id.privacyId:
                Toast.makeText(getApplicationContext(),"privacy is clicked",Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(getApplicationContext(),PrivacyActivity.class);
                startActivity(intent1);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void shareApp(){
        try {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName());
            intent.setType("text/plain");
            intent = Intent.createChooser(intent,"send via: ");
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"unable to share this app",Toast.LENGTH_SHORT).show();
        }
    }
    public void rateApp(){
        Uri uri = Uri.parse("https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName());
        Intent intent1 = new Intent(Intent.ACTION_VIEW,uri);

        try {
            startActivity(intent1);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Unable to rate this app",Toast.LENGTH_SHORT).show();
        }
    }

}