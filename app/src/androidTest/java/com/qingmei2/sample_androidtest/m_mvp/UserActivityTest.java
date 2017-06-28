package com.qingmei2.sample_androidtest.m_mvp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.qingmei2.sample_androidtest.R;
import com.qingmei2.sample_androidtest.a_espresso.a07_async_okhttp.User;
import com.qingmei2.sample_androidtest.m_mvp.mvp.UserActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by QingMei on 2017/6/27.
 * desc:
 */
@RunWith(AndroidJUnit4.class)
public class UserActivityTest {

    @Rule
    public ActivityTestRule<UserActivity> activityTestRule = new ActivityTestRule<>(UserActivity.class);
    private UserActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = activityTestRule.getActivity();
    }

    @Test
    public void onShowUserData() throws Exception {
        User user = new User();
        user.setName("青梅");
        user.setLogin("qingmei2");
        activity.runOnUiThread(() -> activity.onShowUserData(user));

        onView(withId(R.id.tv_name))
                .check(matches(withText("qingmei2")));
    }

    @Test
    public void onError() throws Exception {

    }

}