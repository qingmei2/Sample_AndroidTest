package com.qingmei2.sample_androidtest.b_mockito;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.qingmei2.sample_androidtest.R;

/**
 * Created by QingMei on 2017/6/26.
 * desc:
 */

public class B01SimpleActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b01_simple);
    }
}
