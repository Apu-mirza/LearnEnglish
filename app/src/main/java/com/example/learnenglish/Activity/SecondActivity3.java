package com.example.learnenglish.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnenglish.R;
import com.example.learnenglish.Adapter.SecondGridAdapter;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Random;

public class SecondActivity3 extends AppCompatActivity {

    private TextToSpeech textToSpeech;
    private Button button;
    private GridView gridView;
    AdView mAdView;
    String[] motivationList;
    private String[] subTopics, animal, color, conversation, date_time, driving, emergency, fruits_vegetables,
            greetings, grocery,health, home, humanbody, kitchen, office, relation, shopping, sports, travel, weather;
    private int[] flags = {R.drawable.fox, R.drawable.colour, R.drawable.talking, R.drawable.schedule, R.drawable.car,
            R.drawable.emergency, R.drawable.fruits_vegetables, R.drawable.handshake, R.drawable.grocery, R.drawable.favorite,
            R.drawable.house, R.drawable.human_body, R.drawable.cooking, R.drawable.office, R.drawable.relation, R.drawable.grocery,
            R.drawable.playing, R.drawable.airplane, R.drawable.cloudy
    };
    private String[] countryDetails, countryNames;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Useful Sentences");
        setContentView(R.layout.activity_second3);

        subTopics = getResources().getStringArray(R.array.sub_topics3);
        animal = getResources().getStringArray(R.array.animal);
        color = getResources().getStringArray(R.array.color);
        conversation = getResources().getStringArray(R.array.conversation);
        date_time = getResources().getStringArray(R.array.date_time);
        driving = getResources().getStringArray(R.array.driving);
        emergency = getResources().getStringArray(R.array.emergency);
        fruits_vegetables = getResources().getStringArray(R.array.fruits_vegetables);
        sports = getResources().getStringArray(R.array.games);
        greetings = getResources().getStringArray(R.array.greetings);
        grocery = getResources().getStringArray(R.array.grocery);
        health = getResources().getStringArray(R.array.health);
        home = getResources().getStringArray(R.array.home);
        humanbody = getResources().getStringArray(R.array.human_body);
        travel = getResources().getStringArray(R.array.travel);
        kitchen = getResources().getStringArray(R.array.kitchen);
        office = getResources().getStringArray(R.array.office);
        relation = getResources().getStringArray(R.array.relation);
        shopping = getResources().getStringArray(R.array.shopping);
        weather = getResources().getStringArray(R.array.weather);




        gridView = (GridView) findViewById(R.id.gridViewId);

        SecondGridAdapter adapter = new SecondGridAdapter(this,subTopics,flags);
        gridView.setAdapter(adapter);

        //admob ad initialization
//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });
//
//        //admob ad loading
//        mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),subTopics[i]+" is clicked",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
                if(i==0){
                    intent.putExtra("dataHeader", animal);
                    intent.putExtra("dataChild", animal);
                    intent.putExtra("title", "Animal");
                    startActivity(intent);
                }
                else if(i==1){
                    intent.putExtra("dataHeader", color);
                    intent.putExtra("dataChild", color);
                    intent.putExtra("title", "Color");
                    startActivity(intent);
                }
                else if(i==2){
                    intent.putExtra("dataHeader", conversation);
                    intent.putExtra("dataChild", conversation);
                    intent.putExtra("title", "Conversation");
                    startActivity(intent);
                }
                else if(i==3){
                    intent.putExtra("dataHeader", date_time);
                    intent.putExtra("dataChild", date_time);
                    intent.putExtra("title", "Date & Time");
                    startActivity(intent);
                }
                else if(i==4){
                    intent.putExtra("dataHeader", driving);
                    intent.putExtra("dataChild", driving);
                    intent.putExtra("title", "Driving");
                    startActivity(intent);
                }
                else if(i==5){
                    intent.putExtra("dataHeader", emergency);
                    intent.putExtra("dataChild", emergency);
                    intent.putExtra("title", "Emergency");
                    startActivity(intent);
                }
                else if(i==6){
                    intent.putExtra("dataHeader", fruits_vegetables);
                    intent.putExtra("dataChild", fruits_vegetables);
                    intent.putExtra("title", "Fruits & Vegetables");
                    startActivity(intent);
                }
                else if(i==7){
                    intent.putExtra("dataHeader", greetings);
                    intent.putExtra("dataChild", greetings);
                    intent.putExtra("title", "Greetings");
                    startActivity(intent);
                }
                else if(i==8){
                    intent.putExtra("dataHeader", grocery);
                    intent.putExtra("dataChild", grocery);
                    intent.putExtra("title", "Grocery");
                    startActivity(intent);
                }
                else if(i==9){
                    intent.putExtra("dataHeader", health);
                    intent.putExtra("dataChild", health);
                    intent.putExtra("title", "Health");
                    startActivity(intent);
                }
                else if(i==10){
                    intent.putExtra("dataHeader", home);
                    intent.putExtra("dataChild", home);
                    intent.putExtra("title", "Home");
                    startActivity(intent);
                }
                else if(i==11){
                    intent.putExtra("dataHeader", humanbody);
                    intent.putExtra("dataChild", humanbody);
                    intent.putExtra("title", "Human Body");
                    startActivity(intent);
                }
                else if(i==12){
                    intent.putExtra("dataHeader", kitchen);
                    intent.putExtra("dataChild", kitchen);
                    intent.putExtra("title", "Kitchen");
                    startActivity(intent);
                }
                else if(i==13){
                    intent.putExtra("dataHeader", office);
                    intent.putExtra("dataChild", office);
                    intent.putExtra("title", "office");
                    startActivity(intent);
                }
                else if(i==14){
                    intent.putExtra("dataHeader", relation);
                    intent.putExtra("dataChild", relation);
                    intent.putExtra("title", "Relation");
                    startActivity(intent);
                }
                else if(i==15){
                    intent.putExtra("dataHeader", shopping);
                    intent.putExtra("dataChild", shopping);
                    intent.putExtra("title", "Shopping");
                    startActivity(intent);
                }
                else if(i == 16){
                    intent.putExtra("dataHeader", sports);
                    intent.putExtra("dataChild", sports);
                    intent.putExtra("title", "Sports");
                    startActivity(intent);
                }
                else if(i==17){
                    intent.putExtra("dataHeader", travel);
                    intent.putExtra("dataChild", travel);
                    intent.putExtra("title", "Travel");
                    startActivity(intent);
                }
                else {
                    intent.putExtra("dataHeader", weather);
                    intent.putExtra("dataChild", weather);
                    intent.putExtra("title", "Weather");
                    startActivity(intent);
                }
                view.setBackgroundColor(Color.DKGRAY); //changing background in click time
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.setBackgroundColor(Color.TRANSPARENT); // Reset the background color to transparent
                    }
                }, 1000);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //options menu selected button
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

    //app sharing method
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

    //app rating method
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