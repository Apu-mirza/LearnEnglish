package com.example.learnenglish.Activity;


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

import androidx.appcompat.app.AppCompatActivity;

import com.example.learnenglish.R;
import com.google.android.gms.ads.AdView;

public class SecondActivity2 extends AppCompatActivity {

    ListView listView;
    AdView mAdView;
    String[] proverb_a, proverb_b, proverb_c, proverb_d, proverb_e,proverb_f,proverb_g,proverb_h, proverb_i, proverb_jkl, 
            proverb_m, proverb_n, proverb_o,proverb_pqr,proverb_s,proverb_t, proverb_uv, proverb_w, proverb_y;
    String[] proverb_meaning_a, proverb_meaning_b, proverb_meaning_c, proverb_meaning_d, proverb_meaning_e,proverb_meaning_f,proverb_meaning_g,proverb_meaning_h, proverb_meaning_i, proverb_meaning_jkl, 
            proverb_meaning_m, proverb_meaning_n, proverb_meaning_o,proverb_meaning_pqr,proverb_meaning_s,proverb_meaning_t, proverb_meaning_uv, proverb_meaning_w, proverb_meaning_y;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Proverbs");
        setContentView(R.layout.activity_second2);

        String[] sub_topics2 = getResources().getStringArray(R.array.sub_topics2);

        listView = findViewById(R.id.listView2);

        proverb_a = getResources().getStringArray(R.array.proverb_a);
        proverb_meaning_a = getResources().getStringArray(R.array.proverb_meaning_a);
        proverb_b = getResources().getStringArray(R.array.proverb_b);
        proverb_meaning_b = getResources().getStringArray(R.array.proverb_meaning_b);
        proverb_c = getResources().getStringArray(R.array.proverb_c);
        proverb_meaning_c = getResources().getStringArray(R.array.proverb_meaning_c);
        proverb_d = getResources().getStringArray(R.array.proverb_d);
        proverb_meaning_d = getResources().getStringArray(R.array.proverb_meaning_d);
        proverb_e = getResources().getStringArray(R.array.proverb_e);
        proverb_meaning_e = getResources().getStringArray(R.array.proverb_meaning_e);
        proverb_f = getResources().getStringArray(R.array.proverb_f);
        proverb_meaning_f = getResources().getStringArray(R.array.proverb_meaning_f);
        proverb_g = getResources().getStringArray(R.array.proverb_g);
        proverb_meaning_g = getResources().getStringArray(R.array.proverb_meaning_g);
        proverb_h = getResources().getStringArray(R.array.proverb_h);
        proverb_meaning_h = getResources().getStringArray(R.array.proverb_meaning_h);
        proverb_i = getResources().getStringArray(R.array.proverb_i);
        proverb_meaning_i = getResources().getStringArray(R.array.proverb_meaning_i);
        proverb_jkl = getResources().getStringArray(R.array.proverb_j_k_l);
        proverb_meaning_jkl = getResources().getStringArray(R.array.proverb_meaning_j_k_l);
        proverb_m = getResources().getStringArray(R.array.proverb_m);
        proverb_meaning_m = getResources().getStringArray(R.array.proverb_meaning_m);
        proverb_n = getResources().getStringArray(R.array.proverb_n);
        proverb_meaning_n = getResources().getStringArray(R.array.proverb_meaning_n);
        proverb_o = getResources().getStringArray(R.array.proverb_o);
        proverb_meaning_o = getResources().getStringArray(R.array.proverb_meaning_o);
        proverb_pqr = getResources().getStringArray(R.array.proverb_p_q_r);
        proverb_meaning_pqr = getResources().getStringArray(R.array.proverb_meaning_p_q_r);
        proverb_s = getResources().getStringArray(R.array.proverb_s);
        proverb_meaning_s = getResources().getStringArray(R.array.proverb_meaning_s);
        proverb_t = getResources().getStringArray(R.array.proverb_t);
        proverb_meaning_t = getResources().getStringArray(R.array.proverb_meaning_t);
        proverb_uv = getResources().getStringArray(R.array.proverb_u_v);
        proverb_meaning_uv = getResources().getStringArray(R.array.proverb_meaning_u_v);
        proverb_w = getResources().getStringArray(R.array.proverb_w);
        proverb_meaning_w = getResources().getStringArray(R.array.proverb_meaning_w);
        proverb_y = getResources().getStringArray(R.array.proverb_y);
        proverb_meaning_y = getResources().getStringArray(R.array.proverb_meaning_y);
        



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_sample,R.id.listText, sub_topics2);
        listView.setAdapter(adapter);

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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
                if (position == 0){
                    intent.putExtra("dataHeader",proverb_a);
                    intent.putExtra("dataChild",proverb_meaning_a);
                    intent.putExtra("title", "Proverb starts with A");
                    startActivity(intent);
                } else if (position == 1) {
                    intent.putExtra("dataHeader",proverb_b);
                    intent.putExtra("dataChild",proverb_meaning_b);
                    intent.putExtra("title", "Proverb starts with B");
                    startActivity(intent);
                } else if (position == 2) {
                    intent.putExtra("dataHeader",proverb_c);
                    intent.putExtra("dataChild",proverb_meaning_c);
                    intent.putExtra("title", "Proverb starts with C");
                    startActivity(intent);
                } else if (position == 3) {
                    intent.putExtra("dataHeader",proverb_d);
                    intent.putExtra("dataChild",proverb_meaning_d);
                    intent.putExtra("title", "Proverb starts with D");
                    startActivity(intent);
                } else if (position == 4) {
                    intent.putExtra("dataHeader",proverb_e);
                    intent.putExtra("dataChild",proverb_meaning_e);
                    intent.putExtra("title", "Proverb starts with E");
                    startActivity(intent);
                } else if (position == 5) {
                    intent.putExtra("dataHeader",proverb_f);
                    intent.putExtra("dataChild",proverb_meaning_f);
                    intent.putExtra("title", "Proverb starts with F");
                    startActivity(intent);
                } else if (position == 6) {
                    intent.putExtra("dataHeader",proverb_g);
                    intent.putExtra("dataChild",proverb_meaning_g);
                    intent.putExtra("title", "Proverb starts with G");
                    startActivity(intent);
                } else if (position == 7) {
                    intent.putExtra("dataHeader",proverb_h);
                    intent.putExtra("dataChild",proverb_meaning_h);
                    intent.putExtra("title", "Proverb starts with H");
                    startActivity(intent);
                } else if (position == 8) {
                    intent.putExtra("dataHeader",proverb_i);
                    intent.putExtra("dataChild",proverb_meaning_i);
                    intent.putExtra("title", "Proverb starts with I");
                    startActivity(intent);
                } else if (position == 9) {
                    intent.putExtra("dataHeader",proverb_jkl);
                    intent.putExtra("dataChild",proverb_meaning_jkl);
                    intent.putExtra("title", "Proverb starts with J,K,L");
                    startActivity(intent);
                } else if (position == 10) {
                    intent.putExtra("dataHeader",proverb_m);
                    intent.putExtra("dataChild",proverb_meaning_m);
                    intent.putExtra("title", "Proverb starts with M");
                    startActivity(intent);
                } else if (position == 11) {
                    intent.putExtra("dataHeader",proverb_n);
                    intent.putExtra("dataChild",proverb_meaning_n);
                    intent.putExtra("title", "Proverb starts with N");
                    startActivity(intent);
                } else if (position == 12) {
                    intent.putExtra("dataHeader",proverb_o);
                    intent.putExtra("dataChild",proverb_meaning_o);
                    intent.putExtra("title", "Proverb starts with O");
                    startActivity(intent);
                } else if (position == 13) {
                    intent.putExtra("dataHeader",proverb_pqr);
                    intent.putExtra("dataChild",proverb_meaning_pqr);
                    intent.putExtra("title", "Proverb starts with P,Q,R");
                    startActivity(intent);
                } else if (position == 14) {
                    intent.putExtra("dataHeader",proverb_s);
                    intent.putExtra("dataChild",proverb_meaning_s);
                    intent.putExtra("title", "Proverb starts with S");
                    startActivity(intent);
                } else if (position == 15) {
                    intent.putExtra("dataHeader",proverb_t);
                    intent.putExtra("dataChild",proverb_meaning_t);
                    intent.putExtra("title", "Proverb starts with T");
                    startActivity(intent);
                } else if (position == 16) {
                    intent.putExtra("dataHeader",proverb_uv);
                    intent.putExtra("dataChild",proverb_meaning_uv);
                    intent.putExtra("title", "Proverb starts with U,V");
                    startActivity(intent);
                } else if (position == 17) {
                    intent.putExtra("dataHeader",proverb_w);
                    intent.putExtra("dataChild",proverb_meaning_w);
                    intent.putExtra("title", "Proverb starts with W");
                    startActivity(intent);
                } else if (position == 18) {
                    intent.putExtra("dataHeader",proverb_y);
                    intent.putExtra("dataChild",proverb_meaning_y);
                    intent.putExtra("title", "Proverb starts with Y");
                    startActivity(intent);
                }
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