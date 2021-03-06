package com.qingmei2.sample_androidtest.a_espresso.a06_async_dialog_fragment;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.qingmei2.sample_androidtest.R;

import org.junit.After;
import org.junit.Before;
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
public class A06AsyncActivity2Test {

    @Rule
    public ActivityTestRule<A06AsyncActivity2> activityRule = new ActivityTestRule<>(A06AsyncActivity2.class);

    private IdlingResource idlingresource;


    @Before
    public void setUp() throws Exception {
        idlingresource = activityRule.getActivity().getIdlingresource();

        //去掉下行注释，只有异步结束后，TextView处于显示状态，才进行接下来的测试代码（tests passed）
        Espresso.registerIdlingResources(idlingresource);
    }

    @Test
    public void onLoadingFinished() throws Exception {
//        Thread.sleep(3000);

        // 未注册idlingResource时，立即进行test，此时异步并未结束，报错（tests failed）
        onView(withId(R.id.text))
                .check(matches(withText("done")));
    }

    @After
    public void release() throws Exception {
        Espresso.unregisterIdlingResources(idlingresource);
    }
}