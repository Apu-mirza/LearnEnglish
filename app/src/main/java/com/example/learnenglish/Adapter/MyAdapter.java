package com.example.learnenglish.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnenglish.Activity.FirstActivity;
import com.example.learnenglish.Activity.MyHelper;
import com.example.learnenglish.Activity.SecondActivity;
import com.example.learnenglish.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.viewHolder> {

    Context context;
    int[] images;
    String[] titles;

    private OnItemClickListener itemClickListener;
    FirstActivity firstActivity;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }
    public MyAdapter(Context context, int[] images, String[] titles) {
        this.context = context;
        this.images = images;
        this.titles = titles;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.grid_sample, null, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.thumbnail.setImageResource(images[position]);
        holder.titleView.setText(titles[position]);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView thumbnail;
        TextView titleView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            thumbnail = itemView.findViewById(R.id.imageViewId);
            titleView = itemView.findViewById(R.id.headingId);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyHelper.setCustomBackground(v);
                    if (itemClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            itemClickListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
