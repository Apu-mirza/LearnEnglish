package com.example.learnenglish.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.learnenglish.R;
import com.google.android.gms.ads.AdView;


public class SecondActivity4 extends AppCompatActivity {

    ListView listView;
    AdView mAdView;
    String words, sentence, parts_of_speech, tense, article, phrase, number, clauses, conditionals, modalAuxiliaries,
            mood, subject_verb_agreement, right_form_verb, modifiers, narration, negation, inversion, punctuation;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Learn Grammar Basics");
        setContentView(R.layout.activity_second4);

        String[] grammarTopics = getResources().getStringArray(R.array.grammar);

        listView = findViewById(R.id.listView4);

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

        words = getResources().getString(R.string.word);




        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_sample,R.id.listText, grammarTopics);
        listView.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //adding extra space after listview end
        View footerView = new View(this); // Create an empty view
        int footerHeight = 400; // Adjust this value according to your needs

        // Set the height of the footer view
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, footerHeight);
        footerView.setLayoutParams(layoutParams);
        listView.addFooterView(footerView); // Add the footer view to the ListView
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // Not needed for this implementation
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount) {
                    if (footerView != null) {
                        footerView.setVisibility(View.VISIBLE); // Show the footer view
                    }
                } else {
                    // Last item is not visible, hide the empty footer view if necessary
                    if (footerView != null) {
                        footerView.setVisibility(View.GONE); // Hide the footer view
                    }
                }
            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
                if (position == 0){
                    Intent intent1 = new Intent(getApplicationContext(), GrammarActivity.class);
                    startActivity(intent1);
                }
//                else if (position == 1) {
//                    intent.putExtra("dataHeader",phrase_b);
//                    intent.putExtra("dataChild",phrase_example_b);
//                    intent.putExtra("title", "Idioms starts with B");
//                    startActivity(intent);
//                } else if (position == 2) {
//                    intent.putExtra("dataHeader",phrase_c);
//                    intent.putExtra("dataChild",phrase_example_c);
//                    intent.putExtra("title", "Idioms starts with C");
//                    startActivity(intent);
//                } else if (position == 3) {
//                    intent.putExtra("dataHeader",phrase_d);
//                    intent.putExtra("dataChild",phrase_example_d);
//                    intent.putExtra("title", "Idioms starts with D");
//                    startActivity(intent);
//                } else if (position == 4) {
//                    intent.putExtra("dataHeader",phrase_e);
//                    intent.putExtra("dataChild",phrase_example_e);
//                    intent.putExtra("title", "Idioms starts with E");
//                    startActivity(intent);
//                } else if (position == 5) {
//                    intent.putExtra("dataHeader",phrase_f);
//                    intent.putExtra("dataChild",phrase_example_f);
//                    intent.putExtra("title", "Idioms starts with F");
//                    startActivity(intent);
//                } else if (position == 6) {
//                    intent.putExtra("dataHeader",phrase_f);
//                    intent.putExtra("dataChild",phrase_example_f);
//                    intent.putExtra("title", "Idioms starts with G");
//                    startActivity(intent);
//                } else if (position == 7) {
//                    intent.putExtra("dataHeader",phrase_h);
//                    intent.putExtra("dataChild",phrase_example_h);
//                    intent.putExtra("title", "Idioms starts with H");
//                    startActivity(intent);
//                } else if (position == 8) {
//                    intent.putExtra("dataHeader",phrase_i);
//                    intent.putExtra("dataChild",phrase_example_i);
//                    intent.putExtra("title", "Idioms starts with I");
//                    startActivity(intent);
//                } else if (position == 9) {
//                    intent.putExtra("dataHeader",phrase_jkl);
//                    intent.putExtra("dataChild",phrase_example_jkl);
//                    intent.putExtra("title", "Idioms starts with J,K,L");
//                    startActivity(intent);
//                } else if (position == 10) {
//                    intent.putExtra("dataHeader",phrase_mn);
//                    intent.putExtra("dataChild",phrase_example_mn);
//                    intent.putExtra("title", "Idioms starts with M,N");
//                    startActivity(intent);
//                } else if (position == 11) {
//                    intent.putExtra("dataHeader",phrase_o);
//                    intent.putExtra("dataChild",phrase_example_o);
//                    intent.putExtra("title", "Idioms starts with O");
//                    startActivity(intent);
//                } else if (position == 12) {
//                    intent.putExtra("dataHeader",phrase_pq);
//                    intent.putExtra("dataChild",phrase_example_pq);
//                    intent.putExtra("title", "Idioms starts with P,Q");
//                    startActivity(intent);
//                } else if (position == 13) {
//                    intent.putExtra("dataHeader",phrase_rs);
//                    intent.putExtra("dataChild",phrase_example_rs);
//                    intent.putExtra("title", "Idioms starts with R,S");
//                    startActivity(intent);
//                } else if (position == 14) {
//                    intent.putExtra("dataHeader",phrase_tuvw);
//                    intent.putExtra("dataChild",phrase_example_tuvw);
//                    intent.putExtra("title", "Idioms starts with T,U,V,W");
//                    startActivity(intent);
//                }
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
            Toast.makeText(getApplicationContext(),"Ubanle to rate this app",Toast.LENGTH_SHORT).show();
        }
    }
}