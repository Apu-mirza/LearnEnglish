package com.example.learnenglish.Activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.learnenglish.R;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions;
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions;

import java.util.ArrayList;
import java.util.Locale;

//adcolony library import


public class TranslatorActivity extends AppCompatActivity {

    private Spinner fromSpinner, toSpinner;
    ImageView recognizeMic;
    private TextInputEditText sourceText;
    private ImageView micIV;
    AdView mAdView;
    private MaterialButton translateBtn;
    String secondLineResponse, mainResponse;
    TextToSpeech textToSpeech;
    private TextView translateIV;
    InterstitialAd mInterstitialAd;

    String[] fromlanguage = {"English", "Africans", "Arabic", "Belarusian", "Bulgarian", "Bengali", "Welsh", "Hindi", "Urdu"};
    String[] tolanguage = {"Bengali", "Africans", "Arabic", "Belarusian", "Bulgarian", "English", "Welsh", "Hindi", "Urdu"};
    private static final int REQUEST_PERMISSION_CODE = 100;
    int languageCode, fromLanguageCode, toLanguageCode = 0;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Translator");
        setContentView(R.layout.activity_translator);


        fromSpinner = findViewById(R.id.idFromSpinner);
        toSpinner = findViewById(R.id.idTospinner);
        sourceText = findViewById(R.id.EditSource);
        micIV = findViewById(R.id.iIVdMic);
        translateBtn = findViewById(R.id.idBtnTranslation);
        translateIV = findViewById(R.id.idTranslatedTV);
        recognizeMic = findViewById(R.id.recMic);

        parseAdController();

        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fromLanguageCode = getLanguageCode(fromlanguage[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> fromAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, fromlanguage);
        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(fromAdapter);

        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                toLanguageCode = getLanguageCode(tolanguage[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> toAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, tolanguage);
        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpinner.setAdapter(toAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        translateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translateIV.setVisibility(View.VISIBLE);
                translateIV.setText("");
                if (sourceText.getText().toString().isEmpty()){
                    Toast.makeText(TranslatorActivity.this,"Please enter any text.",Toast.LENGTH_LONG).show();
                }
                else if (fromLanguageCode == 0){
                    Toast.makeText(TranslatorActivity.this,"Please select source language.",Toast.LENGTH_LONG).show();
                }
                else if (toLanguageCode == 0){
                    Toast.makeText(TranslatorActivity.this,"Please select translation language.",Toast.LENGTH_LONG).show();
                }
                else {
                    translateText(fromLanguageCode,toLanguageCode,sourceText.getText().toString());
                }

            }
        });

        recognizeMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"say something to translate");

                recognizeMic.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounded_background));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Remove the rounded background
                        recognizeMic.setBackground(null);
                    }
                }, 300);

                try{
                    startActivityForResult(intent,REQUEST_PERMISSION_CODE);
                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(TranslatorActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();

                }
            }
        });

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

        micIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = translateIV.getText().toString();
                micIV.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounded_background));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Remove the rounded background
                        micIV.setBackground(null);
                    }
                }, 300);
                textToSpeech.setSpeechRate(0.5f);
                textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

    }

    private void translateText(int fromLanguageCode, int toLanguageCode, String source){
        translateIV.setText("Downloading model, please wait....");
        FirebaseTranslatorOptions options = new FirebaseTranslatorOptions.Builder()
                .setSourceLanguage(fromLanguageCode)
                .setTargetLanguage(toLanguageCode)
                .build();
        FirebaseTranslator translator = FirebaseNaturalLanguage.getInstance().getTranslator(options);
        FirebaseModelDownloadConditions conditions = new FirebaseModelDownloadConditions.Builder().build();

        translator.downloadModelIfNeeded(conditions).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                translateIV.setText("Translating...");
                translator.translate(source).addOnSuccessListener(new OnSuccessListener<String>() {
                    @Override
                    public void onSuccess(String s) {
                        translateIV.setText(s);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(TranslatorActivity.this,"Failed to translate!!Try again.",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(TranslatorActivity.this,"Failed to download model. Check your internet connection.",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && null != data){
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            sourceText.setText(result.get(0));
        }
    }


    private int getLanguageCode(String language){

        int languageCode = 0;
        switch (language){
            case "English":
                languageCode = FirebaseTranslateLanguage.EN;
                break;
            case "Africans":
                languageCode = FirebaseTranslateLanguage.AF;
                break;
            case "Arabic":
                languageCode = FirebaseTranslateLanguage.AR;
                break;
            case "Belarusian":
                languageCode = FirebaseTranslateLanguage.BE;
                break;
            case "Bulgarian":
                languageCode = FirebaseTranslateLanguage.BG;
                break;
            case "Bengali":
                languageCode = FirebaseTranslateLanguage.BN;
                break;
            case "Welsh":
                languageCode = FirebaseTranslateLanguage.CY;
                break;
            case "Hindi":
                languageCode = FirebaseTranslateLanguage.HI;
                break;
            case "Urdu":
                languageCode = FirebaseTranslateLanguage.UR;
                break;

            default: languageCode = 0;

        }
        return languageCode;

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
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
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
                Intent intent1 = new Intent(getApplicationContext(), PrivacyActivity.class);
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

    public void parseAdController(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://englishappcontroller.000webhostapp.com/learnenglish/other.php";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d(TAG, "onResponse: "+response);

                        if (response.contains("showOtherBanner")){
                            showBannerAds();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    private void showBannerAds(){
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        //admob ad loading(banner ad)
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

}
