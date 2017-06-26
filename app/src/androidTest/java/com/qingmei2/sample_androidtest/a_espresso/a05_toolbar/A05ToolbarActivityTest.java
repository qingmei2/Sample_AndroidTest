package com.qingmei2.sample_androidtest.a_espresso.a05_toolbar;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.Toolbar;

import com.qingmei2.sample_androidtest.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;

/**
 * Created by QingMei on 2017/6/23.
 * desc:
 */
@RunWith(AndroidJUnit4.class)
public class A05ToolbarActivityTest {

    @Rule
    public ActivityTestRule<A05ToolbarActivity> rule = new ActivityTestRule<>(A05ToolbarActivity.class);

    @Test
    public void toolbarTest() throws Exception {
        CharSequence title = rule.getActivity().getResources().getString(R.string.hello_espresso);

        //记得要在清单文件中添加noActionBar
        onView(isAssignableFrom(Toolbar.class))
                .check(matches(withToolbarTitle(title)));
    }

    private static Matcher<Object> withToolbarTitle(final CharSequence title) {
        return new BoundedMatcher<Object, Toolbar>(Toolbar.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("with toolbar title :");
            }

            @Override
            protected boolean matchesSafely(Toolbar item) {
                return item.getTitle().equals(title);
            }
        };
    }


}