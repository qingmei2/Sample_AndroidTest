package com.qingmei2.sample_androidtest.a08_async_dagger2;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

import com.qingmei2.sample_androidtest.a08_async_dagger2.di.TestApplication;

/**
 * Created by QingMei on 2017/6/23.
 * desc:
 */

public class ServiceTestRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws InstantiationException,
            IllegalAccessException, ClassNotFoundException {
        String testApplicationClassName = TestApplication.class.getCanonicalName();
        return super.newApplication(cl, testApplicationClassName, context);
    }
}
