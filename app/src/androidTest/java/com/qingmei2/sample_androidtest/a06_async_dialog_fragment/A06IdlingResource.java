package com.qingmei2.sample_androidtest.a06_async_dialog_fragment;

import android.support.test.espresso.IdlingResource;
import android.view.View;

/**
 * Created by QingMei on 2017/6/23.
 * desc:
 */

public class A06IdlingResource implements IdlingResource {

    private volatile ResourceCallback resourceCallback;

    private final View view;

    public A06IdlingResource(View view) {
        this.view = view;
    }

    @Override
    public String getName() {
        return "MyIdlingResource";
    }

    @Override
    public boolean isIdleNow() {
        if (view.isShown()) {
            if (resourceCallback != null) {
                resourceCallback.onTransitionToIdle();
            }
            return true;
        }
        return false;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        resourceCallback = callback;
    }
}
