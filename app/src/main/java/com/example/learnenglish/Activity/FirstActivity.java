package com.example.learnenglish.Activity;

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
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnenglish.Adapter.GridAdapter;
import com.example.learnenglish.Adapter.MyAdapter;
import com.example.learnenglish.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

import java.util.Random;


public class FirstActivity extends AppCompatActivity {

    private String[]  topics, sub_topics, subt_topics2, vocabulary;
    private Button button1, button2;

    AdView mAdView;
    String news;
    String[] newsArray;
    private RecyclerView recyclerView;
    GridView gridView;
    private int[] flags = {R.drawable.vocabulary, R.drawable.translate, R.drawable.favorite,
            R.drawable.talking, R.drawable.idea, R.drawable.speech, R.drawable.grammar,
            R.drawable.share, R.drawable.star
    };

    TextView newsTextIv;
    String[] motivationList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);


        recyclerView = findViewById(R.id.recyclerView);
//        gridView = findViewById(R.id.gridViewId);

        topics = getResources().getStringArray(R.array.topics);
        FirebaseMessaging.getInstance().subscribeToTopic("allDevices");

        sub_topics = getResources().getStringArray(R.array.sub_topics);
        subt_topics2 = getResources().getStringArray(R.array.sub_topics2);
        vocabulary = getResources().getStringArray(R.array.vocabulary1);


        MyAdapter myAdapter = new MyAdapter(this, flags, topics);
        recyclerView.setAdapter(myAdapter);

        //news set to news textview
        getNews();
        LinearLayout newsLayout = findViewById(R.id.newsLayout);
        newsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FieldActivity.class);
                intent.putExtra("fieldTitle", "Trending News");
                String news1 = getNews();
                int itemId = 2;
                intent.putExtra("itemId", itemId);
                startActivity(intent);
            }
        });
        //motivation text set to motivation textview
        motivationList = getResources().getStringArray(R.array.motivations);

        Random random = new Random();
        int randomIndex = random.nextInt(motivationList.length);

        String randomSentence = motivationList[randomIndex];

        TextView motivationIv = findViewById(R.id.motivationText);

        motivationIv.setText(randomSentence);

        LinearLayout motivationLayout = findViewById(R.id.motivationLayout);
        motivationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FieldActivity.class);
                intent.putExtra("fieldTitle", "Quote of the day");
                intent.putExtra("content", randomSentence);
                int itemId = 1;
                intent.putExtra("itemId", itemId);
                startActivity(intent);
            }
        });

        //load new word to learn new
        Random random1 = new Random();
        int randomInt = random1.nextInt(vocabulary.length);

        String randomWord = vocabulary[randomInt];

        TextView newWord = findViewById(R.id.wordText);
        newWord.setText(randomWord);

        //load news
        newsTextIv = findViewById(R.id.newsText);


        //recyclerview click listener
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                Log.d(TAG, "onItemClick: item is clicked successfully");
                if (position == 0){
                    Intent intent = new Intent(getApplicationContext(), VocaActivity.class);
                    intent.putExtra("vocabulary", vocabulary);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Vocabulary will be added later",Toast.LENGTH_SHORT).show();
                }
                if(position == 1){
                    Intent intent = new Intent(FirstActivity.this, TranslatorActivity.class);
                    startActivity(intent);
                }
                else if(position == 2){
                    Toast.makeText(getApplicationContext(),"favorite item will be added later",Toast.LENGTH_SHORT).show();
                }
                else if(position == 3){
                    Intent intent = new Intent(FirstActivity.this, SecondActivity3.class);
                    startActivity(intent);
                }
                if (position == 4){
                    Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                    startActivity(intent);
                }
                else if(position == 5){
                    Intent intent = new Intent(FirstActivity.this, SecondActivity2.class);
                    startActivity(intent);
                }
                else if(position == 6){
                    Toast.makeText(getApplicationContext(),"grammar will be added later",Toast.LENGTH_SHORT).show();
                }
                else if (position == 7){
                    shareApp();
                }
                else if(position == 8){
                    Uri uri = Uri.parse("https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName());
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);

                    try {
                        startActivity(intent);
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(),"Ubanle to rate this app",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    //options menu button
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

    //app backpressed method
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

    //internet connection checker
    private boolean isConnectedToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }


    public String getNews(){
        NewsApiClient newsApiClient = new NewsApiClient("31c362dcb63e49f78af32ac96706a39c");
        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .q("trump")
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        String news1, news2;
                        news1 = response.getArticles().get(1).getTitle();
                        news2 = response.getArticles().get(2).getTitle();
                        news = news2 +". "+ news1;
                        newsTextIv.setText(news);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }
                }
        );
        return news;
    }

}