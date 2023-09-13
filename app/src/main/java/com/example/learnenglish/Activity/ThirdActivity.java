package com.example.learnenglish.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import com.example.learnenglish.R;
import com.example.learnenglish.Adapter.ThirdAdapter;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ThirdActivity extends AppCompatActivity {

    private ListView listView;
    AdView mAdView;
    private ThirdAdapter adapter;
    List<String> listDataHeader;
    Activity MainActivity;
    HashMap<String, List<String>> listDataChild;
    String[] vocabulary;
    int clickedItemPosition = -1;
    private Typeface currentTypeface;
    private float currentTextSize;

    TextToSpeech textToSpeech;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String title = getIntent().getStringExtra("title");
        if (title != null) {
            setTitle(title);
        }
        setContentView(R.layout.activity_third);

        prePareData();

        vocabulary = getIntent().getStringArrayExtra("vocabulary");

        listView = findViewById(R.id.thirdListView);
        adapter = new ThirdAdapter(this, listDataHeader, listDataChild, clickedItemPosition);
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

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String fontStyle = sharedPreferences.getString("pref_font_style", "");
        String fontSize = sharedPreferences.getString("pref_font_size", "");

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    int result = textToSpeech.setLanguage(Locale.US);
                    if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Toast.makeText(getApplicationContext(),"Language Not Supported",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"Text to Speech conversion failed",Toast.LENGTH_LONG).show();
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setClickedItemPosition(position);
                String groupName = listDataHeader.get(position);
                String[] groupLines = groupName.split("\\r?\\t?\\n"); // Split the group text into lines
                String firstLine = groupLines[0]; // Extract the first line of the group
//                Toast.makeText(getApplicationContext(),firstLine,Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), " is clicked", Toast.LENGTH_SHORT).show();
                textToSpeech.setSpeechRate(1f);
                textToSpeech.speak(firstLine, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
    }
    public void prePareData(){

        String[] original = getIntent().getStringArrayExtra("dataHeader");
        String[] example = getIntent().getStringArrayExtra("dataChild");


        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        for(int i=0; i<original.length; i++){
            listDataHeader.add(original[i]);

            List<String> child = new ArrayList<>();
            child.add(example[i]);

            listDataChild.put(listDataHeader.get(i),child);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        MenuItem searchViewItem = menu.findItem(R.id.app_bar_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                if (listDataHeader.contains(query)){
                    adapter.getFilter().filter(query);
                }else {
                    Toast.makeText(getApplicationContext(),"No match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

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
                shareApp();
                return true;
            case R.id.rate_app:
                Toast.makeText(getApplicationContext(),"share is clicked",Toast.LENGTH_SHORT).show();
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
}
