package com.qingmei2.sample_androidtest.m_mvp.api;

import com.qingmei2.sample_androidtest.a_espresso.a07_async_okhttp.User;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by QingMei on 2017/6/27.
 * desc:
 */

public class UserServiceManager {

    private UserService service;

    public UserServiceManager() {
        init();
    }

    private void init() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .addInterceptor(interceptor)
                .build();

        service = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build()
                .create(UserService.class);
    }

    public Observable<User> getUserInfo(String userName){
        return service.getRxUser(userName);
    }
}
