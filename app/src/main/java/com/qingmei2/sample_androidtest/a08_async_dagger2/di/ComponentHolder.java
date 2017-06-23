package com.qingmei2.sample_androidtest.a08_async_dagger2.di;

/**
 * Created by QingMei on 2017/6/23.
 * desc:
 */

public class ComponentHolder {
    private static AppComponent myAppComponent;

    public static void setAppComponent(AppComponent component){
        myAppComponent = component;
    }

    public static AppComponent getAppComponent(){
        return myAppComponent;
    }
}
