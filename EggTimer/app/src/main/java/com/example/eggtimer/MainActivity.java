package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView timeTextView;
    SeekBar timeSeekBar;
    Button mainBtn;
    int baseSeconds = 30;

    CountDownTimer countDownTimer;
    boolean isCounting=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeSeekBar=findViewById(R.id.timeSeekBar);
        timeTextView=findViewById(R.id.timeTextView);
        mainBtn =findViewById(R.id.mainBtn);

        setupBase();

        timeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                timeTextView.setText(getMinutesEquivalent(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setupBase(){
        isCounting=false;
        mainBtn.setText("START!");
        timeSeekBar.setEnabled(true);
        timeSeekBar.setProgress(baseSeconds);
        timeTextView.setText(getMinutesEquivalent(baseSeconds));
    }

    public void onClick(View view){
        if(isCounting){
            countDownTimer.cancel();
            setupBase();

        }else{
            isCounting=true;

            timeSeekBar.setEnabled(false);
            mainBtn.setText("STOP!");
            countDownTimer = new CountDownTimer(timeSeekBar.getProgress()*1000,1000){

                @Override
                public void onTick(long millisUntilFinished) {
                    timeTextView.setText(getMinutesEquivalent(millisUntilFinished/1000));
                }

                @Override
                public void onFinish() {
                    MediaPlayer.create(getApplicationContext(),R.raw.airhorn).start();
                    setupBase();
                }
            }.start();
        }


    }

    private String getMinutesEquivalent(long seconds){
        String mins = String.valueOf(seconds/60);
        String secs = String.valueOf(seconds%60);

        if(Integer.parseInt(mins)<=9){
            mins ="0"+mins;
        }
        if(Integer.parseInt(secs)<=9){
            secs ="0"+secs;
        }

        return mins+":"+secs;
    }
}
