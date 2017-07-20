package com.example.lyj.myapplication;

import android.app.Application;
import android.os.Environment;

import com.baidu.mapapi.SDKInitializer;

import java.io.File;

/**
 * Created by lgf on 2017/3/19.
 */

public class MyApplication extends Application {
    public static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //自己创建的Application时要在清单文件中注册
        initBaiDuSdk();
        instance=this;
    }

    private void initBaiDuSdk(){
        String offLineMapPath= Environment.getExternalStorageDirectory().getPath()+ "/Android/data/" + getPackageName() + "/files";
        File file=new File(offLineMapPath);
        if(!file.exists()){
            file.mkdirs();
        }
        SDKInitializer.initialize(file.getAbsolutePath(),this);
    }
}
