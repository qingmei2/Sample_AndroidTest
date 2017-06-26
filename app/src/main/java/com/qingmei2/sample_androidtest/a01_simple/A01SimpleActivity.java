package com.qingmei2.sample_androidtest.a01_simple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qingmei2.sample_androidtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class A01SimpleActivity extends AppCompatActivity {

    @BindView(R.id.btn01)
    Button btn01;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.et_01)
    EditText et01;
    @BindView(R.id.btn02)
    Button btn02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn01, R.id.btn02})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn01:
                tvContent.setVisibility(View.VISIBLE);
                tvContent.setText("hello espresso!");
                break;
            case R.id.btn02:
                tvContent.setVisibility(View.VISIBLE);
                tvContent.setText("success");
                et01.setText("");
                break;
        }
    }

}
