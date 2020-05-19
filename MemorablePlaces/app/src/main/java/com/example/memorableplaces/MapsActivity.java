package com.example.memorableplaces;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener{

    private static Context context;

    private GoogleMap mMap;
    private Marker myLocMarker;
    private Location myLocation;

    //===========

    public static final String DATE_FORMAT = "dd-M-yyyy hh:mm:ss";

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
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        context = getApplicationContext();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLongClickListener(this);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.i("Location", location.toString());

                myLocation = location;

                if (myLocMarker != null) {
                    myLocMarker.remove();
                    myLocMarker = null;
                }

                if (myLocMarker == null) {
                    LatLng yourPos = new LatLng(location.getLatitude(), location.getLongitude());
                    myLocMarker = mMap.addMarker(new MarkerOptions().position(yourPos).
                            icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)).title("You are here"));
                }
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
        Intent intent = getIntent();
        int locId = intent.getIntExtra("place", -1);
        if (locId >= 0) {
            LatLng location = MainActivity.latLngArrayList.get(locId);

            mMap.addMarker(new MarkerOptions().position(location).title(MainActivity.placesArrayList.get(locId)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(location.latitude, location.longitude)));
        }

        requestOrStartListening();
    }

    private void requestOrStartListening() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            startListening();
        }
    }


    public void toMyLocation(View view) {
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(myLocation.getLatitude(), myLocation.getLongitude())));
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        String address = getLocationData(latLng);

        mMap.addMarker(new MarkerOptions().position(latLng).title(address));

        MainActivity.latLngArrayList.add(latLng);
        MainActivity.placesArrayList.add(address);
        MainActivity.arrayAdapter.notifyDataSetChanged();

        Toast.makeText(this, "Location Saved!", Toast.LENGTH_SHORT).show();
    }

    public static String getLocationData(LatLng latLng) {

        String address = "";

        List<Address> addressList;

        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);

            if (addressList != null && addressList.size() > 0) {

                if (addressList.get(0).getPostalCode() != null) {
                    address += addressList.get(0).getPostalCode() + " ";
                }
                if (addressList.get(0).getThoroughfare() != null) {
                    address += addressList.get(0).getThoroughfare() + " ";
                }
                if (addressList.get(0).getLocality() != null) {
                    address += addressList.get(0).getLocality();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(address.equals("")){
            address = getCurrentTime();
        }

        return address;
    }

    public static String getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date today = Calendar.getInstance().getTime();
        return dateFormat.format(today);

    }
}
