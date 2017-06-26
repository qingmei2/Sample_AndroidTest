package com.qingmei2.sample_androidtest.a_espresso.a02_async_glide;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ImageView;

import com.qingmei2.sample_androidtest.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

/**
 * Created by QingMei on 2017/6/22.
 * desc:
 */
@RunWith(AndroidJUnit4.class)
public class A02AsyncActivityTest {

    @Rule
    public ActivityTestRule<A02AsyncActivity> activityRule = new ActivityTestRule<>(A02AsyncActivity.class);

    private ImageView imageView;
    private A02IdlingResource a02IdlingResource;

    @Before
    public void setUp() throws Exception {
        imageView = (ImageView) activityRule.getActivity().findViewById(R.id.imageView);
        a02IdlingResource = new A02IdlingResource(imageView);
    }

    @Test
    public void loadImage() throws Exception {

        //点击按钮，加载图片
        onView(withId(R.id.btn01))
                .perform(click());

        onView(withId(R.id.imageView))
                .check(matches(isClickable()));
//                .check(matches(not(isClickable())));

        Espresso.registerIdlingResources(a02IdlingResource);//只有图片加载成功才执行后面的代码

        onView(withId(R.id.imageView))
                .check(matches(not(isClickable())));
    }
}
