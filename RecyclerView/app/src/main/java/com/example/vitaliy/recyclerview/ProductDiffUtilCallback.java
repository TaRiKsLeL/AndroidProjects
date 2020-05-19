package com.example.vitaliy.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.util.Log;

import java.util.List;

public class ProductDiffUtilCallback extends DiffUtil.Callback {

    private final List<Products> oldList;
    private final List<Products> newList;
    private final String KEY_NEW_TITLE = "new_title";


    public ProductDiffUtilCallback(List<Products> oldList, List<Products> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Products oldProduct = oldList.get(oldItemPosition);
        Products newProduct = newList.get(newItemPosition);
        return oldProduct.getId() == newProduct.getId();
    }

    //This method is called by DiffUtil only if areItemsTheSame returns true.
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Products oldProduct = oldList.get(oldItemPosition);
        Products newProduct = newList.get(newItemPosition);
        return oldProduct.getTitle().equals(newProduct.getTitle())
                && oldProduct.getDesc().equals(newProduct.getDesc());
    }

    // If areItemTheSame return true and areContentsTheSame
    // returns false DiffUtil calls this method to get a
    // payload about the change.
    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        Products newProduct = newList.get(newItemPosition);
        Products oldProduct = oldList.get(oldItemPosition);
        Bundle diffBundle = new Bundle();
        if (!(oldProduct.getTitle().equals(newProduct.getTitle())))
            diffBundle.putString(KEY_NEW_TITLE, newProduct.getTitle());
        return diffBundle;
    }
}