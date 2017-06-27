package com.qingmei2.sample_androidtest.a_espresso.a00_index;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.qingmei2.sample_androidtest.R;
import com.qingmei2.sample_androidtest.a_espresso.a01_simple.A01SimpleActivity;
import com.qingmei2.sample_androidtest.a_espresso.a02_async_glide.A02AsyncActivity;
import com.qingmei2.sample_androidtest.a_espresso.a03_list.A03ListActivity;
import com.qingmei2.sample_androidtest.a_espresso.a04_rotate.A04RotateActivity;
import com.qingmei2.sample_androidtest.a_espresso.a05_toolbar.A05ToolbarActivity;
import com.qingmei2.sample_androidtest.a_espresso.a06_async_dialog_fragment.A06AsyncActivity2;
import com.qingmei2.sample_androidtest.a_espresso.a07_async_okhttp.A07OkhttpActivity;
import com.qingmei2.sample_androidtest.a_espresso.a08_async_dagger2.A08Dagger2Activity;
import com.qingmei2.sample_androidtest.b_mockito.B00IndexActivity;
import com.qingmei2.sample_androidtest.m_mvp.mvp.UserActivity;

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

    /**
     * espresso-core：包括核心和基础的View匹配器、动作、断言。详见Basics和 Advanced Samples。
     * espresso-web：包含支持WebView的资源。
     * espresso-idling-resource：Espresso同步后台工作的机制。
     * espresso-contrib：外部支持（External contributions）包含日期选择器、 RecyclerView和绘制动作、断言检查、CountingIdlingResource。
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a00_index);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_01, R.id.btn_02, R.id.btn_03, R.id.btn_04, R.id.btn_05, R.id.btn_06, R.id.btn_07, R.id.btn_08, R.id.btn_09, R.id.btn_10})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_01:
                startActivity(new Intent(this, A01SimpleActivity.class));
                break;
            case R.id.btn_02:
                startActivity(new Intent(this, A02AsyncActivity.class));
                break;
            case R.id.btn_03:
                startActivity(new Intent(this, A03ListActivity.class));
                break;
            case R.id.btn_04:
                startActivity(new Intent(this, A04RotateActivity.class));
                break;
            case R.id.btn_05:
                startActivity(new Intent(this, A05ToolbarActivity.class));
                break;
            case R.id.btn_06:
                startActivity(new Intent(this, A06AsyncActivity2.class));
                break;
            case R.id.btn_07:
                startActivity(new Intent(this, A07OkhttpActivity.class));
                break;
            case R.id.btn_08:
                startActivity(new Intent(this, A08Dagger2Activity.class));
                break;
            case R.id.btn_09:
                startActivity(new Intent(this, B00IndexActivity.class));
                break;
            case R.id.btn_10:
                startActivity(new Intent(this, UserActivity.class));
                break;
        }
    }
}
