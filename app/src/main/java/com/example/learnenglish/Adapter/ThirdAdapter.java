package com.example.learnenglish.Adapter;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.learnenglish.Activity.SpeechActivity;
import com.example.learnenglish.R;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ThirdAdapter extends ArrayAdapter<String> {

    private int clickedItemPosition;
    TextToSpeech textToSpeech;
    private Typeface currentTypeface;
    Context context;
    SpeechRecognizer speechRecognizer;
    private List<String> itemList1;
    private HashMap<String, List<String>> itemList2;
    public ThirdAdapter(Context context, List<String> itemList1, HashMap<String, List<String>> itemList2, int clickedItemPosition) {
        super(context, 0, itemList1);
        this.itemList1 = itemList1;
        this.itemList2 = itemList2;
        this.context = context;
        this.clickedItemPosition = clickedItemPosition;
    }

    public void setClickedItemPosition(int position) {
        clickedItemPosition = position;
        notifyDataSetChanged();
    }

    private void showPopupMenu(View anchorView, final String dataToCopy) {
        PopupMenu popupMenu = new PopupMenu(getContext(), anchorView);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

        // Set item click listener
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.copyButton:
                        // Copy data to clipboard
                        ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("Data", dataToCopy);
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(getContext(), "Data copied to clipboard", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.shareButton:
                        // Share data
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, dataToCopy);
                        getContext().startActivity(Intent.createChooser(shareIntent, "Share via"));
                        return true;
                    default:
                        return false;
                }
            }
        });

        // Show the popup menu
        popupMenu.show();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.default_layout, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.imageButton1 = convertView.findViewById(R.id.micId);
            viewHolder.imageButton2 = convertView.findViewById(R.id.videoId);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Set click listener for default layout
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setClickedItemPosition(position);
            }
        });

        // Change the layout for the clicked item
        if (position == clickedItemPosition) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.clicked_layout, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.imageButton1 = convertView.findViewById(R.id.micId);
            viewHolder.imageButton2 = convertView.findViewById(R.id.videoId);
            viewHolder.imageButton3 = convertView.findViewById(R.id.hearingId);
            viewHolder.imageButton4 = convertView.findViewById(R.id.moreId);



            convertView.setTag(viewHolder);

            // Set click listeners for ImageButton objects in clicked layout
            viewHolder.textView = convertView.findViewById(R.id.item_text);
            viewHolder.textView2 = convertView.findViewById(R.id.item_text2);
            viewHolder.textView3 = convertView.findViewById(R.id.item_text3);

            String key = itemList1.get(position);
            String[] groupLines = key.split("\\r?\\t?\\n"); // Split the group text into lines
            String firstLine = groupLines[0]; // Extract the first line of the group
//            Toast.makeText(getContext(),firstLine,Toast.LENGTH_SHORT).show();
            viewHolder.textView.setText(firstLine);
            String secondLine = groupLines[1];
            viewHolder.textView2.setText(secondLine);

            textToSpeech = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if(status == TextToSpeech.SUCCESS){
                        int result = textToSpeech.setLanguage(Locale.US);
//                        if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
//                            Toast.makeText(getContext(),"Language Not Supported",Toast.LENGTH_LONG).show();
//                        }
                    }else {
                        Toast.makeText(getContext(),"Text to Speech conversion failed",Toast.LENGTH_LONG).show();
                    }
                }
            });

            List<String> values = itemList2.get(key);
            if (values != null && !values.isEmpty()) {
                String firstValue = values.get(0);
                viewHolder.textView3.setText(firstValue);

                ViewHolder finalViewHolder = viewHolder;
                viewHolder.imageButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finalViewHolder.imageButton2.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rounded_background));
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // Remove the rounded background
                                finalViewHolder.imageButton2.setBackground(null);
                            }
                        }, 700);
//                        Toast.makeText(getContext(), "audio button is clicked", Toast.LENGTH_SHORT).show();
                        textToSpeech.setSpeechRate(1f);
                        textToSpeech.speak(firstValue, TextToSpeech.QUEUE_FLUSH, null, null);
                    }
                });

                viewHolder.imageButton3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finalViewHolder.imageButton3.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rounded_background));
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // Remove the rounded background
                                finalViewHolder.imageButton3.setBackground(null);
                            }
                        }, 700);
//                        Toast.makeText(getContext(), "slow hearing button is clicked", Toast.LENGTH_SHORT).show();
                        textToSpeech.setSpeechRate(0.05f);
                        textToSpeech.speak(firstValue, TextToSpeech.QUEUE_FLUSH, null, null);
                    }
                });

                viewHolder.imageButton4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finalViewHolder.imageButton4.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rounded_background));
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // Remove the rounded background
                                finalViewHolder.imageButton4.setBackground(null);
                            }
                        }, 700);
//                        Toast.makeText(getContext(),"option button is clicked",Toast.LENGTH_SHORT).show();
                        showPopupMenu(v, firstValue);
                    }
                });

                viewHolder.imageButton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Toast.makeText(getContext(),"microphone button is clicked",Toast.LENGTH_SHORT).show();
//                        if (isConnectedToInternet()){
                        finalViewHolder.imageButton1.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rounded_background));
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // Remove the rounded background
                                finalViewHolder.imageButton1.setBackground(null);
                            }
                        }, 700);
                        Intent intent = new Intent(getContext(), SpeechActivity.class);
                        intent.putExtra("headerEnglish", firstLine);
                        intent.putExtra("headerBangla", secondLine);
                        intent.putExtra("dataChild", firstValue);
                        context.startActivity(intent);
//                        }else {
//                            Toast.makeText(getContext(),"Please connect to the internet",Toast.LENGTH_LONG).show();
//                        }
                    }
                });
            }



        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.default_layout, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.textView = convertView.findViewById(R.id.item_text);
            viewHolder.textView2 = convertView.findViewById(R.id.item_text2);


            convertView.setTag(viewHolder);
            String key = itemList1.get(position);
            String[] groupLines = key.split("\\r?\\t?\\n"); // Split the group text into lines
            String firstLine = groupLines[0]; // Extract the first line of the group
//            Toast.makeText(getContext(),firstLine,Toast.LENGTH_SHORT).show();
            viewHolder.textView.setText(firstLine);
            String secondLine = groupLines[1];
            viewHolder.textView2.setText(secondLine);
        }

        return convertView;
    }


    static class ViewHolder {
        ImageButton imageButton1;
        ImageButton imageButton2, imageButton3, imageButton4;
        TextView textView, textView2, originalTextView;
        TextView textView3;
    }

    private boolean isConnectedToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}