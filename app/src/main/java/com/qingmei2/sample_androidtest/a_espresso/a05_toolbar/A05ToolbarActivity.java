package com.qingmei2.sample_androidtest.a_espresso.a05_toolbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.qingmei2.sample_androidtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class A05ToolbarActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a05_toolbar);
        ButterKnife.bind(this);
        toolbar.setTitle(R.string.hello_espresso);
    }
}
