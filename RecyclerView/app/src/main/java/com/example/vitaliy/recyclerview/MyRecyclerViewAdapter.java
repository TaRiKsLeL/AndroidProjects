package com.example.vitaliy.recyclerview;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class MyRecyclerViewAdapter extends
        RecyclerView.Adapter<MyRecyclerViewAdapter.myViewHolder> {

     List<Products> mData;
    private final String KEY_NEW_TITLE = "new_title";



    public MyRecyclerViewAdapter(List<Products> Data) {
        mData = Data;
    }

    public void setmData(List<Products> Data) {
        mData = Data;
    }

    public List<Products> getmData() {
        return mData;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i) {
        Log.d("mybind", "bind, position = " + i);
        myViewHolder.myTextView.setText(mData.get(i).getTitle());
        myViewHolder.myDesc.setText(mData.get(i).getDesc());

    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position,
                                 @NonNull List<Object> payloads) {
        if(payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
        }
        else {
            Bundle o = (Bundle)payloads.get(0);
            for(String key : o.keySet()) {
                if(key.equals(KEY_NEW_TITLE)) {
                    holder.myTextView.setText(mData.get(position).getTitle());
                    holder.myTextView.setTextColor(
                            holder.myTextView.getResources().getColor(R.color.colorAccent));
                    holder.myDesc.setText(mData.get(position).getDesc());
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_item_layout, null);

        // create ViewHolder

        myViewHolder viewHolder = new myViewHolder(itemLayoutView);
        return viewHolder;
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView;
        TextView myDesc;
        public RelativeLayout viewBackground, viewForeground;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.myTextView);
            myDesc = itemView.findViewById(R.id.myDesc);
            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);
        }
    }

    public void removeItem(int position) {
        mData.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(Products item, int position) {
        mData.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }
}
