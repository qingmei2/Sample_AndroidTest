package com.qingmei2.sample_androidtest.a_espresso.a02_async_glide;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.qingmei2.sample_androidtest.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by QingMei on 2017/6/22.
 * desc:
 */
@RunWith(AndroidJUnit4.class)
public class A02AsyncActivityTest {

    @Rule
    public ActivityTestRule<A02AsyncActivity> activityRule = new ActivityTestRule<>(A02AsyncActivity.class);


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void loadImage() throws Exception {

        onView(withId(R.id.btn01))
                .check(matches(withText("网络请求图片")));

        //点击按钮，加载图片
        onView(withId(R.id.btn01))
                .perform(click());

        Espresso.registerIdlingResources(
                activityRule.getActivity().getCountingIdlingResource());

        onView(withId(R.id.btn01))
                .check(matches(withText("success!")));
    }

    @After
    public void unregisterIdlingResource() {
        Espresso.unregisterIdlingResources(
                activityRule.getActivity().getCountingIdlingResource());
    }
}
