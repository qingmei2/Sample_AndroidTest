package com.qingmei2.sample_androidtest.a_espresso.a07_async_okhttp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by QingMei on 2017/6/23.
 * desc:
 */

public interface GitHubService {

    @GET("users/{user}")
    Call<User> getUser(@Path("user") String user);

    @GET("users/{user}")
    Observable<User> getRxUser(@Path("user") String user);
}
