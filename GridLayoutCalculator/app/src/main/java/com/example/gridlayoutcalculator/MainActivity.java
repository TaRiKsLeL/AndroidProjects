package com.example.gridlayoutcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.*;


public class MainActivity extends AppCompatActivity {

    TextView mainTextView;
    TextView expressionTextView;
    StringBuilder stringBuilder;
    double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainTextView = findViewById(R.id.mainTextView);
        expressionTextView = findViewById(R.id.expressionTextView);
        stringBuilder=new StringBuilder();
    }

    public void onClick(View view){

        String viewTag = view.getTag().toString();

        if(viewTag.equals(getResources().getString(R.string.clear))){           //IF Clear

            cleanStringBuilders(stringBuilder);
            cleanTextViews(mainTextView,expressionTextView);
            mainTextView.setText(stringBuilder);
            return;

        }else if(Character.isDigit(viewTag.charAt(0))){                         //IF DIGIT

            if(stringBuilder.toString().equals(result+"")){
                cleanStringBuilders(stringBuilder);
                cleanTextViews(mainTextView,expressionTextView);
            }
            stringBuilder.append(viewTag);
            mainTextView.setText(stringBuilder);

        }else if(viewTag.equals(getResources().getString(R.string.equal))){     //IF =

            result = new Expression(stringBuilder.toString()).calculate();

            expressionTextView.setText(stringBuilder);
            cleanStringBuilders(stringBuilder);
            stringBuilder.append(result);
            mainTextView.setText(stringBuilder);

        }else {
            stringBuilder.append(viewTag);
            mainTextView.setText(stringBuilder);
        }

    }

    private void cleanStringBuilders(StringBuilder ... stringBuilders){
        for(StringBuilder stringBuilder : stringBuilders){
            stringBuilder.delete(0,stringBuilder.length());
        }
    }

    private void cleanTextViews(TextView ... textViews){
        for(TextView textView : textViews){
            textView.setText("");
        }
    }
}
