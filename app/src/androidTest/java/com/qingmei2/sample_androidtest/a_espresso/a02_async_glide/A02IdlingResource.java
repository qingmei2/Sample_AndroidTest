package com.qingmei2.sample_androidtest.a_espresso.a02_async_glide;

import android.support.test.espresso.IdlingResource;
import android.widget.ImageView;

import com.qingmei2.sample_androidtest.R;

/**
 * Created by QingMei on 2017/6/22.
 * desc:
 */

public class A02IdlingResource implements IdlingResource {

    private volatile ResourceCallback resourceCallback;
    private ImageView imageView;

    public A02IdlingResource(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    public String getName() {
        return "A02IdlingResource";
    }

    @Override
    public boolean isIdleNow() {
        return imageView.getTag(R.id.indexTag).equals(A02AsyncActivity.IMAGE_LOAD_FINISH);
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        resourceCallback = callback;
    }

}
