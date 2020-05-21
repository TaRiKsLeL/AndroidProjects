package com.example.bottomnavigation.ui.binary;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ToBinaryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ToBinaryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}