package com.qingmei2.sample_androidtest.a_espresso.a07_async_okhttp;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.jakewharton.espresso.OkHttp3IdlingResource;
import com.qingmei2.sample_androidtest.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by QingMei on 2017/6/23.
 * desc:
 */
@RunWith(AndroidJUnit4.class)
public class A07OkhttpActivityTest {

    @Rule
    public ActivityTestRule<A07OkhttpActivity> rule = new ActivityTestRule<>(A07OkhttpActivity.class);

    @Test
    public void requestHttpTest() throws Exception {
        OkHttp3IdlingResource idlingResource = OkHttp3IdlingResource.create("okhttp", OkHttpProvider.getOkHttpInstance());

        Espresso.registerIdlingResources(idlingResource);

        onView(withId(R.id.tv_name))
                .check(matches(withText("qingmei2")));

        //解除注册
        Espresso.unregisterIdlingResources(idlingResource);
    }
}