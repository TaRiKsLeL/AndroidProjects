package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int maxRangeValue=20;
    int startTimerState=30;
    //--------------------------------------------//
    ArrayList<View> beginningViews;
    ArrayList<View> gameViews;
    ArrayList<Button> answerButtons;

    Task currentTask;
    int score;
    int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLists();
        changeViewsVisibility(beginningViews,View.VISIBLE);
        changeViewsVisibility(gameViews,View.INVISIBLE);
    }
    private void initLists() {

        beginningViews=new ArrayList<>();
        Button startButton = findViewById(R.id.startButton);
        beginningViews.add(startButton);

        gameViews=new ArrayList<>();
        GridLayout gridLayout = findViewById(R.id.buttonsGridLayout);
        TextView timerTextView = findViewById(R.id.timerTextView);
        TextView taskTextView = findViewById(R.id.taskTextView);
        TextView scoreTextView = findViewById(R.id.scoreTextView);
        //Button playAgainButton=findViewById(R.id.playAgainButton);

        gameViews.add(gridLayout);
        gameViews.add(timerTextView);
        gameViews.add(taskTextView);
        gameViews.add(scoreTextView);
        //gameViews.add(playAgainButton);

        answerButtons = new ArrayList<>();
        for(int i=0;i<gridLayout.getChildCount();i++){
            answerButtons.add((Button) gridLayout.getChildAt(i));
        }
    }

    private void changeViewsVisibility(ArrayList<View> views, int visibility){
        for(View view : views){
            view.setVisibility(visibility);
        }
    }

    public void startGameClick(View view){
        changeViewsVisibility(beginningViews,View.INVISIBLE);
        changeViewsVisibility(gameViews,View.VISIBLE);

        TextView tipTextView = findViewById(R.id.tipTextView);
        tipTextView.setVisibility(View.VISIBLE);
        tipTextView.setText("Start");

        findViewById(R.id.playAgainButton).setVisibility(View.INVISIBLE);
        score=0; level=0;
        updateScoreTextView();

        currentTask = generateTask();
        updateTaskTextView();
        generateAnswers();
        startTimer();
    }

    public void guessAnswer(View view){
        level++;
        if(view.getTag().equals(currentTask.getAnswer())){
            score++;
            ((TextView)findViewById(R.id.tipTextView)).setText("Cool!");

            currentTask = generateTask();
            updateTaskTextView();
            generateAnswers();
        }else{
            ((TextView)findViewById(R.id.tipTextView)).setText("Wrong(");
        }
        updateScoreTextView();
    }

    private void updateScoreTextView() {
        ((TextView)findViewById(R.id.scoreTextView)).setText(score+"/"+level);
    }

    private void updateTaskTextView(){
        ((TextView)findViewById(R.id.taskTextView)).setText(currentTask.getTask());
    }

    private void generateAnswers() {
        int rightId = getRandomIntInRange(0,3);
        int i=0;
        for(Button button : answerButtons){

            if(i==rightId){
                button.setTag(currentTask.getAnswer());
                button.setText(currentTask.getAnswer()+"");
            }else{
                button.setTag(getRandomIntInRange(0,20));
                button.setText(getRandomIntInRange(0,20)+"");
            }
            i++;
        }
    }

    private void startTimer(){
        new CountDownTimer(startTimerState*1000,1000){

            TextView timerTextView = findViewById(R.id.timerTextView);
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(millisUntilFinished/1000+"s");
                Log.i("Info",millisUntilFinished/1000+"");
            }

            @Override
            public void onFinish() {
                findViewById(R.id.playAgainButton).setVisibility(View.VISIBLE);
                ((TextView)findViewById(R.id.tipTextView)).setText("Done!");
            }
        }.start();
    }

    private Task generateTask() {
        Task task = new Task();

        int first = getRandomIntInRange(1,maxRangeValue);
        int second = getRandomIntInRange(1,maxRangeValue);

        switch (getRandomIntInRange(1,2)){
            case 1:
                {
                    task.setSign("+");
                    task.setAnswer(first+second);
                    break;
                }
            case 2:
                {
                    task.setSign("-");
                    task.setAnswer(first-second);
                    break;
                }
            default:
                {
                    task.setSign("+");
                    task.setAnswer(first+second);
                    break;
                }
        }

        task.setFirst(first);
        task.setSecond(second);

        return task;
    }
    private int getRandomIntInRange(int min,int max){
        return new Random().nextInt((max - min) + 1) + min;
    }
}
