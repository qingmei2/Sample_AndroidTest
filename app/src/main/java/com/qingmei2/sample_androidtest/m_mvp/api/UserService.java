package com.qingmei2.sample_androidtest.m_mvp.api;

import com.qingmei2.sample_androidtest.a_espresso.a07_async_okhttp.User;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by QingMei on 2017/6/27.
 * desc:
 */

public interface UserService {

    @GET("users/{user}")
    Observable<User> getRxUser(@Path("user") String user);
}
