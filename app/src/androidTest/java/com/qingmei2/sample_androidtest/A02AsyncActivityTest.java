package com.qingmei2.sample_androidtest;

import android.support.test.rule.ActivityTestRule;

import com.qingmei2.sample_androidtest.a02async.A02AsyncActivity;

import org.junit.Rule;
import org.junit.Test;

/**
 * Created by QingMei on 2017/6/22.
 * desc:
 */

public class A02AsyncActivityTest {

    @Rule
    ActivityTestRule<A02AsyncActivity> activityRule = new ActivityTestRule<>(A02AsyncActivity.class);

    @Test
    public void name() throws Exception {


    }
}
