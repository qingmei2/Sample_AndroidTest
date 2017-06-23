package com.qingmei2.sample_androidtest.a08_async_dagger2.di;

/**
 * Created by QingMei on 2017/6/23.
 * desc:
 */

public class TestComponentHolder {
    private static TestAppComponent myAppComponent;

    public static void setTestAppComponent(TestAppComponent component){
        myAppComponent = component;
    }

    public static TestAppComponent getAppComponent(){
        return myAppComponent;
    }
}
