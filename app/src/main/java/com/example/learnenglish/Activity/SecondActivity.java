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


public class SecondActivity extends AppCompatActivity {

    ListView listView;
    AdView mAdView;
    String[] phrase_a, phrase_b, phrase_c, phrase_d, phrase_e,phrase_f,phrase_g,phrase_h, phrase_i, phrase_jkl,
            phrase_mn, phrase_o,phrase_pq,phrase_rs,phrase_tuvw;
    String[] phrase_example_a, phrase_example_b, phrase_example_c, phrase_example_d, phrase_example_e,phrase_example_f,phrase_example_g,phrase_example_h, phrase_example_i, phrase_example_jkl,
            phrase_example_mn, phrase_example_o,phrase_example_pq,phrase_example_rs,phrase_example_tuvw;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Phrases & Idioms");
        setContentView(R.layout.activity_second);

        String[] sub_topics = getResources().getStringArray(R.array.sub_topics);

        listView = findViewById(R.id.listView);


        phrase_a = getResources().getStringArray(R.array.phrases_a);
        phrase_example_a = getResources().getStringArray(R.array.phrase_example_a);
        phrase_b = getResources().getStringArray(R.array.phrases_b);
        phrase_example_b = getResources().getStringArray(R.array.phrase_example_b);
        phrase_c = getResources().getStringArray(R.array.phrases_c);
        phrase_example_c = getResources().getStringArray(R.array.phrase_example_c);
        phrase_d = getResources().getStringArray(R.array.phrases_d);
        phrase_example_d = getResources().getStringArray(R.array.phrase_example_d);
        phrase_e = getResources().getStringArray(R.array.phrases_e);
        phrase_example_e = getResources().getStringArray(R.array.phrase_example_e);
        phrase_f = getResources().getStringArray(R.array.phrases_f);
        phrase_example_f = getResources().getStringArray(R.array.phrase_example_f);
        phrase_g = getResources().getStringArray(R.array.phrases_g);
        phrase_example_g = getResources().getStringArray(R.array.phrase_example_g);
        phrase_h = getResources().getStringArray(R.array.phrases_h);
        phrase_example_h = getResources().getStringArray(R.array.phrase_example_h);
        phrase_i = getResources().getStringArray(R.array.phrases_i);
        phrase_example_i = getResources().getStringArray(R.array.phrase_example_i);
        phrase_jkl = getResources().getStringArray(R.array.phrases_j_k_l);
        phrase_example_jkl = getResources().getStringArray(R.array.phrase_example_j_k_l);
        phrase_mn = getResources().getStringArray(R.array.phrases_m_n);
        phrase_example_mn = getResources().getStringArray(R.array.phrase_example_m_n);
        phrase_o = getResources().getStringArray(R.array.phrases_o);
        phrase_example_o = getResources().getStringArray(R.array.phrase_example_o);
        phrase_pq = getResources().getStringArray(R.array.phrases_p_q);
        phrase_example_pq = getResources().getStringArray(R.array.phrase_example_p_q);
        phrase_rs = getResources().getStringArray(R.array.phrases_r_s);
        phrase_example_rs = getResources().getStringArray(R.array.phrase_example_r_s);
        phrase_tuvw = getResources().getStringArray(R.array.phrases_t_u_v_w);
        phrase_example_tuvw = getResources().getStringArray(R.array.phrase_example_t_u_v_w);




        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_sample,R.id.listText, sub_topics);
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
                    intent.putExtra("dataHeader",phrase_a);
                    intent.putExtra("dataChild",phrase_example_a);
                    intent.putExtra("title", "Idioms starts with A");
                    startActivity(intent);
                } else if (position == 1) {
                    intent.putExtra("dataHeader",phrase_b);
                    intent.putExtra("dataChild",phrase_example_b);
                    intent.putExtra("title", "Idioms starts with B");
                    startActivity(intent);
                } else if (position == 2) {
                    intent.putExtra("dataHeader",phrase_c);
                    intent.putExtra("dataChild",phrase_example_c);
                    intent.putExtra("title", "Idioms starts with C");
                    startActivity(intent);
                } else if (position == 3) {
                    intent.putExtra("dataHeader",phrase_d);
                    intent.putExtra("dataChild",phrase_example_d);
                    intent.putExtra("title", "Idioms starts with D");
                    startActivity(intent);
                } else if (position == 4) {
                    intent.putExtra("dataHeader",phrase_e);
                    intent.putExtra("dataChild",phrase_example_e);
                    intent.putExtra("title", "Idioms starts with E");
                    startActivity(intent);
                } else if (position == 5) {
                    intent.putExtra("dataHeader",phrase_f);
                    intent.putExtra("dataChild",phrase_example_f);
                    intent.putExtra("title", "Idioms starts with F");
                    startActivity(intent);
                } else if (position == 6) {
                    intent.putExtra("dataHeader",phrase_f);
                    intent.putExtra("dataChild",phrase_example_f);
                    intent.putExtra("title", "Idioms starts with G");
                    startActivity(intent);
                } else if (position == 7) {
                    intent.putExtra("dataHeader",phrase_h);
                    intent.putExtra("dataChild",phrase_example_h);
                    intent.putExtra("title", "Idioms starts with H");
                    startActivity(intent);
                } else if (position == 8) {
                    intent.putExtra("dataHeader",phrase_i);
                    intent.putExtra("dataChild",phrase_example_i);
                    intent.putExtra("title", "Idioms starts with I");
                    startActivity(intent);
                } else if (position == 9) {
                    intent.putExtra("dataHeader",phrase_jkl);
                    intent.putExtra("dataChild",phrase_example_jkl);
                    intent.putExtra("title", "Idioms starts with J,K,L");
                    startActivity(intent);
                } else if (position == 10) {
                    intent.putExtra("dataHeader",phrase_mn);
                    intent.putExtra("dataChild",phrase_example_mn);
                    intent.putExtra("title", "Idioms starts with M,N");
                    startActivity(intent);
                } else if (position == 11) {
                    intent.putExtra("dataHeader",phrase_o);
                    intent.putExtra("dataChild",phrase_example_o);
                    intent.putExtra("title", "Idioms starts with O");
                    startActivity(intent);
                } else if (position == 12) {
                    intent.putExtra("dataHeader",phrase_pq);
                    intent.putExtra("dataChild",phrase_example_pq);
                    intent.putExtra("title", "Idioms starts with P,Q");
                    startActivity(intent);
                } else if (position == 13) {
                    intent.putExtra("dataHeader",phrase_rs);
                    intent.putExtra("dataChild",phrase_example_rs);
                    intent.putExtra("title", "Idioms starts with R,S");
                    startActivity(intent);
                } else if (position == 14) {
                    intent.putExtra("dataHeader",phrase_tuvw);
                    intent.putExtra("dataChild",phrase_example_tuvw);
                    intent.putExtra("title", "Idioms starts with T,U,V,W");
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
                MyHelper.shareApp(SecondActivity.this);
                return true;
            case R.id.rate_app1:
                Toast.makeText(getApplicationContext(),"rate is clicked",Toast.LENGTH_SHORT).show();
                MyHelper.rateApp(SecondActivity.this);
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