package com.qingmei2.sample_androidtest.a08_async_dagger2.di;

import com.qingmei2.sample_androidtest.a08_async_dagger2.A08Dagger2ActivityTest;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by QingMei on 2017/6/23.
 * desc:
 */
@Singleton
@Component(modules = TestAppModule.class)
public interface TestAppComponent {

    void inject(A08Dagger2ActivityTest activity);
}
