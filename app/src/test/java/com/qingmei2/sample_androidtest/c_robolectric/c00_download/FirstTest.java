package com.qingmei2.sample_androidtest.c_robolectric.c00_download;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by QingMei on 2017/7/3.
 * desc:
 */
@Config(manifest = "./src/main/AndroidManifest.xml")
@RunWith(RobolectricTestRunner.class)
public class FirstTest {

    @Test
    public void firstTest() throws Exception {
        System.out.println("hello robolectric!");
    }
}
