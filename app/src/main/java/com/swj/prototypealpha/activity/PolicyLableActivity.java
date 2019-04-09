package com.swj.prototypealpha.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

import com.swj.prototypealpha.R;

public class PolicyLableActivity extends AppCompatActivity{
    private Toolbar   mToolbar;
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
        itemm=findViewById(R.id.itemm);
        itemm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent=new Intent(PolicyLableActivity.this,PolicyinfoActivity.class);
                startActivity(intent);
            }
        });
    }

}
