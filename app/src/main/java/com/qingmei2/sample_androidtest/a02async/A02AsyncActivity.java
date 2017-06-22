package com.qingmei2.sample_androidtest.a02async;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qingmei2.sample_androidtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class A02AsyncActivity extends AppCompatActivity {

    @BindView(R.id.btn01)
    Button btn01;
    @BindView(R.id.imageView)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a02_async);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn01)
    public void onViewClicked() {
        Glide.with(this).load("").into(imageView);
    }
}
