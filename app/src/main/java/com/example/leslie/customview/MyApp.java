package com.example.leslie.customview;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;

/**
 * @author:yangm
 * @package:com.example.leslie.customview
 * @desciption:${TODO}
 * @date:2019/5/22 0022
 */
public class MyApp extends Application {

    public static ArrayList<Activity> sActivities;
    @Override
    public void onCreate() {
        super.onCreate();
        sActivities = new ArrayList<>();
    }
}
