package com.qingmei2.sample_androidtest.a_espresso.a08_async_dagger2;

import android.app.Application;

import com.qingmei2.sample_androidtest.a_espresso.a08_async_dagger2.di.AppComponent;
import com.qingmei2.sample_androidtest.a_espresso.a08_async_dagger2.di.AppModule;
import com.qingmei2.sample_androidtest.a_espresso.a08_async_dagger2.di.ComponentHolder;
import com.qingmei2.sample_androidtest.a_espresso.a08_async_dagger2.di.DaggerAppComponent;

/**
 * Created by QingMei on 2017/6/23.
 * desc:
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        inject();
    }

    private void inject() {
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        ComponentHolder.setAppComponent(appComponent);
    }
}
