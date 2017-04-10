package com.example.aluno.acidentestransito;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private Connection con = new Connection();
    private List<Marker> markers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng sjc = new LatLng(-23.2146027,-45.8854213);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sjc));
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(11));
        googleMap.isTrafficEnabled();

        try{
            this.markers = con.getData();
            for (Marker marker:this.markers){
                if(marker.getTotalAcidentes() >= 10){
                    googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(marker.getLatitude(), marker.getLongitude()))
                            .title(marker.getEndereco())
                            .snippet("TOTAL DE ACIDENTES: " + marker.getTotalAcidentes() + "   ACIDENTES FATAIS: " + marker.getAcidentesFatais())
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_red))
                    );
                }
                else if(marker.getTotalAcidentes() <= 3){
                    googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(marker.getLatitude(), marker.getLongitude()))
                            .title(marker.getEndereco())
                            .snippet("TOTAL DE ACIDENTES: " + marker.getTotalAcidentes() + "   ACIDENTES FATAIS: " + marker.getAcidentesFatais())
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_green))
                    );
                }
                else{
                    googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(marker.getLatitude(), marker.getLongitude()))
                            .title(marker.getEndereco())
                            .snippet("TOTAL DE ACIDENTES: " + marker.getTotalAcidentes() + "   ACIDENTES FATAIS: " + marker.getAcidentesFatais())
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_yellow))
                    );
                }
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }
}