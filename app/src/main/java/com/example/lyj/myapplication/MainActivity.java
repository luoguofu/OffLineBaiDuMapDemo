package com.example.lyj.myapplication;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.mapapi.map.offline.MKOLUpdateElement;
import com.baidu.mapapi.map.offline.MKOfflineMap;
import com.baidu.mapapi.map.offline.MKOfflineMapListener;

import java.io.File;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button down,viewOffline;
    private String cityPath= Environment.getExternalStorageDirectory().getPath()+ "/Android/data/" + MyApplication.getInstance().getPackageName() + "/files/BaiduMapSDK/vmp/h/"
            + "hangzhou_179.dat";
    private String cityKeyPath=Environment.getExternalStorageDirectory().getPath()+"/Android/data/"+MyApplication.getInstance().getPackageName()+"/files/BaiduMapSDK/vmp/h/"
            +"DVUserdat.cfg";
    private File offLineMap,keyFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewOffline= (Button) findViewById(R.id.view_offline);
        down = (Button) findViewById(R.id.down);
        down.setOnClickListener(this);
        viewOffline.setOnClickListener(this);
        offLineMap=new File(cityPath);
        keyFile=new File(cityKeyPath);

    }

    private void downLoadMap() {
        //二者必须同时存在
        if(!offLineMap.exists()||!keyFile.exists()) {
            if(offLineMap.exists()){
                offLineMap.delete();
            }
            if(keyFile.exists()){
                keyFile.delete();
            }
            final MKOfflineMap offlineMap = new MKOfflineMap();
            offlineMap.init(new MKOfflineMapListener() {
                @Override
                public void onGetOfflineMapState(int type, int state) {
                    switch (type) {
                        case MKOfflineMap.TYPE_DOWNLOAD_UPDATE:
                            MKOLUpdateElement update = offlineMap.getUpdateInfo(state);
                            Log.e("111", "下载进度" + update.ratio);
                            if (update.ratio == 100) {
                                Log.e("111", "下载完成");
                                Toast.makeText(MainActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
                                viewOffline.setVisibility(View.VISIBLE);
                            }
                            break;

                        case MKOfflineMap.TYPE_NEW_OFFLINE:
                            break;

                        case MKOfflineMap.TYPE_VER_UPDATE:
                            break;
                    }
                }
            });
            //下载杭州地图
            offlineMap.start(179);
        }else{
            viewOffline.setVisibility(View.VISIBLE);
        }
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.down:
                downLoadMap();
                break;

            case R.id.view_offline:
                viewOfflineMap();
                break;
        }
    }

    private void viewOfflineMap(){
        startActivity(new Intent(this,MapActivity.class));
    }
}
