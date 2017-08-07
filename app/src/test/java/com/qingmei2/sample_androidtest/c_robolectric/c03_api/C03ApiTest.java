package com.qingmei2.sample_androidtest.c_robolectric.c03_api;

import android.os.Build;

import com.qingmei2.sample_androidtest.BuildConfig;
import com.qingmei2.sample_androidtest.a_espresso.a08_async_dagger2.BaseApplication;
import com.qingmei2.sample_androidtest.base.mocks.MockAssestsReader;
import com.qingmei2.sample_androidtest.c_robolectric.c00_base.BaseTestTestRunner;

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
@Config(constants = BuildConfig.class, application = BaseApplication.class, assetDir = Config.DEFAULT_ASSET_FOLDER, sdk = Build.VERSION_CODES.JELLY_BEAN)
public class C03ApiTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void apiTest() throws Exception {
        String s = MockAssestsReader.readAssets(RuntimeEnvironment.application.getAssets(), "/mock/userJson");
        System.out.print(s);
    }
}
