package com.qingmei2.sample_androidtest.a_espresso.a06_async_dialog_fragment;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.qingmei2.sample_androidtest.R;
import com.qingmei2.sample_androidtest.a_espresso.util.EspressoIdlingResource;

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

        EspressoIdlingResource.increment();
    }

    public void onLoadingFinished() {
        if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
            EspressoIdlingResource.decrement();
        }

        textView.setText("done");
        textView.setVisibility(View.VISIBLE);
    }

    @VisibleForTesting
    public IdlingResource getIdlingresource() {
        return EspressoIdlingResource.getIdlingResource();
    }
}
