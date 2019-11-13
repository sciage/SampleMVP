package com.example.sampleapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sampleapp.rx.PeoplePojo;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private List<PeoplePojo> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        mData = new ArrayList<>();
    }

    public void addAll(List<PeoplePojo> moveResults) {
        for (PeoplePojo result : moveResults) {
            add(result);
        }

    }

    public void add(PeoplePojo r) {
        mData.add(r);
        notifyItemInserted(mData.size() - 1);
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Glide.with(holder.itemView.getContext()).asDrawable()
                .load(mData.get(position).getUrl())
                .error(R.drawable.ic_clear_black_24dp)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.avatar_url);

        holder.user_location.setText(mData.get(position).getLocation());
        holder.user_age.setText(mData.get(position).getAge());
        holder.user_name.setText(mData.get(position).getName());

    }



    // total number of rows
    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView avatar_url;
        private TextView user_location;
        private TextView user_age;
        private TextView user_name;

        ViewHolder(View itemView) {
            super(itemView);
            avatar_url = itemView.findViewById(R.id.avatar_url);
            user_location = itemView.findViewById(R.id.user_location);
            user_age = itemView.findViewById(R.id.user_age);
            user_name = itemView.findViewById(R.id.user_name);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
  //  String getItem(int id) {
    //    return mData.get(id);
   // }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
