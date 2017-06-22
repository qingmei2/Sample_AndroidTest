package com.qingmei2.sample_androidtest;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.qingmei2.sample_androidtest.a01simple.A01SimpleActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class A01SimpleActivityTest {

    @Rule
    public ActivityTestRule<A01SimpleActivity> rule = new ActivityTestRule<>(A01SimpleActivity.class);

    @Test
    public void clickTest() {
        //tvContent是否默认不显示
        onView(withId(R.id.tv_content))
                .check(matches(not(isDisplayed())));

        //检查btn01的text，然后执行点击事件
        onView(withId(R.id.btn01))
                .check(matches(withText("修改内容")))
                .perform(click());

        //检查tv内容是否修改，并且是否可见
        onView(withId(R.id.tv_content))
                .check(matches(withText("hello espresso!")))
                .check(matches(isDisplayed()));
    }
}
