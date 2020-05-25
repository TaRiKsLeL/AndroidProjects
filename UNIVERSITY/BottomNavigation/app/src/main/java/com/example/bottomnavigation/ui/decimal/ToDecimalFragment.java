package com.example.bottomnavigation.ui.decimal;

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

import com.example.bottomnavigation.NumberConverter;
import com.example.bottomnavigation.R;

public class ToDecimalFragment extends Fragment {

    private ToDecimalViewModel toDecimalViewModel;
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
        toDecimalViewModel =
                ViewModelProviders.of(this).get(ToDecimalViewModel.class);
        root = inflater.inflate(R.layout.fragment_decimal, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        toDecimalViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        Button submitBtn = (Button)root.findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText digitET = (EditText)root.findViewById(R.id.firstDigitET);
                String toChange = digitET.getText().toString();
                result = NumberConverter.toDecimal(toChange)+"";
                ((TextView)root.findViewById(R.id.resultTV)).setText(result);
            }
        });


        return root;
    }

}
