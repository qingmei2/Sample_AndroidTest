package com.qingmei2.sample_androidtest.c_robolectric.c02_sharepreferences;

import com.qingmei2.sample_androidtest.BuildConfig;
import com.qingmei2.sample_androidtest.c_robolectric.c00_base.BaseTestTestRunner;
import com.qingmei2.sample_androidtest.normal.Preferences;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

/**
 * Created by QingMei on 2017/8/7.
 * desc:
 */
@RunWith(BaseTestTestRunner.class)
@Config(constants = BuildConfig.class)
public class C02SharePreferencesTest {

    private Preferences preferences;

    @Before
    public void setUp() throws Exception {
        preferences = new Preferences(RuntimeEnvironment.application);
    }

    @Test
    public void setUserTest() throws Exception {
        preferences.setUserName("qingmei2");
        Assert.assertEquals("qingmei2", preferences.getUserName());
    }

    @Test
    public void getDefaultUserTest() throws Exception {
        Assert.assertEquals("", preferences.getUserName());
    }
}
