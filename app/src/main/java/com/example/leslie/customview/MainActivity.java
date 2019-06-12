package com.example.leslie.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;


public class MainActivity extends SwipeBackActivity {

    @BindView(R.id.rg1)
    RadioGroup mRg1;
    @BindView(R.id.rg2)
    RadioGroup mRg2;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rb1)
    RadioButton mRb1;
    @BindView(R.id.rb2)
    RadioButton mRb2;
    @BindView(R.id.rb3)
    RadioButton mRb3;
    @BindView(R.id.rb4)
    RadioButton mRb4;
    @BindView(R.id.lv_left_menu)
    ListView mLvLeftMenu;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.statusBarView)
    View mStatusBarView;
    private final String toolbarColor = "#6A659A";
    @BindView(R.id.iv)
    ImageView mIv;
    private Unbinder mBind;
    private MainActivity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mBind = ButterKnife.bind(this);
        mActivity = MainActivity.this;
        addStatusViewWithColor(Color.parseColor(toolbarColor));
//        setNavigationBar();
        MyApp.sActivities.add(this);
        if (MyApp.sActivities.size() <= 1) {
            getSwipeBackLayout().setEnableGesture(false);
        }
        initToolbar();
        initMenuList();
    }

    private void initMenuList() {
        ArrayList menuList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            menuList.add("List Item_" + i);
        }
        ArrayAdapter adapter = new ArrayAdapter(mActivity, android.R.layout.simple_list_item_1, menuList);
        mLvLeftMenu.setAdapter(adapter);
        mLvLeftMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mActivity, "点击侧滑菜单_" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initToolbar() {
//        mToolbar.setNavigationIcon(R.mipmap.ic_launcher);
        mToolbar.setBackgroundColor(Color.parseColor(toolbarColor));
        mToolbar.setLogo(R.mipmap.ic_launcher);//设置app logo
        mToolbar.setTitle("编程区" + MyApp.sActivities.size());//设置主标题
//        mToolbar.setSubtitle("Subtitle");//设置子标题
//        mToolbar.inflateMenu(R.menu.menu_toolbar);
//        mToolbar.setOnMenuItemClickListener(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //创建返回键，并实现打开关/闭监听
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(mActivity, mDrawerLayout, mToolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Toast.makeText(mActivity, "onDrawerOpened", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Toast.makeText(mActivity, "onDrawerClosed", Toast.LENGTH_SHORT).show();
            }
        };
        drawerToggle.syncState();
        mDrawerLayout.addDrawerListener(drawerToggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onMenuItemClick(item);
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (menu != null) {
            if (menu.getClass() == MenuBuilder.class) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    System.out.print(getClass().getSimpleName() + "onMenuOpened...unable to set icons for overflow menu" + e);
                }
            }
        }

        return super.onPrepareOptionsPanel(view, menu);
    }

    private boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                Toast.makeText(mActivity, "点击search", Toast.LENGTH_SHORT).show();
                break;
            case R.id.search02:
                Toast.makeText(mActivity, "点击search02", Toast.LENGTH_SHORT).show();
                break;
            case R.id.notification:
                Toast.makeText(mActivity, "点击notification", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_1:
                Toast.makeText(mActivity, "点击menu_1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_2:
                Toast.makeText(mActivity, "点击menu_2", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    /**
     * 添加状态栏占位视图
     */
    private void addStatusViewWithColor(int color) {
        ViewGroup contentView = (ViewGroup) mActivity.findViewById(android.R.id.content);
        View statusBarView = new View(mActivity);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStateBarHeight());
        statusBarView.setBackgroundColor(color);
        contentView.addView(statusBarView, lp);
    }

    /**
     * 获取状态栏高度
     */
    public int getStateBarHeight() {
        int result = 0;
        Context context = mActivity.getApplicationContext();
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        if (result == 0) {
            result = (int) Math.ceil(20 * context.getResources().getDisplayMetrics().density);
        }
        return result;
    }

    public void setNavigationBar() {
        // 透明状态栏
        mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ViewGroup parentView = (ViewGroup) ((ViewGroup) mActivity.findViewById(android.R.id.content)).getChildAt(0);//拿到第一层的content

        if (parentView != null && Build.VERSION.SDK_INT >= 14) {
            //布局预留状态栏高度的 padding
            parentView.setFitsSystemWindows(true);
            if (parentView instanceof DrawerLayout) {
                DrawerLayout drawer = (DrawerLayout) parentView;
                //将主页面顶部延伸至status bar;虽默认为false,但经测试,DrawerLayout需显示设置
                drawer.setClipToPadding(false);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
        MyApp.sActivities.remove(this);
    }

    @OnClick({R.id.btn_sure, R.id.btn_save, R.id.btn_download,R.id.iv})
    public void onViewClicked(View view) {
        Intent intent = new Intent(mActivity, MainActivity.class);
        switch (view.getId()) {
            case R.id.btn_sure:
                startActivity(intent);
                break;
            case R.id.btn_save:
                startActivity(intent);
                break;
            case R.id.btn_download:
                startActivity(intent);
                break;
            case R.id.iv:
                Intent webIntent = new Intent();
                webIntent.setAction("android.intent.action.VIEW");//Intent.ACTION_VIEW
                Uri content_url = Uri.parse("http://www.baidu.com");
                webIntent.setData(content_url);
                startActivity(webIntent);
                break;
        }
    }
}
