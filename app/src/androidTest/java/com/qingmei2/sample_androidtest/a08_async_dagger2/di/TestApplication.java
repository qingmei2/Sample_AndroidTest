package com.qingmei2.sample_androidtest.a08_async_dagger2.di;

import com.qingmei2.sample_androidtest.a08_async_dagger2.MyApplication;


/**
 * Created by QingMei on 2017/6/23.
 * desc:
 */

public class TestApplication extends MyApplication {

    private TestAppComponent testAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        inject();
    }

    private void inject() {
        testAppComponent = DaggerTestAppComponent.builder()
                .testAppModule(new TestAppModule(this))
                .build();
        TestComponentHolder.setTestAppComponent(testAppComponent);
    }

    public TestAppComponent getTestAppComponent() {
        return testAppComponent;
    }
}
