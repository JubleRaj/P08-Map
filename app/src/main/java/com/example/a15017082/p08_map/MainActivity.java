package com.example.a15017082.p08_map;

import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity{

    Button btnNorth, btnCentral, btnEast;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinner.getSelectedItemPosition() == 1) {
                    btnNorth.performClick();
                } else if (spinner.getSelectedItemPosition() == 2) {
                    btnCentral.performClick();
                } else if (spinner.getSelectedItemPosition() == 3) {
                    btnEast.performClick();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnNorth = (Button) findViewById(R.id.btnNorth);
        btnCentral = (Button) findViewById(R.id.btnCentral);
        btnEast = (Button) findViewById(R.id.btnEast);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                LatLng poi_North = new LatLng(1.433240, 103.781218);
                Marker cp = map.addMarker(new
                        MarkerOptions()
                        .position(poi_North)
                        .title("HQ-North")
                        .snippet("Block 333, Admiralty Ave 3, 765654 ")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));


                LatLng poi_Central = new LatLng(1.297802, 103.847441);
                Marker cp1 = map.addMarker(new
                        MarkerOptions()
                        .position(poi_Central)
                        .title("HQ-Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542 ")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));


                LatLng poi_East = new LatLng(1.367149, 103.928021);
                Marker cp3 = map.addMarker(new
                        MarkerOptions()
                        .position(poi_East)
                        .title("HQ-East")
                        .snippet("Block 555, Tampines Ave 3, 287788 ")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

                LatLng poi_Singapore = new LatLng(1.352083, 103.819836);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Singapore,
                        11));


                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                }

                btnNorth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LatLng poi_North = new LatLng(1.433240, 103.781218);
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_North,
                                15));

                        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                            @Override
                            public boolean onMarkerClick(Marker marker) {
                                Toast.makeText(MainActivity.this,"HQ-North",Toast.LENGTH_SHORT).show();
                                return false;
                            }
                        });
                    }
                });

                btnCentral.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LatLng poi_Central = new LatLng(1.297802, 103.847441);
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Central,
                                15));

                        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                            @Override
                            public boolean onMarkerClick(Marker marker) {
                                Toast.makeText(MainActivity.this,"HQ-Central",Toast.LENGTH_SHORT).show();
                                return false;
                            }
                        });
                    }
                });

                btnEast.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LatLng poi_East = new LatLng(1.367149, 103.928021);
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_East,
                                15));

                        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                            @Override
                            public boolean onMarkerClick(Marker marker) {
                                Toast.makeText(MainActivity.this,"HQ-East",Toast.LENGTH_SHORT).show();
                                return false;
                            }
                        });
                    }
                });
            }
        });
    }
}
