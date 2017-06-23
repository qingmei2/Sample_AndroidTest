package com.qingmei2.sample_androidtest.a04rotate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.qingmei2.sample_androidtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class A04RotateActivity extends AppCompatActivity {

    private static final String KEY_COUNT = "count";

    @BindView(R.id.count)
    TextView countText;

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a04_rotate);
        ButterKnife.bind(this);

        if (savedInstanceState != null)
            count = savedInstanceState.getInt(KEY_COUNT,0);

        updateCount();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_COUNT, count);
    }

    @OnClick(R.id.increment_button)
    public void onViewClicked() {
        count += 1;
        updateCount();
    }

    private void updateCount() {
        countText.setText(String.valueOf(count));
    }

}
