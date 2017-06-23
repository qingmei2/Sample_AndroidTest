package com.qingmei2.sample_androidtest.a03list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import com.qingmei2.sample_androidtest.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Created by QingMei on 2017/6/23.
 * desc:
 */

public class A03ListActivityTest {

    @Rule
    public ActivityTestRule<A03ListActivity> activityTestRule = new ActivityTestRule<>(A03ListActivity.class);

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void listClickTest() throws Exception {
        //默认不可见
        onView(withId(R.id.text_view))
                .check(matches(not(isDisplayed())));

        //先滑动到item15，然后点击item8
        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.scrollToPosition(15))
                .perform(RecyclerViewActions.actionOnItemAtPosition(8,click()));

        //校验是否可见及内容
        onView(withId(R.id.text_view))
                .check(matches(isDisplayed()))
                .check(matches(withText("8")));

    }
}
