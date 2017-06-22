package com.qingmei2.sample_androidtest.a00index;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.qingmei2.sample_androidtest.a01simple.A01SimpleActivity;
import com.qingmei2.sample_androidtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by QingMei on 2017/6/22.
 * desc:
 */

public class A00IndexActivity extends AppCompatActivity {

    @BindView(R.id.btn_01)
    Button btn01;
    @BindView(R.id.btn_02)
    Button btn02;
    @BindView(R.id.btn_03)
    Button btn03;
    @BindView(R.id.btn_04)
    Button btn04;
    @BindView(R.id.btn_05)
    Button btn05;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_01, R.id.btn_02, R.id.btn_03, R.id.btn_04, R.id.btn_05})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_01:
                startActivity(new Intent(this, A01SimpleActivity.class));
                break;
            case R.id.btn_02:
                break;
            case R.id.btn_03:
                break;
            case R.id.btn_04:
                break;
            case R.id.btn_05:
                break;
        }
    }
}
