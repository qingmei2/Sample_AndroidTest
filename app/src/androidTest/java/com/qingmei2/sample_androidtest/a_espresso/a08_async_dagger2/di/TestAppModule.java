package com.qingmei2.sample_androidtest.a_espresso.a08_async_dagger2.di;

import android.app.Application;

import com.qingmei2.sample_androidtest.a_espresso.a08_async_dagger2.api.GitHubServiceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by QingMei on 2017/6/23.
 * desc:
 */
@Module
public class TestAppModule {

    private Application application;

    public TestAppModule(Application application){
        this.application = application;
    }

    @Provides
    @Singleton
    GitHubServiceManager provideServiceManager(){
        return new MockGitHubServiceManager();
    }

    @Provides
    @Singleton
    Application provideApplication(){
        return application;
    }
}
