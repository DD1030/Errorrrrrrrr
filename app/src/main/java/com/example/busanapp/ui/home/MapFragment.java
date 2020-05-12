package com.example.busanapp.ui.home;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.busanapp.R;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.util.FusedLocationSource;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MapFragment extends Fragment implements OnMapReadyCallback {
    private static final int ACCESS_LOCATION_PERMISSION_REQUEST_CODE = 100;
    private FusedLocationSource locationSource;

    public MapFragment(){}

    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }


    @UiThread
    @Override
    public void onMapReady(@NonNull NaverMap naverMap){
        locationSource = new FusedLocationSource(this, ACCESS_LOCATION_PERMISSION_REQUEST_CODE);
        naverMap.setLocationSource(locationSource);
        UiSettings uiSettings = naverMap.getUiSettings();
        uiSettings.setLocationButtonEnabled(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        MapFragment mapFragment = (MapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);  //mapFragment 가 아니라 callback이 떠야함

        return rootView;
    }

    private void getMapAsync(MapFragment mapFragment) {
    }

    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode){
            case ACCESS_LOCATION_PERMISSION_REQUEST_CODE :
                locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults);
                return;
        }
    }

}