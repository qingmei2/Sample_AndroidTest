package com.qingmei2.sample_androidtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class A01MainActivity extends AppCompatActivity {

    @BindView(R.id.btn01)
    Button btn01;
    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn01)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn01:
                tvContent.setVisibility(View.VISIBLE);
                tvContent.setText("hello espresso!");
                break;
        }
    }
}
