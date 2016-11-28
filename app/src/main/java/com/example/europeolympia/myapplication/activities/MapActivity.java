package com.example.europeolympia.myapplication.activities;

import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;

import com.example.europeolympia.myapplication.R;
import com.example.europeolympia.myapplication.data.GPSData;
import com.example.europeolympia.myapplication.interfaces.IGPSData;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * Created by EuropeOlympia on 21/11/2016.
 */

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap mMap;
    IGPSData myPositionGPSData;
    Marker mPosition;
    MarkerOptions mPositionMarker = new MarkerOptions().title("MyPosition");

    Runnable mUIRunnable = new Runnable() {
        @Override
        public void run() {
            Location location = myPositionGPSData.getPosition();
            LatLng myPosition = new LatLng(location.getLatitude(), location.getLongitude());

            if(mMap != null) {

                if (mPosition != null) {
                    mPosition.remove();
                }

                mPositionMarker.position(myPosition);
                mPositionMarker.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher));

                // Add a marker in Sydney and move the camera
                mPosition = mMap.addMarker(mPositionMarker);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(myPosition));
            }
        }
    };


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap){

        this.mMap = googleMap;

        //Start Walking guy
        myPositionGPSData = new GPSData();

        // Add a marker in Paris and move the camera
        LatLng ivry = new LatLng(48.814266, 2.394818);
        mMap.addMarker(new MarkerOptions().position(ivry).title("Marker in Ivry"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ivry));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ivry, 13f));

    }

}
