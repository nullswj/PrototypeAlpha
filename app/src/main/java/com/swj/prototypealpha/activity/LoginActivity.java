package com.swj.prototypealpha.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.swj.prototypealpha.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{

    private Button btn_login;

    private void initUI()
    {
        btn_login = findViewById(R.id.btn_login);
    }

    private void setOnclickListener()
    {
        btn_login.setOnClickListener(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initUI();
        setOnclickListener();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_login:
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
                break;
            default:
                break;
        }
    }
}
