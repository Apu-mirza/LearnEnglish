package com.example.learnenglish;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;


public class FirstActivity extends AppCompatActivity {

    private String[]  topics, sub_topics, subt_topics2;
    private Button button1, button2;

    AdView mAdView;
    private GridView gridView;
    private int[] flags = {R.drawable.vocabulary, R.drawable.translate, R.drawable.favorite,
            R.drawable.talking, R.drawable.idea, R.drawable.speech, R.drawable.grammar,
            R.drawable.share, R.drawable.star
    };


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);


        gridView = (GridView) findViewById(R.id.gridViewId);

        topics = getResources().getStringArray(R.array.topics);
        FirebaseMessaging.getInstance().subscribeToTopic("allDevices");

        sub_topics = getResources().getStringArray(R.array.sub_topics);
        subt_topics2 = getResources().getStringArray(R.array.sub_topics2);



        GridAdapter myAdapter = new GridAdapter(this,topics, flags);
        gridView.setAdapter(myAdapter);

        //admob ad initialization
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        //admob ad loading
        mAdView = findViewById(R.id.adView);
        if (isConnectedToInternet()){
            mAdView.setVisibility(View.VISIBLE);
        }
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0){
                    Toast.makeText(getApplicationContext(),"Vocabulary will be added later",Toast.LENGTH_SHORT).show();
                }
                if(i == 1){
                    Intent intent = new Intent(FirstActivity.this, TranslatorActivity.class);
                    startActivity(intent);
                }
                else if(i==2){
                    Toast.makeText(getApplicationContext(),"favorite item will be added later",Toast.LENGTH_SHORT).show();
                }
                else if(i == 3){
                    Intent intent = new Intent(FirstActivity.this, SecondActivity3.class);
                    startActivity(intent);
                }
                if (i == 4){
                    Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                    startActivity(intent);
                }
                else if(i == 5){
                    Intent intent = new Intent(FirstActivity.this, SecondActivity2.class);
                    startActivity(intent);
                }
                else if(i == 6){
                    Toast.makeText(getApplicationContext(),"grammar will be added later",Toast.LENGTH_SHORT).show();
                }
                else if (i == 7){
                    shareApp();
                }
                else if(i == 8){
                    Uri uri = Uri.parse("https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName());
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);

                    try {
                        startActivity(intent);
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(),"Ubanle to rate this app",Toast.LENGTH_SHORT).show();
                    }
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

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

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert!!");
        builder.setMessage("Are you sure you want to exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Perform any additional actions or exit the app
                finish();
            }
        });
        builder.setNegativeButton("No", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private boolean isConnectedToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

}