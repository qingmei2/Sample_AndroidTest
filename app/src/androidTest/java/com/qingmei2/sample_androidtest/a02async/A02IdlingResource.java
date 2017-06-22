package com.qingmei2.sample_androidtest.a02async;

import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by QingMei on 2017/6/22.
 * desc:
 */

public class A02IdlingResource implements IdlingResource {

    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public String getName() {
        return "A02IdlingResource";
    }

    @Override
    public boolean isIdleNow() {
        return counter.get() == 0;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {

    }
}
