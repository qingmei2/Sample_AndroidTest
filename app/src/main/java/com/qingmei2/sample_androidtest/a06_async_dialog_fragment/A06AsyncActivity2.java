package com.qingmei2.sample_androidtest.a06_async_dialog_fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.qingmei2.sample_androidtest.R;

public class A06AsyncActivity2 extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a06_async2);

        textView = (TextView) findViewById(R.id.text);
        textView.setVisibility(View.GONE);

        LoadingDialogFragment fragment = new LoadingDialogFragment();
        fragment.setCancelable(false);
        fragment.show(getSupportFragmentManager(), LoadingDialogFragment.TAG);
    }

    public void onLoadingFinished() {
        textView.setText("done");
        textView.setVisibility(View.VISIBLE);
    }
}
