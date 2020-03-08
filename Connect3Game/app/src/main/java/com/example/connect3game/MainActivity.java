package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //0: yellow, 1: red

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    int activePlayer = 0;

    boolean activeGame = true;

    ArrayList<View> viewsToToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewsToToggle = new ArrayList();

        viewsToToggle.add(findViewById(R.id.playAgainButton));
        viewsToToggle.add(findViewById(R.id.winnerTextView));
    }

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] != 2 || !activeGame) return;

        gameState[tappedCounter] = activePlayer;

        counter.setTranslationY(-1500);
        setImageAndActivePlayer(counter);
        counter.animate().translationYBy(1500).rotation(360).setDuration(500);
        winCheck();
    }

    private void setImageAndActivePlayer(ImageView counter) {
        if (activePlayer == 0) {
            counter.setImageResource(R.drawable.yellow);
            activePlayer = 1;
        } else if (activePlayer == 1) {
            counter.setImageResource(R.drawable.red);
            activePlayer = 0;
        }
    }

    private void winCheck() {
        for (int[] winningPosition : winningPositions) {
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                    && gameState[winningPosition[0]] != 2) {

                // Someone has won
                String winner = "";

                if (activePlayer == 0) {
                    winner = "Red";
                } else if (activePlayer == 1) {
                    winner = "Yellow";
                }
                activeGame = false;

                ((TextView)findViewById(R.id.winnerTextView)).setText(winner + " has won!");

                setArrayElemsVisability(View.VISIBLE);

            }
        }
    }

    public void playAgain(View view) {
        setArrayElemsVisability(View.INVISIBLE);

        androidx.gridlayout.widget.GridLayout gridLayout = (androidx.gridlayout.widget.GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i<gridLayout.getChildCount(); i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        activePlayer=0;
        activeGame=true;
    }

    private void setArrayElemsVisability(int visability) {
        for (View view : viewsToToggle) {
            view.setVisibility(visability);
        }
    }
}