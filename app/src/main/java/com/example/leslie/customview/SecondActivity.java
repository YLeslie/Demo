package com.example.leslie.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * @author:yangm
 * @package:com.example.leslie.customview
 * @desciption:${TODO}
 * @date:2019/5/22 0022
 */
public class SecondActivity extends SwipeBackActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
