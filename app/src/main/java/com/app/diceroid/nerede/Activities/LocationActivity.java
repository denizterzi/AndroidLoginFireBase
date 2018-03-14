package com.app.diceroid.nerede.Activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.diceroid.nerede.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationActivity extends FragmentActivity implements OnMapReadyCallback {

    @BindView(R.id.location_info_text)
    TextView location_info_text;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_location);

        ButterKnife.bind(this);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        getLocation();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        // Add a marker in Sydney, Australia, and move the camera.
        LatLng edirne = new LatLng(41.667188, 26.571848);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            // Show rationale and request permission.
            return;
        }
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(edirne, 13));
        mMap.addMarker(new MarkerOptions().title("Deneme").snippet("Basarili").position(edirne));
    }
//Latitute longitude bilgilerini textbox a alıyoruz
    public void getLocation() {
        LocationManager locationManager= (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        LocationListener locationListener = new LocationListener() {

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }
//Bu alttaki 2 fonksiyon abstract edilmek zorunda konum servisleri açık mı kapalı mı diye bakıyor.
            @Override
            public void onProviderEnabled(String provider) {
                Toast.makeText(getApplicationContext(), R.string.gps_info, Toast.LENGTH_SHORT).show();
                //location_info.setText("GPS Veri bilgileri Alınıyor...");
            }

            @Override
            public void onProviderDisabled(String provider) {
                Toast.makeText(getApplicationContext(), R.string.gps_notification_disable, Toast.LENGTH_SHORT).show();
                //location_info.setText("GPS Bağlantı Bekleniyor...");
            }

            @Override
            public void onLocationChanged(Location location) {
                location.getLatitude();
                location.getLongitude();

                String infoText = "Bulunduğunuz konum bilgileri : \n" + "Latitud = " + location.getLatitude() + "\nLongitud = " + location.getLongitude();
                location_info_text.setText(infoText);
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }
    }
