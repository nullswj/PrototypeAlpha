package com.swj.prototypealpha.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.swj.prototypealpha.R;

import java.util.ArrayList;
import java.util.List;

public class PolicyLableActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar   mToolbar;
    private TextView  mTvpolicy1;
    private TextView  mTvpolicy2;
    private TextView  mTvpolicy3;
    private ViewPager mVpaction;
    private LinearLayout itemm;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy_lable);
        mToolbar = findViewById(R.id.toolbarPolicy);
        initUI();

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    private void initUI () {
        mToolbar.inflateMenu(R.menu.toolbar_menu);
        mTvpolicy1 = findViewById(R.id.action_policy1);
        mTvpolicy2 = findViewById(R.id.action_policy2);
        mTvpolicy3 = findViewById(R.id.action_policy3);
        mVpaction = findViewById(R.id.action_item);
        itemm=findViewById(R.id.itemm);

        mTvpolicy3.setOnClickListener(this);
        mTvpolicy2.setOnClickListener(this);
        mTvpolicy1.setOnClickListener(this);
        itemm.setOnClickListener(this);
        mVpaction.setOnPageChangeListener(new MyPagerChangeListener());

        List<Fragment> mList = new ArrayList<>();
        //mList.add(new Action1Fragment());
        //mList.add(new Action2Fragment());
        //mList.add(new Action3Fragment());

        ActionTabAdapter mAdapter = new ActionTabAdapter
                (getSupportFragmentManager(), mList);

        mVpaction.setAdapter(mAdapter);
        mVpaction.setCurrentItem(0);
        mTvpolicy1.setBackgroundColor(Color.LTGRAY);
    }


    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.action_policy1:
                mVpaction.setCurrentItem(0);
                mTvpolicy1.setBackgroundColor(Color.LTGRAY);
                mTvpolicy2.setBackgroundColor(Color.WHITE);
                mTvpolicy3.setBackgroundColor(Color.WHITE);
                break;
            case R.id.action_policy2:
                mVpaction.setCurrentItem(1);
                mTvpolicy1.setBackgroundColor(Color.WHITE);
                mTvpolicy2.setBackgroundColor(Color.LTGRAY);
                mTvpolicy3.setBackgroundColor(Color.WHITE);
                break;
            case R.id.action_policy3:
                mVpaction.setCurrentItem(2);
                mTvpolicy1.setBackgroundColor(Color.WHITE);
                mTvpolicy2.setBackgroundColor(Color.WHITE);
                mTvpolicy3.setBackgroundColor(Color.LTGRAY);
                break;
            case R.id.itemm:
                Intent intent=new Intent(this,PolicyinfoActivity.class);
                startActivity(intent);
        }
    }

    public class MyPagerChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged (int arg0) {
        }

        @Override
        public void onPageScrolled (int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected (int arg0) {
            switch (arg0) {
                case 0:
                    mTvpolicy1.setBackgroundColor(Color.LTGRAY);
                    mTvpolicy2.setBackgroundColor(Color.WHITE);
                    mTvpolicy3.setBackgroundColor(Color.WHITE);
                    break;
                case 1:
                    mTvpolicy1.setBackgroundColor(Color.WHITE);
                    mTvpolicy2.setBackgroundColor(Color.LTGRAY);
                    mTvpolicy3.setBackgroundColor(Color.WHITE);
                    break;
                case 2:
                    mTvpolicy1.setBackgroundColor(Color.WHITE);
                    mTvpolicy2.setBackgroundColor(Color.WHITE);
                    mTvpolicy3.setBackgroundColor(Color.LTGRAY);
                    break;
            }
        }
    }
}
