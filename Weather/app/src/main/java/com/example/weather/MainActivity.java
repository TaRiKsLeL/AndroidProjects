package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    //------------------< UI

    EditText editText;
    TextView resultTextView;

    //------------------<
    public static String API_KEY = "aeca55359ace4e24dfe873cee9aa5c0c";
    public static String URL_PART1 = "https://api.openweathermap.org/data/2.5/weather?q=";
    public static String URL_PART2 = "&appid="+API_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.cityName);
        resultTextView = findViewById(R.id.weatherTextView);

    }

    public void getWeather(View view){
        DownloadTask task = new DownloadTask();
        try {
            resultTextView.setText(task.execute(URL_PART1+editText.getText()+URL_PART2).get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class DownloadTask extends AsyncTask<String, Void, String >{

        @Override
        protected String doInBackground(String... urls) {
            try {
                return getPageByURL(new URL(urls[0]));

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


        }

        private String getPageByURL(URL url){
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
    }
}
