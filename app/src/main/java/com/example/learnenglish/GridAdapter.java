package com.example.learnenglish;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {
        String[] topics;
        int[] icons;
        Context context;
        LayoutInflater layoutInflater;

        GridAdapter(Context context, String[] topics, int[] icons){
            this.context = context;
            this.topics = topics;
            this.icons = icons;
        }

        @Override
        public int getCount() {
            return topics.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            if (view==null){
                layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = layoutInflater.inflate(R.layout.grid_sample, viewGroup, false);
            }

            ImageView imageView = (ImageView) view.findViewById(R.id.imageViewId);
            TextView textView = (TextView) view.findViewById(R.id.headingId);

            imageView.setImageResource(icons[i]);
            textView.setText(topics[i]);


            return view;
        }


}



