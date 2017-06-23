package com.qingmei2.sample_androidtest.a08_async_dagger2;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.qingmei2.sample_androidtest.a08_async_dagger2.api.GitHubServiceManager;
import com.qingmei2.sample_androidtest.a08_async_dagger2.di.TestApplication;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

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
    public void name() throws Exception {
        System.out.print(serviceManager.toString());

    }
}