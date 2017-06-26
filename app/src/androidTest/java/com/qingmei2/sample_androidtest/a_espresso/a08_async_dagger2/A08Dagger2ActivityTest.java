package com.qingmei2.sample_androidtest.a_espresso.a08_async_dagger2;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.qingmei2.sample_androidtest.R;
import com.qingmei2.sample_androidtest.a_espresso.a08_async_dagger2.api.GitHubServiceManager;
import com.qingmei2.sample_androidtest.a_espresso.a08_async_dagger2.di.TestApplication;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by QingMei on 2017/6/23.
 * desc:
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class A08Dagger2ActivityTest {

    @Rule
    public ActivityTestRule<A08Dagger2Activity> rule = new ActivityTestRule<>(A08Dagger2Activity.class);

    @Inject
    GitHubServiceManager serviceManager;

    private static final String USERNAME = "qingmei2";

    @Before
    public void setUp() throws Exception {
        ((TestApplication) rule.getActivity().getApplication()).getTestAppComponent().inject(this);
    }

    @Test
    public void daggerTest() throws Exception {
        serviceManager.getUser(USERNAME)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                            //serviceManager = com.qingmei2.sample_androidtest.a_espresso.a08_async_dagger2.di.MockGitHubServiceManager@xxxxxx
                            //user = User(login=qingmei2, name=null)
                            //说明Dagger2依赖注入使用Mock数据成功
                            Log.i("tag", "serviceManager = " + serviceManager.toString());
                            Log.i("tag", "user = " + user.toString());
                            rule.getActivity().showData(user.getLogin());
                        }
                );

        onView(withId(R.id.btn_http)).perform(click());

        //查看是否匹配
        onView(withId(R.id.tv_name)).check(matches(withText(USERNAME)));
    }
}