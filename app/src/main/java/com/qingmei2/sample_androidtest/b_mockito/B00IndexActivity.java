package com.qingmei2.sample_androidtest.b_mockito;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.qingmei2.sample_androidtest.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by QingMei on 2017/6/26.
 * desc:Mockito library 索引页
 */

public class B00IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b00_index);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_01, R.id.btn_02, R.id.btn_03, R.id.btn_04, R.id.btn_05, R.id.btn_06, R.id.btn_07, R.id.btn_08, R.id.btn_09})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_01:
                startActivity(new Intent(this, B01SimpleActivity.class));
                break;
            case R.id.btn_02:
                break;
            case R.id.btn_03:
                break;
            case R.id.btn_04:
                break;
            case R.id.btn_05:
                break;
            case R.id.btn_06:
                break;
            case R.id.btn_07:
                break;
            case R.id.btn_08:
                break;
            case R.id.btn_09:
                break;
        }
    }
}
