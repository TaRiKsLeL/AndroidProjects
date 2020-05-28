package com.example.hikewatch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //===========< UI

    ArrayList<TextView> textViews;


    //===========
    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startListening();
        }
    }

    private void startListening() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupTextViews();


        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.i("Location", location.toString());

                fillTextViews(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        };

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                startListening();
                Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                fillTextViews(lastKnownLocation);

            }

    }

    private void setupTextViews() {
        textViews = new ArrayList<>();

        textViews.add((TextView)findViewById(R.id.latitudeTextView));
        textViews.add((TextView)findViewById(R.id.longitudeTextView));
        textViews.add((TextView)findViewById(R.id.accuracyTextView));
        textViews.add((TextView)findViewById(R.id.altitudeTextView));
       // textViews.add((TextView)findViewById(R.id.addressTextView));

    }

    private void fillTextViews(Location location){

        for (TextView textView:textViews){
            switch (textView.getId()){
                case R.id.latitudeTextView: textView.setText("Latitude: "+location.getLatitude()+"");break;
                case R.id.longitudeTextView: textView.setText("Longitude: "+location.getLongitude()+"");break;
                case R.id.accuracyTextView: textView.setText("Accuracy: "+location.getAccuracy()+"");break;
                case R.id.altitudeTextView: textView.setText("Altitude: "+location.getAltitude()+"");break;
            }
        }


        Geocoder geocoder = new Geocoder(getApplicationContext(),Locale.getDefault());

        try {
            List<Address> addressList = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);

            String address ="";

            if(addressList!=null && addressList.size()>0 ){

                if(addressList.get(0).getThoroughfare()!=null){
                    address += addressList.get(0).getThoroughfare();
                }
                if(addressList.get(0).getLocality()!=null){
                    address+=addressList.get(0).getLocality();
                }

                ((TextView)findViewById(R.id.addressTextView)).setText("Address: "+address);

            }else{
                ((TextView)findViewById(R.id.addressTextView)).setText("Couldn't find the address");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
