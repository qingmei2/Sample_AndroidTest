package com.qingmei2.sample_androidtest.a_espresso.a08_async_dagger2.di;

import com.qingmei2.sample_androidtest.a_espresso.a08_async_dagger2.BaseApplication;


/**
 * Created by QingMei on 2017/6/23.
 * desc:
 */

public class TestApplication extends BaseApplication {

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
