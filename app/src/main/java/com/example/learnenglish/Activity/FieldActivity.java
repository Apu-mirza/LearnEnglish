package com.example.learnenglish.Activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnenglish.R;
import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

public class FieldActivity extends AppCompatActivity {
    TextView contentTextIv, authorTextIv, newsNameIv;
    String[] newsArray;
    String news, news1;
    LinearLayout linearLayout;
    String newsName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String title = getIntent().getStringExtra("fieldTitle");
        if (title != null) {
            setTitle(title);
        }
        setContentView(R.layout.activity_field);

        contentTextIv = findViewById(R.id.contentText);
        authorTextIv = findViewById(R.id.authorText);
        newsNameIv = findViewById(R.id.newsName);

        linearLayout = findViewById(R.id.newsBackgroundLayout);

        String content = getIntent().getStringExtra("content");
        int itemId = getIntent().getIntExtra("itemId", -1);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (itemId == -1){
            Toast.makeText(getApplicationContext(), "The item id "+itemId+" is invalid", Toast.LENGTH_LONG).show();
        }else if(itemId == 1){
            String[] contentLines = content.split("\\r?\\t?\\n");
            String contentText = contentLines[0];
            String authorText = contentLines[1];

            linearLayout.setBackground(null);
            contentTextIv.setText(contentText);
            authorTextIv.setText(authorText);
        } else if (itemId == 2) {
            CardView cardView = findViewById(R.id.cardviewId);
            ImageView imageBackground = findViewById(R.id.imageBackground);

            float newFontSize = 18;
            imageBackground.setImageDrawable(null);

            newsNameIv.setVisibility(View.VISIBLE);

            news1 = getNews();
            contentTextIv.setTextSize(newFontSize);
            contentTextIv.setText(news1);
        }



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
                            String news1, news2, news3,news4, news5;
                            for(int i=1; i<12; i++){
                                news1 = response.getArticles().get(i).getTitle();
                                news += news1+". ";
                            }

                            contentTextIv.setText(news);

                            contentTextIv.append("\nTo know more information, click the following link: ");

                            newsName = response.getArticles().get(1).getUrl();


                            // Create a SpannableString with the text you want to make clickable
                            SpannableString spannableString = new SpannableString("Visit Site");

                            // Create a ClickableSpan for the clickable portion
                            ClickableSpan clickableSpan = new ClickableSpan() {
                                @Override
                                public void onClick(View widget) {
                                    // Define the action to perform when the link is clicked
                                    // For example, open a URL in a web browser
                                    Uri uri = Uri.parse(newsName);
                                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(intent);
                                }
                            };

                            // Set the ClickableSpan on the SpannableString
                            spannableString.setSpan(clickableSpan, 0, spannableString.length(), 0);

                            // Set the SpannableString to the TextView
                            newsNameIv.setText(spannableString);

                            // Enable the TextView to handle clicks on the link
                            newsNameIv.setMovementMethod(LinkMovementMethod.getInstance());

                        }

                        @Override
                        public void onFailure(Throwable throwable) {
                            System.out.println(throwable.getMessage());
                        }
                    }
            );
            return news;
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
            case R.id.settings:
                Toast.makeText(getApplicationContext(),"settings is clicked",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.share_app:
                Toast.makeText(getApplicationContext(),"more app is clicked",Toast.LENGTH_SHORT).show();
                MyHelper.shareApp(FieldActivity.this);
                return true;
            case R.id.rate_app:
                Toast.makeText(getApplicationContext(),"share is clicked",Toast.LENGTH_SHORT).show();
                MyHelper.rateApp(FieldActivity.this);
                return true;
            case R.id.privacyId:
                Toast.makeText(getApplicationContext(),"privacy is clicked",Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(getApplicationContext(), PrivacyActivity.class);
                startActivity(intent1);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

}