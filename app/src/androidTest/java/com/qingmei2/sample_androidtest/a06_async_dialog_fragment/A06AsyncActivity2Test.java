package com.qingmei2.sample_androidtest.a06_async_dialog_fragment;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import com.qingmei2.sample_androidtest.R;

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

    private A06IdlingResource myIdlingResource;

    @Before
    public void setUp() throws Exception {
        TextView textView = (TextView) activityRule.getActivity().findViewById(R.id.text);
        myIdlingResource = new A06IdlingResource(textView);
    }

    @Test
    public void onLoadingFinished() throws Exception {
        //去掉下行注释，只有三秒结束后，TextView处于显示状态，才进行接下来的测试代码（tests passed）
//        Espresso.registerIdlingResources(myIdlingResource);

        // 未注册myIdlingResource时，立即进行test，此时Dialog三秒并未结束，报错（tests failed）
        onView(withId(R.id.text))
                .check(matches(withText("done")));
    }

}