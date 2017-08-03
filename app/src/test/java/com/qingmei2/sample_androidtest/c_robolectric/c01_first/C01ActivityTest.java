package com.qingmei2.sample_androidtest.c_robolectric.c01_first;

import android.app.Activity;
import android.app.Application;
import android.widget.Button;
import android.widget.CheckBox;

import com.qingmei2.sample_androidtest.BuildConfig;
import com.qingmei2.sample_androidtest.R;
import com.qingmei2.sample_androidtest.c_robolectric.C01Activity;
import com.qingmei2.sample_androidtest.c_robolectric.c00_base.BaseTestTestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by QingMei on 2017/8/1.
 * desc:{@see http://www.jianshu.com/p/9d988a2f8ff7}
 */
@RunWith(BaseTestTestRunner.class)
@Config(constants = BuildConfig.class)
public class C01ActivityTest {

    private C01Activity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(C01Activity.class);
    }

    @Test
    public void testActivity() throws Exception {
        assertNotNull(activity);
        assertEquals(activity.getTitle(), "C01Activity");
    }

    @Test
    public void testLifecycle() throws Exception {
        ActivityController<C01Activity> activityController =
                Robolectric.buildActivity(C01Activity.class).create().start();
        Activity activity = activityController.get();

        Button button = (Button) activity.findViewById(R.id.btn01);
        assertEquals(button.getText(), "Hello Robolectric!");

        activityController.resume();
        assertEquals("onResume", button.getText());

        activityController.destroy();
        assertEquals("onDestroy", button.getText());
    }

    @Test
    public void testViewState() throws Exception {
        //测试组件状态
        CheckBox checkBox = (CheckBox) activity.findViewById(R.id.cb_box);
        Button button = (Button) activity.findViewById(R.id.btn_control_cb);
        assertTrue(button.isEnabled());

        checkBox.setChecked(true);
        //点击button，反选对话框
        button.performClick();
        assertTrue(!checkBox.isChecked());
        button.performClick();
        assertTrue(checkBox.isChecked());
    }

    @Test
    public void testDialogAndToast() throws Exception {
        Button button = (Button) activity.findViewById(R.id.btn_alert_dialog);
        button.performClick();
//        AlertDialog latestAlertDialog = ShadowAlertDialog.getLatestAlertDialog();
//        assertNotNull(latestAlertDialog);

        String textOfLatestToast = ShadowToast.getTextOfLatestToast();
        assertEquals("hello robolectric", textOfLatestToast);
    }

    @Test
    public void testResource() throws Exception {
        Application application = RuntimeEnvironment.application;
        System.out.print(application.toString());
        String app_name = application.getString(R.string.app_name);
        assertEquals("Sample_AndroidTest", app_name);
    }


}
