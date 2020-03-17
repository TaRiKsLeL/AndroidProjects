package com.example.guesscelebrity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.CellIdentity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.*;

public class MainActivity extends AppCompatActivity {

    public static String CELEBS_LINK = "https://www.careeraddict.com/highest-paid-celebrities";

    //------------------------< UI

    ImageView imageView;
    ArrayList<Button> buttons;

    //------------------------< COLLECTIONS

    ArrayList<String> names;
    ArrayList<Bitmap> bitmaps;
    ArrayList<Celebrity> celebrities;

    //------------------------<
    Random random;
    Celebrity currCeleb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        random = new Random();
        imageView = findViewById(R.id.imageView);
        initButtons();

        DataDownloader task = new DataDownloader();
        try {
            celebrities= task.execute(CELEBS_LINK).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setupRandCelebWithBtns();
        //startTimer();
    }

    public void guessClick(View view){
        if(((Button)view).getText().equals(currCeleb.getName())){
            setupRandCelebWithBtns();
            Toast.makeText(this,"Wow!!!",Toast.LENGTH_SHORT).show();
        }else{
            setupRandCelebWithBtns();
            Toast.makeText(this,"NOPE(",Toast.LENGTH_SHORT).show();
        }
    }

    private void setupRandCelebWithBtns() {

        currCeleb = celebrities.get(random.nextInt(celebrities.size()));

        int rightIndex = random.nextInt(buttons.size());

        int i=0;
        for(Button button: buttons){

            if(i==rightIndex){
                button.setText(currCeleb.getName());
                i++;
                continue;
            }

            String randCelebName = "";
            do{
                randCelebName = celebrities.get(random.nextInt(celebrities.size())).getName();
            }while (currCeleb.getName().equals(randCelebName));
            button.setText(randCelebName);

            i++;
        }

        imageView.setImageBitmap(currCeleb.getBitmap());

    }

    private void initButtons() {
        buttons = new ArrayList<>();

        buttons.add((Button) findViewById(R.id.firstBtn));
        buttons.add((Button) findViewById(R.id.scndBtn));
        buttons.add((Button) findViewById(R.id.thirdBtn));
        buttons.add((Button) findViewById(R.id.forthBtn));
    }

    private void startTimer(){
        new CountDownTimer(celebrities.size()*1000,1000){

            int i=0;
            @Override
            public void onTick(long millisUntilFinished) {
                 imageView.setImageBitmap(celebrities.get(i).getBitmap());
                 Log.i("Info", i+ "   "+celebrities.get(i).getName());
                 i++;
            }

            @Override
            public void onFinish() {
            }
        }.start();
    }


    public class DataDownloader extends AsyncTask<String, Void, ArrayList<Celebrity>>{

        @Override
        protected ArrayList<Celebrity> doInBackground(String... urls) {

            try {
                String html = getHTMLByURL(new URL(urls[0]));

                names = getNames(html);
                bitmaps = getBitmaps(html);

                Log.i("Info", "Names: "+names.size()+"    Bitmaps: "+bitmaps.size());

                ArrayList<Celebrity> celebrities = createCelebritiesByArrays(names,bitmaps);

                return celebrities;

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        private String getHTMLByURL(URL url){
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                String input;
                StringBuffer stringBuffer = new StringBuffer();
                while ((input = in.readLine()) != null)
                {
                    stringBuffer.append(input);
                }
                in.close();
            return stringBuffer.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        private ArrayList<String> getNames(String html){
            ArrayList<String> names = new ArrayList<>();

            Pattern p = Pattern.compile("<h2>(.*?)</h2>");
            Matcher m = p.matcher(html);

            while(m.find()){
                names.add(m.group(1));
            }

            return names;
        }

        public ArrayList<Bitmap> getBitmaps(String html){
            ArrayList<Bitmap> bitmaps = new ArrayList<>();

            Pattern p = Pattern.compile("data-src=\"(.*?)\"");
            Matcher m = p.matcher(html);

            while(m.find()){
                Bitmap bitmap =getBitmapByLink("https:"+m.group(1));

                bitmaps.add(bitmap);
            }

            return bitmaps;
        }

        private Bitmap getBitmapByLink(String imageLink){
            try {
                URL url = new URL(imageLink);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (IOException e) {
                // Log exception
                return null;
            }
        }

        private ArrayList<Celebrity> createCelebritiesByArrays(ArrayList<String> names, ArrayList<Bitmap> images){
            ArrayList<Celebrity> celebrities = new ArrayList<>();

            int index=0;

            for (String name: names) {
                index++;
                celebrities.add(new Celebrity(name,images.get(index*2)));
            }

            return celebrities;
        }
    }
}
