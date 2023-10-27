package com.example.learnenglish.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.learnenglish.R;

import org.w3c.dom.Text;

public class GrammarActivity extends AppCompatActivity {

    LinearLayout expandableLessonLayout, compressLayout;
    ImageView expandButton, compressButton;
    TextView lessonTitle, lessonContent, wordText;
    String word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar);

        wordText = findViewById(R.id.textWord);
        word = getResources().getString(R.string.word);

        wordText.setText(word);

    }
}