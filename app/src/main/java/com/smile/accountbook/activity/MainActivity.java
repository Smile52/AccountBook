package com.smile.accountbook.activity;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.smile.accountbook.fragment.HomeFragment;
import com.smile.accountbook.fragment.SecondFragment;
import com.zhy.autolayout.AutoLayoutActivity;

import accountbook.smile.com.accountbook.R;

/**
 *
 */
public class MainActivity extends AutoLayoutActivity {
    private View inflater;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private String[] mTitle=new String[]{"标题一","标题二"};//选项卡标题
    private Toolbar mToolbar;
    private int THEMEID = -1;// 设置主题id
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initviews();
    }

    /**
     * 初始化view
     */
    private void initviews() {
        mToolbar= (Toolbar) findViewById(R.id.id_toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        mTabLayout= (TabLayout) findViewById(R.id.id_tabLayout);
        mViewPager= (ViewPager) findViewById(R.id.id_viewpager);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position==0){
                    return new HomeFragment();
                }if (position==1){
                    return new SecondFragment();
                }

                return new HomeFragment();
            }

            @Override
            public int getCount() {
                return mTitle.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle[position];
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);//将viewpager与tablayout关联

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.id_red:
                        //mToolbar.setBackgroundColor(Color.RED);

                        break;

                    case R.id.id_blue:
                        setTheme(R.style.blueTheme);

                        break;
                }
                return true;
            }
        });
    }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }


}
