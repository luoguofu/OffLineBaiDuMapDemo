package com.example.lyj.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;

public class MapActivity extends AppCompatActivity {
    private MapView mMapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mMapView= (MapView) findViewById(R.id.mapview);
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(new LatLng(30.279404,120.137788));
        BaiduMap mBaiduMap= mMapView.getMap();
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
    }
}
