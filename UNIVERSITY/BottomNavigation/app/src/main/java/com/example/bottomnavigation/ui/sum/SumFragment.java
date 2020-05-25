package com.example.bottomnavigation.ui.sum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.bottomnavigation.MathExt;
import com.example.bottomnavigation.R;

public class SumFragment extends Fragment {

    private SumViewModel sumViewModel;
    View root;

    String result="";

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("result",result);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState!=null){
            ((TextView)root.findViewById(R.id.resultTV)).setText(savedInstanceState.getString("result"));
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sumViewModel =
                ViewModelProviders.of(this).get(SumViewModel.class);
        root = inflater.inflate(R.layout.fragment_sum, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        sumViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        Button submitBtn = (Button)root.findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText firdigitET = (EditText)root.findViewById(R.id.firstDigitET);
                EditText secdigitET = (EditText)root.findViewById(R.id.secondDigitET);
                String digitFirst = firdigitET.getText().toString();
                String digitSec = secdigitET.getText().toString();
                result = MathExt.sumBinaryStrings(digitFirst,digitSec);
                ((TextView)root.findViewById(R.id.resultTV)).setText(result);
            }
        });


        return root;
    }
}
