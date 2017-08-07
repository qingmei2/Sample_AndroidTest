package com.qingmei2.sample_androidtest.c_robolectric.c02_api;

import com.qingmei2.sample_androidtest.BuildConfig;
import com.qingmei2.sample_androidtest.c_robolectric.c00_base.BaseTestTestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

/**
 * Created by QingMei on 2017/8/7.
 * desc:
 */
@RunWith(BaseTestTestRunner.class)
@Config(constants = BuildConfig.class)
public class C02ApiTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void ApiTest() throws Exception {

    }
}
